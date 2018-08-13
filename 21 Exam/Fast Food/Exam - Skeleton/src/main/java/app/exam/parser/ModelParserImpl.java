package app.exam.parser;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import app.exam.parser.interfaces.ModelParser;

@Component
public class ModelParserImpl implements ModelParser {

    public ModelParserImpl() {}

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass) {
        ModelMapper mapper = new ModelMapper();
        D result = mapper.map(source,destinationClass);
        return result;
    }

    @Override
    public <S, D> D convert(S source, Class<D> destinationClass, PropertyMap<S, D> propertyMap) {
        return null;
    }
}
