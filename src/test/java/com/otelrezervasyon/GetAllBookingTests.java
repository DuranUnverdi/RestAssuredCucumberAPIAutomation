package com.otelrezervasyon;

import org.junit.jupiter.api.Test;

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
}
