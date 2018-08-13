package judgeSystem.parser;

import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;

@Component
public class ValidationUtil {

    public static <T> boolean isValid(T t) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        return t!=null && validator.validate(t).size()==0;
    }
}
