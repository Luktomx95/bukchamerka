package bukmacher.bukmacher.konfiguracja;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BukmacherConfig {

    @Primary
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

//    @Primary
//    @Bean
//    public ObjectMapper getObjectMapper() {
//        return new ObjectMapper();
//    }
}
