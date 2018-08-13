package judgeSystem.services;

import judgeSystem.domain.dto.strategyImport.StrategyImportJSON;
import judgeSystem.domain.entities.Strategy;
import judgeSystem.parser.ModelParser;
import judgeSystem.repositories.StrategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StrategyService {
    
    private final StrategyRepository strategyRepository;
    private final ModelParser modelParser;

    @Autowired
    public StrategyService(StrategyRepository strategyRepository,
                           ModelParser modelParser) {
        this.strategyRepository = strategyRepository;
        this.modelParser = modelParser;
    }

    public void importDTO(StrategyImportJSON dto) {
        Strategy strategy = this.modelParser.convert(dto, Strategy.class);
        this.strategyRepository.save(strategy);
    }
}
