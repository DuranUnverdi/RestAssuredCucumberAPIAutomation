package com.otelrezervasyon;

import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.geom.RectangularShape;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllBookingTests extends BaseTest{
    @Test
    public void getAllBookingTest(){
        //Get Çağrısı
        given(spec)
                .when()
                .get("/booking")
                .then()
                //yapılan çağrının sonucunu yazdırmak için kulllanıyoruz
                .statusCode(200);

    }
    @Test
    public void getBooking_with_firstname_filter(){
        //Yeni rezervasyon oluştur
        int bookingId=createBookingId();
        //Query paramatresi ekle
        spec.queryParam("firstname","Duran");
        spec.queryParam("lastname","Unverdi");
        //Çağrıyı gerçekleştir
        Response response=given(spec)
                .when()
                .get("/booking");
        //Assertion Test
        response
                .then()
                .statusCode(200);
        List<Integer> filtreleRez=response.jsonPath().getList("bookingid");
        Assertions.assertTrue(filtreleRez.contains(bookingId));

    }
}
