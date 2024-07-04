package br.com.ntconsult.desafio_fairfax;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class DesafioFairfaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioFairfaxApplication.class, args);
	}

}
