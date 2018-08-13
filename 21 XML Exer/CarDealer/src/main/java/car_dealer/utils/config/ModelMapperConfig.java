package car_dealer.utils.config;

import org.modelmapper.ModelMapper;

public class ModelMapperConfig {
    private final ModelMapper mapper;

    public ModelMapperConfig(ModelMapper mapper){
        this.mapper=mapper;
        this.init();
    }

    private void init() {
    }
}
