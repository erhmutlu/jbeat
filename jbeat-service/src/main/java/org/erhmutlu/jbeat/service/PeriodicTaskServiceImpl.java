package org.erhmutlu.jbeat.service;

import org.erhmutlu.jbeat.api.exceptions.JBeatException;
import org.erhmutlu.jbeat.api.exceptions.JBeatExceptionCodes;
import org.erhmutlu.jbeat.persistency.dao.PeriodicTaskDao;
import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public PeriodicTask createPeriodicTask(String taskName, String queue, String crontab, Map params, String description) throws JBeatException{
        logger.info("PeriodicTaskService create (taskName: {}, crontab: {})",
                taskName, crontab);

        Boolean exist = checkExistByTaskNameOrQueue(taskName, queue);
        if(exist){
            throw new JBeatException(JBeatExceptionCodes.PERIODIC_TASK_ALREADY_EXIST);
        }

        PeriodicTask periodicTask = new PeriodicTask();
        periodicTask.setTaskName(taskName);
        periodicTask.setQueue(queue);
        periodicTask.setCrontab(crontab);
        periodicTask.setParams(params);
        periodicTask.setActive(true);
        periodicTask.setDescription(description);
        return periodicTaskDao.save(periodicTask);
    }

    @Override
    public PeriodicTask updateCrontabByTaskName(String taskName, String newCrontab) throws JBeatException{
        logger.info("PeriodicTaskService updateCrontabByTaskName (taskName: {}, newCrontab: {})", taskName, newCrontab);

        PeriodicTask periodicTask = getTaskByName(taskName);
        periodicTask.setCrontab(newCrontab);
        periodicTask.setActive(true);
        return periodicTaskDao.save(periodicTask);
    }

    @Override
    public PeriodicTask getTaskByName(String taskName) throws JBeatException{
        logger.info("PeriodicTaskService getTaskByName {}", taskName);
        PeriodicTask periodicTask = periodicTaskDao.findByTaskName(taskName);

        if(periodicTask == null){
            throw new JBeatException(JBeatExceptionCodes.PERIODIC_TASK_NOT_FOUND_BY_TASKNAME, new Object[]{taskName});
        }

        logger.debug("Active PeriodicTask: {} is found", periodicTask);
        return periodicTask;
    }

    @Override
    public PeriodicTask getTaskByNameOrQueue(String taskName, String queue) throws JBeatException{
        logger.info("PeriodicTaskService getTaskByNameOrQueue taskName: {}, queue: {}", taskName, queue);
        List<PeriodicTask> actives = periodicTaskDao.findByTaskNameOrQueueAndIsActiveIsTrue(taskName, queue);
        if(actives.size() == 0){
            throw new JBeatException(JBeatExceptionCodes.PERIODIC_TASK_NOT_FOUND_BY_TASKNAME_OR_QUEUE, new Object[]{taskName});
        }else if(actives.size() > 1){
            throw new JBeatException(JBeatExceptionCodes.MULTIPLE_PERIODIC_TASK_FOUND);
        }

        PeriodicTask periodicTask = actives.get(0);
        logger.debug("ActivePeriodicTask: {} is found", periodicTask);
        return periodicTask;
    }

    @Override
    public boolean checkExistByTaskNameOrQueue(String taskName, String queue){
        try {
            getTaskByNameOrQueue(taskName, queue);
            //if a JBeatException is not thrown, any PeriodicTask is found with given values
            return true;
        } catch (JBeatException e) {
            return false;
        }
    }

    @Override
    public PeriodicTask setActiveByTaskName(String taskName, Boolean isActive) throws JBeatException{
        logger.info("PeriodicTaskService setActiveByTaskName taskName: {}, isActive: {}", taskName, isActive);
        PeriodicTask periodicTask = getTaskByName(taskName);
        periodicTask.setActive(isActive);
        return periodicTaskDao.save(periodicTask);
    }
}
