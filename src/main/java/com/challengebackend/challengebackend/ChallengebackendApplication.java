package com.challengebackend.challengebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ChallengebackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengebackendApplication.class, args);
	}

}
