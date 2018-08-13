package judgeSystem.services;

import judgeSystem.domain.dto.userImport.UserImportJSON;
import judgeSystem.domain.entities.User;
import judgeSystem.parser.ModelParser;
import judgeSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelParser modelParser;

    @Autowired
    public UserService(UserRepository userRepository,
                       ModelParser modelParser) {
        this.userRepository = userRepository;
        this.modelParser = modelParser;
    }

    public void importDTO(UserImportJSON dto) {

        User user = this.modelParser.convert(dto, User.class);
        this.userRepository.save(user);

    }
}
