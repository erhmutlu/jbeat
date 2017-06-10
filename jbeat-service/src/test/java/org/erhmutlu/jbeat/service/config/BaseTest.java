package org.erhmutlu.jbeat.service.config;

import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.config.JBeatServiceConfig;
import org.erhmutlu.jbeat.service.config.JBeatTestConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by erhmutlu on 10/06/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JBeatTestConfig.class, JBeatServiceConfig.class})
public class BaseTest {

    private Random random = new Random();

    protected PeriodicTask randomPeriodicTaskInstance(){
        PeriodicTask periodicTask = new PeriodicTask();
        periodicTask.setTaskName(generateRandomString(7));
        periodicTask.setQueue(generateRandomString(5));
        periodicTask.setCrontab("* * * * * *");
        periodicTask.setParams(new HashMap<>());
        periodicTask.setActive(true);
        periodicTask.setDescription("test");

        return periodicTask;
    }

    private String generateRandomString(int length){
        return this.random.ints(48,122)
                .filter(i-> (i<57 || i>65) && (i <90 || i>97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
