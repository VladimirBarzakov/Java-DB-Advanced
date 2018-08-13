package car_dealer.utils.beans;

import car_dealer.utils.config.ModelMapperConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanImportData {

    @Bean
    public Gson getgson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();
        ModelMapperConfig config = new ModelMapperConfig(mapper);
        return mapper;
    }
}
