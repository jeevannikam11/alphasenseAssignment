# alphasenseAssignment

Project Info

This project is been built in Cucumber framework with Maven as a build tool and I have used Cucumber masterthought plugin
for generating HTML report.

All the test scenarios are present in feature file petStoreTests.feature
Feature file Path : src/test/resources/feature/petStoreTests.feature

Runner file is located at below path
Runner file path : src/test/java/com/alphasense/cukes/PetStoreRunner.java

We have saved the Base URL in application.yml file, which is present at below path
application.yml file path : src/test/resources/config/qa/application.yml

Steps to execute the project

Pre-requisits:
1. Maven should be installed on the machine
2. Java should be installed on the machine
3. IDE (IntelliJ or Ecllipse) should be installed on the machine

Steps:
1. Clone the repo
2. if executing from command line execute command mvn clean install
3. if executing from IDE, go to the maven and do clean install
4. Once you see BUILD SUCCESS message then go to below path to check the report
   report Path : \target\cucumber-custom-reports\feature-overview.html
