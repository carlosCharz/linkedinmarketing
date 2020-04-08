package com.wedevol.linkedinmarketing.core.configuration;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * App settings loaded from a property file
 * 
 * @author charz
 */

@Component
@ConfigurationProperties(prefix = "app")
public class AppSetting {

  private static final Logger logger = LoggerFactory.getLogger(AppSetting.class);

  private Integer outputQty;

  public AppSetting() {
    logger.info("Loading app properties");
  }

  @PostConstruct
  public void postConstruct() {
    logger.info("App properties -> outputQty: {}", outputQty);
  }

  public Integer getOutputQty() {
    return outputQty;
  }

  public void setOutputQty(Integer outputQty) {
    this.outputQty = outputQty;
  }

}
