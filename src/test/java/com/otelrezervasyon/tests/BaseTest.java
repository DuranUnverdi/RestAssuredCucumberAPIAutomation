package com.otelrezervasyon.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class BaseTest {

    RequestSpecification spec;

    //Base url oluşturduk ve request response için Loglama yaptık
    //Before each ile test koşumları öncesinde bir defa koşacak
    @BeforeEach
    public void setup(){
      spec=new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()))
                .build();
    }

    protected Response createBooking(){
        Response res= given(spec)
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject("Duran","Unverdi",200,true))
                .post("/booking");
        res
                .then()
                .statusCode(200);

        return res;
    }
    protected String bookingObject(String firstName,String lastName,int totalPrice,boolean depositPaid){
        JSONObject body=new JSONObject();
        body.put("firstname",firstName);
        body.put("lastname",lastName);
        body.put("totalprice",totalPrice);
        body.put("depositpaid",depositPaid);

        //Obje içinde obje içeren bir alan olduğu için date alanını içeren ayrı bir obje oluşturuldu
        JSONObject bookingDate=new JSONObject();
        bookingDate.put("checkin","2018-01-01");
        bookingDate.put("checkout","2019-01-01");

        body.put("bookingdates",bookingDate);
        body.put("additionalneeds","Evcil hayvan kabul edilen oda");

        return body.toString();
    }
    protected String createToken(){
        JSONObject body=new JSONObject();
        body.put("username","admin");
        body.put("password","password123");

        Response res= given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(body.toString())
                .post("/auth");


        return res.jsonPath().getJsonObject("token");
    }
    protected int createBookingId(){
        Response response=createBooking();
        return response.jsonPath().getJsonObject("bookingid");
    }
}
