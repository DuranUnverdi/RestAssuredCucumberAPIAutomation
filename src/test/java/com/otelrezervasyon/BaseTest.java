package com.otelrezervasyon;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected Response createBooking(){
        Response res= given()
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject("Duran","Unverdi",200,true))
                .post("https://restful-booker.herokuapp.com/booking");
        res.prettyPrint();
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

        Response res= given()
                .contentType(ContentType.JSON)
                .when()
                .body(body.toString())
                .log().all()
                .post("https://restful-booker.herokuapp.com/auth");

        res.prettyPrint();

        return res.jsonPath().getJsonObject("token");
    }
}
