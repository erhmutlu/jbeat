package org.erhmutlu.jbeat.starter;

import org.erhmutlu.jbeat.persistency.dao.PeriodicTaskDao;
import org.erhmutlu.jbeat.service.ScheduledJobRegistryService;
import org.erhmutlu.jbeat.service.schedule.ScheduledJobRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
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
