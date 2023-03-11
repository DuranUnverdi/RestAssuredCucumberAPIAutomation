package com.otelrezervasyon;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateBookingTest extends BaseTest{
    @Test
    public void createBookingTest(){

        Response res=createBooking();
        Assertions.assertEquals("Duran",res.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Unverdi",res.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(200,(Integer) res.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true,res.jsonPath().getJsonObject("booking.depositpaid"));
    }
}
