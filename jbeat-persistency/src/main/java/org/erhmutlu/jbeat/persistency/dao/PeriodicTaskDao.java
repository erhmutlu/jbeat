package org.erhmutlu.jbeat.persistency.dao;

import org.erhmutlu.jbeat.persistency.models.PeriodicTask;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by erhmutlu on 03/06/17.
 */
public interface PeriodicTaskDao extends CrudRepository<PeriodicTask, Long>{

    PeriodicTask findByTaskName(String taskName);
    PeriodicTask findByTaskNameAndIsActiveIsTrue(String taskName);

    List<PeriodicTask> findByTaskNameOrQueueAndIsActiveIsTrue(String taskName, String queue);

}
