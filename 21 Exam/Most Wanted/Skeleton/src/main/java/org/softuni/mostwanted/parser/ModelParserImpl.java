package org.softuni.mostwanted.parser;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.springframework.stereotype.Component;

@Component
public class ModelParserImpl implements ModelParser{

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
