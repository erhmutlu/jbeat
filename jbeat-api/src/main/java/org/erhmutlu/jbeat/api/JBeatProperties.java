package org.erhmutlu.jbeat.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * Created by erhmutlu on 08/06/17.
 */
@ConfigurationProperties("jbeat")
@Validated
public class JBeatProperties {

    private Runner runner = new Runner();

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public Boolean isAutoDiscoverChangesEnabled() {
        return getRunner().isAutoDiscoverChangesEnabled();
    }

    public Long getAutoDiscoverInterval() {
        return getRunner().getAutoDiscoverInterval();
    }

    public static class Runner {
        private Boolean autoDiscoverChangesEnabled = false;
        private Long autoDiscoverInterval = 60000l;

        public Boolean isAutoDiscoverChangesEnabled() {
            return autoDiscoverChangesEnabled;
        }

        public void setAutoDiscoverChangesEnabled(Boolean autoDiscoverChangesEnabled) {
            this.autoDiscoverChangesEnabled = autoDiscoverChangesEnabled;
        }

        public Long getAutoDiscoverInterval() {
            return autoDiscoverInterval;
        }

        public void setAutoDiscoverInterval(Long autoDiscoverInterval) {
            this.autoDiscoverInterval = autoDiscoverInterval;
        }
    }
}
