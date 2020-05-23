package id.co.scrapper.instagram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "id.co.scrapper.instagram.repository")
@EnableTransactionManagement
public class DatabaseConfiguration {
}
