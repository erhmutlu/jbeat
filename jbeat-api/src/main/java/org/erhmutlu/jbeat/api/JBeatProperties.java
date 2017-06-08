package org.erhmutlu.jbeat.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * Created by erhmutlu on 08/06/17.
 */
@ConfigurationProperties("jbeat")
@Validated
public class JBeatProperties {
}
