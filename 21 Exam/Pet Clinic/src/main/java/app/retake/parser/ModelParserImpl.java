package app.retake.parser;

import app.retake.parser.interfaces.ModelParser;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public final class ModelParserImpl implements ModelParser {

    public ModelParserImpl() {

    }
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
