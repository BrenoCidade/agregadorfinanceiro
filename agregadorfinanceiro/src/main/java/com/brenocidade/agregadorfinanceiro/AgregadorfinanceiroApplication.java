package com.brenocidade.agregadorfinanceiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgregadorfinanceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgregadorfinanceiroApplication.class, args);
	}

}
