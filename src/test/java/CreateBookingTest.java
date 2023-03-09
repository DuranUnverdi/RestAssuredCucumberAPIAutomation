import io.restassured.http.ContentType;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateBookingTest {
    @Test
    public void createBookingTest(){

        //Body oluşturuldu
        JSONObject body=new JSONObject();
        body.put("firstname","Duran");
        body.put("lastname","Unverdi");
        body.put("totalprice",200);
        body.put("depositpaid",true);

        //Obje içinde obje içeren bir alan olduğu için date alanını içeren ayrı bir obje oluşturuldu
        JSONObject bookingDate=new JSONObject();
        bookingDate.put("checkin","2018-01-01");
        bookingDate.put("checkout","2019-01-01");

        body.put("bookingdates",bookingDate);
        body.put("additionalneeds","Evcil hayvan kabul edilen oda");

       Response res= given()
                .when()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .post("https://restful-booker.herokuapp.com/booking");
        res.prettyPrint();
        res
                .then()
                .statusCode(200);
        Assertions.assertEquals("Duran",res.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Unverdi",res.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(200,(Integer) res.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true,res.jsonPath().getJsonObject("booking.depositpaid"));
    }
}
