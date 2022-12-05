package com.security.vault;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Credentials.class)
public class VaultApplication implements CommandLineRunner {
	private Credentials credentials;
	VaultApplication(Credentials credentials) {
		this.credentials = credentials;
	}
	public static void main(String[] args) {
		SpringApplication.run(VaultApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Information from Vault:");
		System.out.println("Username is " + credentials.getUser());
		System.out.println("Password is " + credentials.getPassword());
	}

}
