# SC_TestNG

## About
Simple Selenium automation scripts with TestNG reporting using Maven dependencies. GUI Web text input and an API test.

## Maven Dependencies include:
- selenium-java
- testng
- rest-assured
- (see POM.xml file for full dependencies list).

## Required
- Java
- Github
- Maven
- CI/CD tool of choice
- Chrome Webdriver

## Run locally
- Ensure Java, Maven are installed. 
- Install IDE of choice 
- Import the project
- Run from testng.xml file or run from console using:
```mvn clean install```

## Run from Jenkins
- In Jenkins, create "New Item"
- Provide name and description
- Select "Pipeline"
- Run the insert pipeline script below:
```pipeline{
    agent any
    environment {
    //Your java environment
      JAVA_HOME = "C:\\Program Files\\Java\\jdk1.8.0_311\\"
    }    
    stages{
        stage("Build"){
            steps{
                git credentialsId: '<get this from your Jenkins config.xml file>',
                    url: '<Github repository URL>'
                script{
                    bat(/mvn clean install/)
                }
            }
        }
    }
}
```

## Known issues
- testng.xml results file not importing when running from Jenkins due to following error:
```
Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M5:test (default-test) on project SC_TestNG: There are test failures.
```

Error occurs if pipeline step below is included to extract testng-results.xml at runtime in Jenkins, so there's a need to refine getting results file when running in Jenkins:
```
step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
```
- other minor bugs may be found
- Disclaimer: This is my first Selenium script! Don't expect miracles
