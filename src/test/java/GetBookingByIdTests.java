import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingByIdTests {

    @Test
    public void getBookingById(){
      Response res=  given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/602");
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
        Assertions.assertEquals("Josh",firstName);
        Assertions.assertEquals("Allen",lastName);
        Assertions.assertEquals(111,totalPrice);

    }
}
