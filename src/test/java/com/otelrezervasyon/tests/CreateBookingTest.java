package com.otelrezervasyon.tests;

import com.otelrezervasyon.models.Booking;
import com.otelrezervasyon.models.BookingDates;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;

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
    @Test
    public void createBookingWithPojo(){
        BookingDates bookingDates=new BookingDates("2023-03-01","2023-03-05");
        Booking booking=new Booking("Udemy","Kurs",500,false,bookingDates,"Sigara içilebilir bir oda");

        Response response=given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");
        response
                .then().statusCode(200);
    }
}
