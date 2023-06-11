package com.otelrezervasyon.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTest extends BaseTest{

    @Test
    public void updateBookingTest(){


      //Request yap
      Response response=given(spec)
              .contentType(ContentType.JSON)
              .header("Cookie","token="+createToken())
              .body(bookingObject("Ayşe","Fatma",500,true))
              .put("/booking/"+createBookingId());


      //Assertion Testler
      String firstName=response.jsonPath().getJsonObject("firstname");
      String lastName=response.jsonPath().getJsonObject("lastname");
      int totalPrice=response.jsonPath().getJsonObject("totalprice");
      boolean depositPaid=response.jsonPath().getJsonObject("depositpaid");
      Assertions.assertEquals("Ayşe",firstName);
      Assertions.assertEquals("Fatma",lastName);
      Assertions.assertEquals(500,totalPrice);
      Assertions.assertEquals(true,depositPaid);
    }


}
