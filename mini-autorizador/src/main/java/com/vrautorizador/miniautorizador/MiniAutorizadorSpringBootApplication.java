package com.vrautorizador.miniautorizador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.vrautorizador.miniautorizador", "com.vrautorizador.miniautorizador.config"})
public class MiniAutorizadorSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniAutorizadorSpringBootApplication.class, args);
	}

}
