package mapfood;

import feign.Logger;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
public class FeignClientConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.NONE;
    }
}
