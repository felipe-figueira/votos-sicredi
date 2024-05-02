package com.sicredi.votacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class VotacaoApplication {

	public static void main(String[] args) {

		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
		SpringApplication.run(VotacaoApplication.class, args);
	}



}
