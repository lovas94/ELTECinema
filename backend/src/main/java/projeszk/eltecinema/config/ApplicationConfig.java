package projeszk.eltecinema.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatabaseConfig.class, DaoConfig.class})
public class ApplicationConfig {


}
