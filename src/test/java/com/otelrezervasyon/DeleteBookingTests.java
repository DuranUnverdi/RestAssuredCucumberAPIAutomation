package com.otelrezervasyon;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteBookingTests extends BaseTest{
    @Test
    public void deleteBookingTest(){

        Response res=given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token="+createToken())
                .when()
                .delete("https://restful-booker.herokuapp.com/booking/"+createBookingId());
        res.prettyPrint();
        res.then()
                .statusCode(201);


    }
}
