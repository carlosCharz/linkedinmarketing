package com.wedevol.linkedinmarketing.core.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.wedevol.linkedinmarketing.core.configuration.AppSetting;
import com.wedevol.linkedinmarketing.core.entity.Person;
import com.wedevol.linkedinmarketing.core.service.DataProcessorService;

/**
 * Service that manages the business logic
 *
 * @author charz
 */

@Service
public class DataProcessorServiceImpl implements DataProcessorService {

  protected static final Logger logger = LoggerFactory.getLogger(DataProcessorServiceImpl.class);
  private static final String CHARSET_UTF8 = "UTF8";
  private static final String LINE_SEPARATOR = "\\|";

  @Autowired
  private AppSetting appSetting;

  @PostConstruct
  public void postConstruct() throws IOException {
    logger.info("Starting process ...");
    executeMarketingAnalysis();
  }

  @Override
  public void executeMarketingAnalysis() {
    // read input
    logger.info("Reading input file ...");
    List<Person> people = readPeopleFromInputFile();
    logger.info("People: {}", people.size());

    // process
    logger.info("Processing data ...");
    List<Person> targetPeople = processData(people);
    logger.info("Target People: {}", targetPeople.size());

    // print
    printData(targetPeople);

    // build output
    logger.info("Building output file ...");
    createOutputFile(targetPeople);
  }

  private void createOutputFile(List<Person> targetPeople) {
    try (PrintWriter printWriter = new PrintWriter(new FileWriter("people.out"));) {
      targetPeople.stream().forEach(p -> printWriter.println(p.getId()));
      printWriter.close();
    } catch (IOException e) {
      logger.error("Error writing the output file. {}", e);
    }
  }

  private void printData(List<Person> people) {
    people.stream().forEach(p -> logger.info(p.toString()));
  }

  private List<Person> processData(List<Person> people) {
    // order data based on a criteria
    people.sort(peopleWeightedSortComparator());
    // limit output to N first elements
    if (people.size() >= appSetting.getOutputQty()) {
      List<Person> peopleSubset = people.stream().limit(appSetting.getOutputQty()).collect(Collectors.toList());
      return peopleSubset;
    } else {
      logger.info("People qty is smaller than output qty");
      return people;
    }
  }

  private Comparator<Person> peopleWeightedSortComparator() {
    // NOTE I give more weight to people that have more recommendations (70%) than connections (30%)
    return (p1, p2) -> {
      // TODO weights and averages can be parameterized
      int p1RecoQty = p1.getRecommendationsQty();
      int p1ConnQty = p1.getConnectionsQty();
      int p2RecoQty = p2.getRecommendationsQty();
      int p2ConnQty = p2.getConnectionsQty();
      // apply a z-score standardization with weights
      // NOTE assuming average recommendations = 5 with deviation = 2
      // NOTE assuming average connections = 400 with deviation = 50
      // z = (x – μ) / σ
      double p1Score = ((p1RecoQty - 5) / 2.0 * 7.0 + (p1ConnQty - 300) / 50.0 * 3.0) / 10.0;
      double p2Score = ((p2RecoQty - 5) / 2.0 * 7.0 + (p2ConnQty - 300) / 50.0 * 3.0) / 10.0;
      return Double.compare(p2Score, p1Score);
    };
  }

  private List<Person> readPeopleFromInputFile() {
    List<Person> people = new ArrayList<>();
    try {
      // TODO file name and file path can be parameterized
      Resource resource = new ClassPathResource("people.in");
      InputStream inputStream = getInputStream(resource);
      try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, CHARSET_UTF8))) {
        String line;
        String[] lineParts;
        while ((line = buffer.readLine()) != null) {
          if (!line.trim().isEmpty()) {
            lineParts = line.split(LINE_SEPARATOR);
            // to avoid index out of bounds (Example: a|b|||||)
            String id = "";
            if (lineParts.length > 0) {
              id = lineParts[0].trim();
            }

            String name = "";
            if (lineParts.length > 1) {
              name = lineParts[1].trim();
            }

            String lastName = "";
            if (lineParts.length > 2) {
              lastName = lineParts[2].trim();
            }

            String currentRole = "";
            if (lineParts.length > 3) {
              currentRole = lineParts[3].trim();
            }

            String country = "";
            if (lineParts.length > 4) {
              country = lineParts[4].trim();
            }

            String industry = "";
            if (lineParts.length > 5) {
              industry = lineParts[5].trim();
            }

            // for numeric values: if empty -> 0
            int recommendationsQty = 0;
            if (lineParts.length > 6) {
              recommendationsQty = lineParts[6].trim().isEmpty() ? 0 : Integer.parseInt(lineParts[6]);
            }

            int connectionsQty = 0;
            if (lineParts.length > 7) {
              connectionsQty = lineParts[7].trim().isEmpty() ? 0 : Integer.parseInt(lineParts[7]);
            }

            Person person =
                new Person(id, name, lastName, currentRole, country, industry, recommendationsQty, connectionsQty);
            people.add(person);
          } else {
            logger.info("Empty line ...");
          }
        }
      } catch (FileNotFoundException e) {
        logger.error("Error reading the input file. {}", e);
      }
    } catch (Exception e) {
      logger.error("Error opening the input file. {}", e);
    }
    return people;
  }

  private InputStream getInputStream(Resource resource) throws IOException {
    return resource.getInputStream();

  }

}
