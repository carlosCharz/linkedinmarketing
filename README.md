# LinkedIn Email Marketing Campaign - Java

This is a Spring Boot project to get the top N users to send a mail to for a LinkedIn Marketing Campaign. The input file is **people.in** (a pipe separated file) and the output file is **people.out** (the top N user ids to send mail to). For the application I set up N = 10.


## Technologies
- SpringBoot
- Java 8
- JUnit


## To compile project (locate in the pom's directory)
```
mvn clean install -DskipTests=false
```


## To execute project

* With Maven (locate in the pom's directory)

```
mvn spring-boot:run
```

* As executable jar (locate in the jar's directory)

```
java -jar linkedinmarketing.jar
```


## Algorithm
- Since there was no described criteria in the problem definition I propose a simple algorithm to pick the top N candidate ids to send an email. I standardize the 2 different scales (recommendations and connections) using a z-score formula and then assign a weight of 70% for recommendations quantity and 30% for connections quantity. The higher the average number is, the better the candidate is. 
- According to this [article](https://www.forbes.com/sites/dailymuse/2015/01/12/having-500-linkedin-contacts-means-nothing-unless/#6d49066d5774) the number of LinkedIn contacts (connections) means nothing if we analyze alone.


## Enhancements

### Related to application
- Parameterize the input file, input location, output file and output location, weights for scales, etc
- Change executable application to a REST application where we expose an endpoint to filter people to avoid the current initialization time every time we run the application.
- Apply Maven profiles (in case we have different environments)
- More Unit Tests (good practice)
- Handle different exceptions (good practice)

### Related to business logic
- Define a more complex algorithm to find top people (For example: take into account email, phone, searching job flag, etc)
