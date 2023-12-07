package com.challengebackend.challengebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.dependenceapi")
@EntityScan(basePackages = "com.dependenceapi")
public class ChallengebackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengebackendApplication.class, args);
	}

}
