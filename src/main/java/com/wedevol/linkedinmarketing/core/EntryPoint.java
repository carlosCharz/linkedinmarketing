package com.wedevol.linkedinmarketing.core;

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

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EntryPoint.class);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(EntryPoint.class).sources(EntryPoint.class).run(args);
    }

}
