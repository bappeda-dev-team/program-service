package kk.kertaskerja.program_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ProgramServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgramServiceApplication.class, args);
	}

}
