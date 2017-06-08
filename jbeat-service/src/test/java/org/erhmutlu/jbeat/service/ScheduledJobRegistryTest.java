package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.api.Registry;
import org.erhmutlu.jbeat.persistency.dao.PeriodicTaskDao;
import org.erhmutlu.jbeat.service.config.JBeatServiceConfig;
import org.erhmutlu.jbeat.service.config.JBeatTestConfig;
import org.erhmutlu.jbeat.service.schedule.ScheduledJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by erhmutlu on 08/06/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {JBeatTestConfig.class, JBeatServiceConfig.class})
public class ScheduledJobRegistryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Registry<ScheduledJob> registry;

    @Autowired
    PeriodicTaskDao periodicTaskDao;

    @Test
    public void testMe(){
        logger.info("Test begins");
    }

}
