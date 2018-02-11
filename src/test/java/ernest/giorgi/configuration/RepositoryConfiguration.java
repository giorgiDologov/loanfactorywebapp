package ernest.giorgi.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"ernest.giorgi.domain"})
@EnableJpaRepositories(basePackages = {"ernest.giorgi.repository"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
