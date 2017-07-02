package org.erhmutlu.jbeat.starter;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JBeatApplication {

    @Autowired
    AmqpAdmin rabbitAdmin;

    public static void main(String[] args) {
        SpringApplication.run(JBeatApplication.class, args);
    }
}
