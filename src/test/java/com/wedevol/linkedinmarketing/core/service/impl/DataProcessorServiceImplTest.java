package com.wedevol.linkedinmarketing.core.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class DataProcessorServiceImplTest {

    @Test
    @DisplayName("Line array split not empty")
    public void lineArraySplitNotEmptyTest() {
        String line = "4567|arturo|perez|teleport engineering manager|Germany|Telecommunications|3|176";
        String[] lineParts = line.split("\\|");
        Assert.notEmpty(lineParts, "The array should not be empty");
    }

    @Test
    @DisplayName("Line array split with exception")
    public void lineArraySplitWithExceptionTest() {
        Assertions.assertThrows(Exception.class, () -> {
            String line = "4567|arturo|perez|||||";
            String[] lineParts = line.split("\\|");
            lineParts[5].trim();
        });
    }

    // TODO add more tests

}
