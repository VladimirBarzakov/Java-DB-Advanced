package judgeSystem.services;

import judgeSystem.domain.dto.problemImport.ProblemXML;
import judgeSystem.domain.entities.Contest;
import judgeSystem.domain.entities.Problem;
import judgeSystem.repositories.ContestRepository;
import judgeSystem.repositories.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final ContestRepository contestRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository,
                          ContestRepository contestRepository) {
        this.problemRepository = problemRepository;
        this.contestRepository = contestRepository;
    }

    public String importDTO(ProblemXML problemXML) {

        Optional<Contest> contest = this.contestRepository.findById(problemXML.getContest().getId());
        if (!contest.isPresent()){
            return String.format("The problems's contest is not present in database.%n");
        }
        Contest contestDB=contest.get();
        Problem problem = new Problem();
        problem.setName(problemXML.getName());
        problem.setContest(contestDB);
        this.problemRepository.save(problem);
        return String.format("Problems %s successfully inserted in database.%n",
                problemXML.getName());
    }
}
