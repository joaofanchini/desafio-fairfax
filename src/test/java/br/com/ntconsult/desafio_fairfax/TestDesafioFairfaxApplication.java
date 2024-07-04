package br.com.ntconsult.desafio_fairfax;

import org.springframework.boot.SpringApplication;

public class TestDesafioFairfaxApplication {

	public static void main(String[] args) {
		SpringApplication.from(DesafioFairfaxApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
