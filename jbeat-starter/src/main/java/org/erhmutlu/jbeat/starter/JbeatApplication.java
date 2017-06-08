package org.erhmutlu.jbeat.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.erhmutlu.jbeat"})
public class JbeatApplication {

	public static void main(String[] args) {
		SpringApplication.run(JbeatApplication.class, args);
	}
}
