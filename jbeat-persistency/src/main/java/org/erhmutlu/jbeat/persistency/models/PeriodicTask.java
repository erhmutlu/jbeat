package org.erhmutlu.jbeat.persistency.models;

import org.erhmutlu.jbeat.persistency.converter.JpaJsonConverter;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * Created by erhmutlu on 03/06/17.
 */
@Entity
@Table(name = "jbeat_periodic_tasks")
public class PeriodicTask {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "crontab", nullable = false)
    private String crontab;

    @Column(name = "queue_name", nullable = false, unique = true)
    private String queue;

    @Column(name = "task_name", nullable = false, unique = true)
    private String taskName;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "params")
    @Convert(converter = JpaJsonConverter.class)
    private Object params;

    @Column(name = "total_run", nullable = false)
    private Long totalRun = 0l;

    @Column(name = "lastRun")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastRun;

    @Column(name = "description")
    private String description;

    public PeriodicTask(String crontab, String queue, String taskName, Boolean isActive, Object params, Long totalRun, Date lastRun, String description) {
        this.crontab = crontab;
        this.queue = queue;
        this.taskName = taskName;
        this.isActive = isActive;
        this.params = params;
        this.totalRun = totalRun;
        this.lastRun = lastRun;
        this.description = description;
    }

    public PeriodicTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrontab() {
        return crontab;
    }

    public void setCrontab(String crontab) {
        this.crontab = crontab;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Map getParams() {
        return (Map) params;
    }

    public void setParams(Object params) {
        this.params = (Map) params;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getTotalRun() {
        return totalRun;
    }

    public void setTotalRun(Long totalRun) {
        this.totalRun = totalRun;
    }

    public Date getLastRun() {
        return lastRun;
    }

    public void setLastRun(Date lastRun) {
        this.lastRun = lastRun;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PeriodicTask{" +
                "id=" + id +
                ", crontab='" + crontab + '\'' +
                ", queue='" + queue + '\'' +
                ", taskName='" + taskName + '\'' +
                ", isActive=" + isActive +
                ", params=" + params +
                ", totalRun=" + totalRun +
                ", lastRun=" + lastRun +
                ", description='" + description + '\'' +
                '}';
    }
}

