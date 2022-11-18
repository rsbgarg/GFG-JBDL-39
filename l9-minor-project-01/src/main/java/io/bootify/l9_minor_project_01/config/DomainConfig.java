package io.bootify.l9_minor_project_01.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("io.bootify.l9_minor_project_01.domain")
@EnableJpaRepositories("io.bootify.l9_minor_project_01.repos")
@EnableTransactionManagement
public class DomainConfig {
}
