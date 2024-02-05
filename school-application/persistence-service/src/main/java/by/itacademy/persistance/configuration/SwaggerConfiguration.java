package by.itacademy.persistance.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("School service")
                        .description("School REST Service for CRUD operations with school entity")
                        .version("1.0.0")
                )
                .servers(Collections.singletonList(
                        new Server()
                                .description("Local Machine")
                                .url("http://localhost:8080")
                ));
    }

    @Bean
    public ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }
}
