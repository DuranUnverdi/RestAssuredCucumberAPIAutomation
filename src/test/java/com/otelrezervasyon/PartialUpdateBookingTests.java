package com.otelrezervasyon;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PartialUpdateBookingTests extends BaseTest{

    @Test
    public void partialUpdateBookingTest(){

        //Çağrıyı yap
        JSONObject body=new JSONObject();
        body.put("firstname","Mehmet");
        Response response =given()
                .contentType(ContentType.JSON)
                .header("Cookie","token="+createToken())
                .body(body.toString())
                .when()
                .patch("https://restful-booker.herokuapp.com/booking/"+createBookingId() );
        response.prettyPrint();

        //Assertion testleri

        Assertions.assertEquals("Mehmet",response.jsonPath().getJsonObject("firstname"));


    }
}
