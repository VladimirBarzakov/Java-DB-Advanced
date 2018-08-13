package judgeSystem.controllers;

import judgeSystem.domain.dto.strategyImport.StrategyImportJSON;
import judgeSystem.parser.JsonParser;
import judgeSystem.parser.MyException;
import judgeSystem.services.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StrategyController {

    private final StrategyService strategyService;

    private final JsonParser jsonParser;

    @Autowired
    public StrategyController(JsonParser jsonParser,
                              StrategyService strategyService) {
        this.jsonParser = jsonParser;
        this.strategyService = strategyService;
    }

    public String insertStrategy(String content) {
        if (content==null){
            return "No content found";
        }
        StringBuilder builder = new StringBuilder();
        StrategyImportJSON[] DTOs = this.jsonParser.read(
                StrategyImportJSON[].class, content);
        for (StrategyImportJSON dto : DTOs) {
            try {
                this.strategyService.importDTO(dto);
                builder.append(String.format("Added strategy %s.%n",dto.getName()));
            } catch (MyException e){
                builder.append(String.format("Invalid data.%n"));
            }

        }


        return builder.toString().trim();
    }
}
