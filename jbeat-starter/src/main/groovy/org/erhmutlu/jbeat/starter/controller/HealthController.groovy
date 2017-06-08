package org.erhmutlu.jbeat.starter.controller


import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest

@RestController
class HealthController {

    @RequestMapping("/status")
    Map getStatus(HttpServletRequest httpServletRequest) {
        ["STATUS": "OK"]
    }


}