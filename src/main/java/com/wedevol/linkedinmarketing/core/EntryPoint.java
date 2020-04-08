package com.wedevol.linkedinmarketing.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Entry Point class
 *
 * @author charz
 */

@SpringBootApplication
public class EntryPoint extends SpringBootServletInitializer {

  protected static final Logger logger = LoggerFactory.getLogger(EntryPoint.class);

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(EntryPoint.class);
  }

  public static void main(String[] args) {
    new SpringApplicationBuilder(EntryPoint.class).sources(EntryPoint.class).run(args);
  }

}