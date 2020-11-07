package com.alphasense.stepDefHelper;


import com.alphasense.pojoClasses.AddPet;
import com.alphasense.pojoClasses.Category;
import com.alphasense.pojoClasses.OrderPet;
import com.alphasense.pojoClasses.Tag;
import io.cucumber.messages.internal.com.google.common.collect.ImmutableList;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.config.EncoderConfig.encoderConfig;

@Component
public class CommonStepDefHelper {

    @Value("${application.base.url}")
    private String baseUrl;

    private static final Logger LOGGER = LogManager.getLogger(CommonStepDefHelper.class);

    private static Response response;
//    String baseUrl = "https://petstore.swagger.io/";


    public static void testSetup() {
        RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
        RestAssured.replaceFiltersWith(ImmutableList.of());
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.replaceFiltersWith(ResponseLoggingFilter.responseLogger(), new RequestLoggingFilter(LogDetail.ALL));
    }


    public boolean addPetToTheStore(Map<String, Object> fieldAttribute) {

        AddPet addPet = new AddPet();
        Category category = new Category();
        Tag tag = new Tag();
        List<String> photoUrlList = new ArrayList<>();
        List<Tag> tagList = new ArrayList<>();

        List<String> tagIdList = new ArrayList<>();
        List<String> tagNameList = new ArrayList<>();

        try {
            for (Map.Entry<String, Object> entry : fieldAttribute.entrySet()) {

                String key = entry.getKey();
                Object value = entry.getValue();

                if (key.equals("petId"))
                    addPet.setId(Integer.parseInt(value.toString()));
                if (key.equals("categoryId"))
                    category.setId(Integer.parseInt(value.toString()));
                if (key.equals("categoryName"))
                    category.setName(value.toString());
                if (key.equals("petName"))
                    addPet.setName(value.toString());
                if (key.equals("status"))
                    addPet.setStatus(value.toString());
                if (key.equals("photoUrl"))
                    if (value.toString().contains(",")) {
                        String valueInString = value.toString();
                        photoUrlList = Arrays.asList(valueInString.split(","));
                    } else {
                        photoUrlList.add(value.toString());
                    }
                if (key.equals("tagId"))
                    if (value.toString().contains(",")) {
                        String valueInString = value.toString();
                        tagIdList = Arrays.asList(valueInString.split(","));
                        LOGGER.info("Tag Id list is :: " + tagIdList);
                    } else {
                        tag.setId(Integer.parseInt(value.toString()));
                    }
                if (key.equals("tagName"))
                    if (value.toString().contains(",")) {
                        String valueInString = value.toString();
                        tagNameList = Arrays.asList(valueInString.split(","));
                        LOGGER.info("Tag Name list is :: " + tagNameList);

                    } else {
                        tag.setName(value.toString());
                    }
            }

            addPet.setPhotoUrls(photoUrlList);
            addPet.setCategory(category);

            if (tagIdList.size() > 0 && tagNameList.size() > 0) {
                for (int i = 0; i < tagIdList.size(); i++) {
                    Tag tag1 = new Tag();
                    tag1.setId(Integer.parseInt(tagIdList.get(i)));
                    tag1.setName(tagNameList.get(i));
                    tagList.add(tag1);
                }
            } else {
                tagList.add(tag);
            }

            addPet.setTags(tagList);

            testSetup();
            RestAssured.baseURI = baseUrl;
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");
            response = request.body(addPet).post("v2/pet");
            response.prettyPrint();
            LOGGER.info("Pet added successfully");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Exception while removing pet");
            return false;
        }
    }

    public boolean verifyResponseCode(int responseCode) {
        return response.getStatusCode() == responseCode;
    }

    public boolean removePetFromStore(String petId) {

        try {
            testSetup();
            AddPet addPet = new AddPet();
            RestAssured.baseURI = baseUrl;
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");
            response = request.delete("v2/pet/" + Integer.parseInt(petId));
            response.prettyPrint();
            LOGGER.info("Pet removed successfully");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Exception while removing pet");
            return false;
        }


    }

    public boolean orderPetFromTheStore(Map<String, Object> fieldAttribute) {

        OrderPet orderPet = new OrderPet();

        try {
            for (Map.Entry<String, Object> entry : fieldAttribute.entrySet()) {

                String key = entry.getKey();
                Object value = entry.getValue();

                if (key.equals("orderId"))
                    orderPet.setId(Integer.parseInt(value.toString()));
                if (key.equals("petId"))
                    orderPet.setPetId(Integer.parseInt(value.toString()));
                if (key.equals("quantity"))
                    orderPet.setQuantity(Integer.parseInt(value.toString()));
                if (key.equals("shipDate"))
                    orderPet.setShipDate(value.toString());
                if (key.equals("status"))
                    orderPet.setStatus(value.toString());
                if (key.equals("complete"))
                    orderPet.setComplete(Boolean.parseBoolean(value.toString()));
            }

            testSetup();
            RestAssured.baseURI = baseUrl;
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");
            response = request.body(orderPet).post("v2/store/order");
            response.prettyPrint();
            LOGGER.info("Pet ordered successfully");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Error while Ordering pet");
            return false;
        }
    }

    public OrderPet getPetOrderDetails() {
        try {
            OrderPet orderPet = new OrderPet();
            orderPet = response.as(OrderPet.class);
            LOGGER.info("Order details :: " + orderPet.toString());
            return orderPet;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Error while getting response of Pet Order details");
            return null;
        }

    }
}
