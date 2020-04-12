package com.wedevol.linkedinmarketing.core.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.Assert;

@RunWith(MockitoJUnitRunner.class)
public class DataProcessorServiceImplTest {

  @Test
  public void lineArraySplitTest() {
    String line = "4567|arturo|perez|teleport engineering manager|Germany|Telecommunications|3|176";
    String[] lineParts = line.split("\\|");
    Assert.notEmpty(lineParts, "The array is empty");
  }

  @Test(expected = Exception.class)
  public void lineArraySplitWithExceptionTest() {
    String line = "4567|arturo|perez|||||";
    String[] lineParts = line.split("\\|");
    lineParts[5].trim();
  }

  // TODO add more tests

}
