package judgeSystem.services;

import judgeSystem.domain.dto.submissionImport.SubmissionImportXML;
import judgeSystem.domain.entities.*;
import judgeSystem.parser.ModelParser;
import judgeSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;
    private final StrategyRepository strategyRepository;
    private final ModelParser modelParser;
    private final MaxResultForProblemRepository maxResultForProblemRepository;
    private final MaxResultForContestRepository maxResultForContestRepository;

    @Autowired
    public SubmissionService(SubmissionRepository submissionRepository,
                             UserRepository userRepository,
                             ProblemRepository problemRepository,
                             StrategyRepository strategyRepository,
                             ModelParser modelParser,
                             MaxResultForProblemRepository maxResultForProblemRepository,
                             MaxResultForContestRepository maxResultForContestRepository) {
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
        this.strategyRepository = strategyRepository;
        this.modelParser = modelParser;
        this.maxResultForProblemRepository = maxResultForProblemRepository;
        this.maxResultForContestRepository = maxResultForContestRepository;
    }

    public String importDTO(SubmissionImportXML dto) {
        Optional<User> userOpt = this.userRepository.findById(dto.getUser().getId());
        Optional<Problem> problemOpt = this.problemRepository.findById(dto.getProblem().getId());
        Optional<Strategy> strategyOpt = this.strategyRepository.findById(dto.getStrategy().getId());
        if (!userOpt.isPresent() || !problemOpt.isPresent() || !strategyOpt.isPresent()){
            return String.format("Invalid data.%n");
        }
        User user = userOpt.get();
        Problem problem = problemOpt.get();
        Strategy strategy = strategyOpt.get();
        if (!user.getContests().contains(problem.getContest())){
            return String.format("User is not contestant in contest!%n");
        }
        if (!problem.getContest().getStrategies().contains(strategy)){
            return String.format("Unsupported strategy!%n");
        }
        Submission submission= this.modelParser.convert(dto,Submission.class);
        if (submission.getPerformance()<0){
            return "Invalid data";
        }
        submission.setUser(user);
        submission.setProblem(problem);
        submission.setStrategy(strategy);
        submission=this.submissionRepository.save(submission);
        MaxResultForProblem maxResultForProblem = this.maxResultForProblemRepository
                .getOneByProblemAndUser(problem,user);
        MaxResultForContest maxResultForContest;
        if (maxResultForProblem==null){
            maxResultForProblem=new MaxResultForProblem();
            maxResultForProblem.setProblem(problem);
            maxResultForProblem.setUser(user);
            maxResultForProblem.setSubmission(submission);
            this.maxResultForProblemRepository.saveAndFlush(maxResultForProblem);

            maxResultForContest=this.maxResultForContestRepository.getOneByContestAndUser(
                    problem.getContest(), user);
            maxResultForContest.setOverallPoints(
                    maxResultForContest.getOverallPoints()+submission.getPoints());
            List<MaxResultForProblem> bestSolutionsList = this.maxResultForProblemRepository.
                    getMaxResultSubmissionsList(user,problem.getContest());
            double aggregatePerformance = 0;
            for (MaxResultForProblem resultForProblem : bestSolutionsList) {
                aggregatePerformance+=resultForProblem.getSubmission().getPerformance();
            }
            maxResultForContest.setAveragePerformance(aggregatePerformance/bestSolutionsList.size());
            this.maxResultForContestRepository.save(maxResultForContest);
        } else if (maxResultForProblem.getSubmission().getPoints()<submission.getPoints() ||
                ( maxResultForProblem.getSubmission().getPoints()==submission.getPoints() &&
                  maxResultForProblem.getSubmission().getPerformance()>submission.getPerformance()) ){
            maxResultForProblem.setSubmission(submission);
            this.maxResultForProblemRepository.saveAndFlush(maxResultForProblem);
            maxResultForContest=this.maxResultForContestRepository.getOneByContestAndUser(
                    problem.getContest(), user);
            List<MaxResultForProblem> bestSolutionsList = this.maxResultForProblemRepository.
                    getMaxResultSubmissionsList(user,problem.getContest());
            double aggregatePerformance = 0;
            double aggregatePoints=0;
            for (MaxResultForProblem resultForProblem : bestSolutionsList) {
                aggregatePerformance+=resultForProblem.getSubmission().getPerformance();
                aggregatePoints+=resultForProblem.getSubmission().getPoints();
            }
            maxResultForContest.setOverallPoints(aggregatePoints);
            maxResultForContest.setAveragePerformance(aggregatePerformance/bestSolutionsList.size());
            this.maxResultForContestRepository.saveAndFlush(maxResultForContest);
        }
        problem.getContestants().add(user);
        this.problemRepository.save(problem);
        return String.format("User %s successfully submitted!%n",submission.getUser().getUsername());
    }
}
