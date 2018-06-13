# Cucumber-JVM-Selenium-Quickstart
Java test automation framework, capable of running cucumber BDD scenarios and features in parallel with a wealth of useful code and beautiful reports, mentor project for testers.io automation

This framework auto generates runners into the target class which allows for much easier test execution. 


mvn clean generate-test-sources
- This generates the runners and should be done from the ui-acceptance-tests pom.

mvnw clean verify -Dgui.feature.tags=@regression -Dbrowser=chrome -Dforked.jvm.count=3 -Duse.selenium.grid=false
- This command will run the tests in parallel, at the moment, it's setup to run 3 browsers in parallel.

Reports are generated in 2 formats, Cucumber Reports and Allure.
 - Cucumber Reports generate in the target class
 
 To generate Allure reports, run allure serve in the ui-acceptance-tests location (please note, allure will need to be setup before hand for this to work). 
