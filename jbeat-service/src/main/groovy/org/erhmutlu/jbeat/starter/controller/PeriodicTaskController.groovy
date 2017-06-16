package org.erhmutlu.jbeat.starter.controller

import org.erhmutlu.jbeat.service.JBeatFacade
import org.erhmutlu.jbeat.api.ParameterValidator
import org.erhmutlu.jbeat.service.schedule.ScheduledJob
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
class PeriodicTaskController {

    Logger logger = LoggerFactory.getLogger(this.getClass())

    private JBeatFacade jBeatFacade;

    PeriodicTaskController(JBeatFacade jBeatFacade) {
        this.jBeatFacade = jBeatFacade
    }
/**
     *
     * Creates a PeriodicTask instance on db
     * Schedules a job
     *
     * RequestBody params:
     *  crontab: String
     *  queue: String
     *  taskName: String
     *  isActive: String:[Optional:Default=true]
     *  params: Json[Optional:Default=Empty]
     *  description: String:[Optional:Default=null]
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/schedule",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
    ScheduledJob schedule(@RequestBody Map map) {
        logger.info("PeriodicTaskController schedule(body: {})", map)

        String crontab = map.get("crontab")
        String queue = map.get("queue")
        String taskName = map.get("taskName")
        Boolean isActive = map.getOrDefault("isActive", true)
        Map<String, Object> params = map.getOrDefault("params", new HashMap())
        String description = map.get("description")

        logger.info("crontab: {}, queue: {}, taskName: {}, isActive: {}, description: {}, params: {}", crontab, queue, taskName, isActive, description, params)
        ParameterValidator.validate("crontab", crontab)
        ParameterValidator.validate("queue", queue)
        ParameterValidator.validate("taskName", taskName)
        ParameterValidator.validate("isActive", isActive)

        return jBeatFacade.scheduleNewTask(taskName, queue, crontab, params, isActive, description)
    }


}