package org.erhmutlu.jbeat.service.schedule;

import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.erhmutlu.jbeat.service.PeriodicTaskService;
import org.erhmutlu.jbeat.service.RabbitService;

/**
 * Created by erhmutlu on 05/06/17.
 */
public class RabbitJob extends AbstractJob{

    private PeriodicTask task;
    private RabbitService rabbitService;
    private PeriodicTaskService periodicTaskService;

    public RabbitJob(PeriodicTask periodicTask, RabbitService rabbitService, PeriodicTaskService periodicTaskService) {
        super();
        this.task = periodicTask;
        this.rabbitService = rabbitService;
        this.periodicTaskService = periodicTaskService;
    }


    @Override
    public void run(){
        logger.info(task.getTaskName() + " is running!");
//        rabbitService.write(task.getQueue(), task.getParams());
//        periodicTaskService.increaseTotalRunByTaskName(task.getTaskName());

    }

    @Override
    public void schedule(){
        scheduler.schedule(task.getCrontab(), this);
    }

    @Override
    public void setPeriodicTask(PeriodicTask periodicTask) {
        task = periodicTask;
    }

    @Override
    public PeriodicTask getTask() {
        return task;
    }

    @Override
    public String toString() {
        return "RabbitJob{" +
                "task=" + task +
                '}';
    }

}
