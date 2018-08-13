package judgeSystem.parser;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelParser {

    public <S, D> D convert(S source, Class<D> destinationClass) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(source,destinationClass);
    }
}
