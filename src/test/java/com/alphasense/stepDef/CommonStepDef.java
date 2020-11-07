package com.alphasense.stepDef;

import com.alphasense.config.BaseConfig;
import com.alphasense.stepDefHelper.CommonStepDefHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(loader = SpringBootContextLoader.class, classes = {BaseConfig.class})
public class CommonStepDef {

    @Autowired
    CommonStepDefHelper commonStepDefHelper;

    private static final Logger LOGGER = LogManager.getLogger(CommonStepDef.class);

    @Before
    public void beforeTest(){
        LOGGER.info("::::::::::::: Assignment Test starting :::::::::::::");
    }

    @After
    public void afterTest(){
        LOGGER.info("::::::::::::: Assignment Test Completed :::::::::::::");
    }

    @Given("Add pet to the store using addPet API")
    public void add_pet_to_the_store_using_addPet_API(DataTable dataTable) {
        commonStepDefHelper.addPetToTheStore(dataTable.asMap(String.class, Object.class));
    }

    @Given("Add two numbers {string} and {string}")
    public void addTwoNumbersAnd(String arg0, String arg1) {
        System.out.println(arg0 + arg1);
    }

    @Then("pet is added with response code {int}")
    public void petIsAddedWithResponseCode(int reponseCode) {
        Assert.assertEquals(true, commonStepDefHelper.verifyResponseCode(reponseCode));
    }

    @Then("Delete pet from the store with petId {string}")
    public void deletePetFromTheStoreWithPetId(String petId) {
        commonStepDefHelper.removePetFromStore(petId);
    }

    @Then("pet is deleted with response code {int}")
    public void petIsDeletedWithResponseCode(int reponseCode) {
        Assert.assertEquals(true, commonStepDefHelper.verifyResponseCode(reponseCode));
    }

    @Then("Order a pet using order pet API")
    public void orderAPetUsingOrderPetAPI(DataTable dataTable) {
        commonStepDefHelper.orderPetFromTheStore(dataTable.asMap(String.class, Object.class));

    }

    @Then("check order pet status as {string}")
    public void checkOrderPetStatusAs(String status) {
        Assert.assertEquals(status, commonStepDefHelper.getPetOrderDetails().getStatus());
    }
}
