package br.com.itsolution.fintech.pix_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PixApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PixApiApplication.class, args);
	}

}
