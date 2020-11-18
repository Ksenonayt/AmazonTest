# AmazonTest

Project is implemented with usage of Selenium WebDriver, Junit and Cucumber

To configure project before execution: 
  - find src/main/resources/config.properties
  - set chrome.driver to your chromedriver location,
  by default value is chrome.driver=/usr/local/bin/chromedriver

To run tests:
  - Clone repository to your local workstation
  - go to project root folder
  - execute mvn verify via console
After execution finished, search for target/cucumber-html-report.html

Html report is being generated by maven-cucumber-reporting plugin
