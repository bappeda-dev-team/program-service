package kk.kertaskerja.program_service;

import org.springframework.boot.SpringApplication;

public class TestProgramServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProgramServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
