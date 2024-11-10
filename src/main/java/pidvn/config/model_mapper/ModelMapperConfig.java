package pidvn.config.model_mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)    // Đặt chế độ strict (map chính xác tên thuộc tính)
                .setFieldMatchingEnabled(true);                    // Cho phép ánh xạ theo tên thuộc tính
        return modelMapper;
    }
}
