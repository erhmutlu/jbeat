package org.erhmutlu.jbeat.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"org.erhmutlu.jbeat"})
@EntityScan(basePackages = "org.erhmutlu.jbeat.persistency.models")
@EnableJpaRepositories(basePackages = "org.erhmutlu.jbeat.persistency.dao")
public class JBeatApplication {


//	@Bean
//	CommandLineRunner runner(ScheduledJobRegistry scheduledJobRegistry) {
//		return (strings -> {
//			System.out.println(scheduledJobRegistry.get("lalala"));
//
//		});
//	}



		public static void main(String[] args) {
		SpringApplication.run(JBeatApplication.class, args);
	}
}
