package com.portfolio.blog.config;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing      // JPA의 Audition기능을 활성화
public class AuditConfig {

    public static void main(String[] args) {
        SpringApplication.run(AuditConfig.class, args);
    }

}
