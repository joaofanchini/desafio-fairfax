package br.com.ntconsult.desafio_fairfax.integration.config;

import br.com.ntconsult.desafio_fairfax.DesafioFairfaxApplication;
import org.springframework.boot.SpringApplication;

public class TestDesafioFairfaxApplication {

	public static void main(String[] args) {
		SpringApplication.from(DesafioFairfaxApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
