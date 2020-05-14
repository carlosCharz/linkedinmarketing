package com.wedevol.linkedinmarketing.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Entry Point class
 *
 * @author charz
 */

@SpringBootApplication
public class EntryPoint {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EntryPoint.class).sources(EntryPoint.class).run(args);
    }

}
