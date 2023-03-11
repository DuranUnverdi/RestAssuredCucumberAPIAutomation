package com.otelrezervasyon;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTest extends BaseTest{

    @Test
    public void updateBookingTest(){
      //Token oluşturduk
      String token= createToken();

      //Response oluştur
      Response creatBookingObject=createBooking();
      int bookingId= creatBookingObject.jsonPath().getJsonObject("bookingid");

      //Request yap
      Response response=given()
              .contentType(ContentType.JSON)
              .header("Cookie","token="+token)
              .body(bookingObject("Ayşe","Fatma",500,true))
              .put("https://restful-booker.herokuapp.com/booking/"+bookingId);

      response.prettyPrint();

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
