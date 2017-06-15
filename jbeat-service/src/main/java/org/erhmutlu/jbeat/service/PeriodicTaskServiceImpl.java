package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.api.exceptions.JBeatException;
import org.erhmutlu.jbeat.persistency.dao.PeriodicTaskDao;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.exceptions.JBeatExceptionCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by erhmutlu on 10/06/17.
 */

@Service
public class PeriodicTaskServiceImpl implements PeriodicTaskService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PeriodicTaskDao periodicTaskDao;

    public PeriodicTaskServiceImpl(PeriodicTaskDao periodicTaskDao) {
        this.periodicTaskDao = periodicTaskDao;
    }

    @Override
    public PeriodicTask updateCrontabByTaskName(String taskName, String newCrontab) throws JBeatException{
        logger.info("PeriodicTaskService updateCrontabByTaskName (taskName: {}, newCrontab: {})", taskName, newCrontab);

        PeriodicTask periodicTask = getByTaskName(taskName);
        periodicTask.setCrontab(newCrontab);
        periodicTask.setActive(true);
        return periodicTaskDao.save(periodicTask);
    }

    @Override
    public PeriodicTask getByTaskName(String taskName) throws JBeatException{
        logger.info("PeriodicTaskService getByTaskName {}", taskName);
        PeriodicTask periodicTask = periodicTaskDao.findByTaskName(taskName);

        if(periodicTask == null){
            throw new JBeatException(JBeatExceptionCodes.PERIODIC_TASK_NOT_FOUND_BY_TASKNAME, new Object[]{taskName});
        }

        logger.debug("PeriodicTask: {} is found", periodicTask);
        return periodicTask;
    }
}
