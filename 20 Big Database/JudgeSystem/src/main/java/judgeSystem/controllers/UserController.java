package judgeSystem.controllers;

import judgeSystem.domain.dto.userImport.UserImportJSON;
import judgeSystem.parser.JsonParser;
import judgeSystem.parser.MyException;
import judgeSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    private final JsonParser jsonParser;

    @Autowired
    public UserController(UserService userService, JsonParser jsonParser) {
        this.userService = userService;
        this.jsonParser = jsonParser;
    }

    public String insertUser(String content) {
        if (content==null){
            return "No content found";
        }
        StringBuilder builder = new StringBuilder();
        UserImportJSON[] DTOs = this.jsonParser.read(UserImportJSON[].class, content);
        for (UserImportJSON dto : DTOs) {
            try {
                this.userService.importDTO(dto);
                builder.append(String.format("Import user %s.%n", dto.getUsername()));
            } catch (MyException e){
                builder.append(String.format("Invalid data.%n"));
            }
        }
        return builder.toString().trim();
    }
}
