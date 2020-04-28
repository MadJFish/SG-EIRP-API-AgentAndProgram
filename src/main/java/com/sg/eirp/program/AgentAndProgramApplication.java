package com.sg.eirp.program;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.sg.eirp.program.repo")
@EntityScan("com.sg.eirp.program.model")
@SpringBootApplication
public class AgentAndProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentAndProgramApplication.class, args);
	}

}
