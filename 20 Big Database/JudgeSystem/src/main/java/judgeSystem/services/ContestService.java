package judgeSystem.services;

import judgeSystem.domain.dto.contetsImport.ContestImportJSON;
import judgeSystem.domain.dto.contetsImport.ContestStrategyImportJSON;
import judgeSystem.domain.dto.enrollmentInport.ParticipationXML;
import judgeSystem.domain.entities.*;
import judgeSystem.parser.ModelParser;
import judgeSystem.parser.MyException;
import judgeSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ContestService {

    private final ContestRepository contestRepository;
    private final StrategyRepository strategyRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final MaxResultForContestRepository maxResultForContestRepository;
    private final ModelParser modelParser;

    @Autowired
    public ContestService(ContestRepository contestRepository,
                          StrategyRepository strategyRepository,
                          CategoryRepository categoryRepository,
                          UserRepository userRepository,
                          MaxResultForContestRepository maxResultForContestRepository,
                          ModelParser modelParser) {
        this.contestRepository = contestRepository;
        this.strategyRepository = strategyRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.maxResultForContestRepository = maxResultForContestRepository;
        this.modelParser = modelParser;
    }

    public void importDTO(ContestImportJSON dto) {
        Contest contest = this.modelParser.convert(dto, Contest.class);
        Category category = this.categoryRepository.findOneByName(dto.getCategory().getName());
        if (category==null){
            throw new MyException();
        }
        contest.setCategory(category);
        Set<Strategy> allowedStrategies =new HashSet<>();
        for (ContestStrategyImportJSON strategyDTO : dto.getAllowedStrategies()) {
            Strategy strategy = this.strategyRepository.findOneByName(strategyDTO.getName());
            if (strategy!=null){
                allowedStrategies.add(strategy);
            }
        }
        if (allowedStrategies.size()==0){
            throw new MyException();
        }
        contest.setStrategies(allowedStrategies);
        this.contestRepository.save(contest);
    }

    public String importParticipation(ParticipationXML dto) {
        Optional<User> user = this.userRepository.findById(dto.getUser().getId());
        Optional<Contest> contest =this.contestRepository.findById(dto.getContest().getId());
        if (!user.isPresent() || !contest.isPresent()){
            return String.format("Invalid data.%n");
        } else if(contest.get().getContestants().contains(user.get())){
            return String.format("User already has enrolled to contest.%n");
        } else{
            Contest contestDB=contest.get();
            User userDB=user.get();
            contestDB.getContestants().add(userDB);
            contestDB=this.contestRepository.save(contestDB);
            MaxResultForContest maxResultForContest = new MaxResultForContest(contestDB, userDB);
            this.maxResultForContestRepository.saveAndFlush(maxResultForContest);
            return String.format("User %s has successfully enrolled to the contest.%n",
                    user.get().getUsername());
        }
    }
}
