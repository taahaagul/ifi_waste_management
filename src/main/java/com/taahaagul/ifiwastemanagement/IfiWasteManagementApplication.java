package com.taahaagul.ifiwastemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IfiWasteManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfiWasteManagementApplication.class, args);
	}

}
