package com.otelrezervasyon;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingByIdTests extends BaseTest{

    @Test
    public void getBookingById(){

      //Yeni kayıt oluşturduk
      Response newBooking=createBooking();

      //Yeni oluşturulan kaydın idsini aldık
      int reservationId=newBooking.jsonPath().getJsonObject("bookingid");

      Response res=  given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/"+reservationId);
      res
              .then()
              .statusCode(200);

      //ekrana basıyoruz
      res.prettyPrint();

      //Jsonda obje içindeki firstname,lastname,totalprice alanındaki değerleri alıyoruz
      String firstName=res.jsonPath().getJsonObject("firstname");
      String lastName=res.jsonPath().getJsonObject("lastname");
      int totalPrice=res.jsonPath().getJsonObject("totalprice");


      //Aldığımız değeri karşılaştırıyoruz
      Assertions.assertEquals("Duran",firstName);
      Assertions.assertEquals("Unverdi",lastName);
      Assertions.assertEquals(200,totalPrice);

    }
}
