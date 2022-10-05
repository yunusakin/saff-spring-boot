package com.yunusakin.saff;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SAFF API", version = "1.0", description = "Store and Fetch Files API Information"))
public class SaffBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaffBackendApplication.class, args);
	}

}
