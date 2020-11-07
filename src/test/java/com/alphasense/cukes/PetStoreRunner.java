package com.alphasense.cukes;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/feature/petStoreTests.feature"},
        glue = {"com/alphasense"},
        tags = "@Test",
        plugin= {"pretty","html:target/site/cucumber-pretty","json:target/cucumber-html-reports/cucumber.json"}

)
public class PetStoreRunner {
}
