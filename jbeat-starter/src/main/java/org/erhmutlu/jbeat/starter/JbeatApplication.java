package org.erhmutlu.jbeat.starter;

import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.PeriodicTaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class JBeatApplication {


	@Bean
	CommandLineRunner cmdRunner(PeriodicTaskService periodicTaskService){
		return (strings -> {
			try {
				PeriodicTask periodicTask = periodicTaskService.getTaskById(251L);
				System.out.println(periodicTask);
				System.out.println("HİHİHİHİ");
			}catch (Exception e){
				System.out.println("LALALALALA");
				e.printStackTrace();
			}
		});
	}



		public static void main(String[] args) {
		SpringApplication.run(JBeatApplication.class, args);
	}
}
