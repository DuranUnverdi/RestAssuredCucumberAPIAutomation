import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetAllBookingTests {
    @Test
    public void getAllBookingTest(){
        //Get Çağrısı
        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                //yapılan çağrının sonucunu yazdırmak için kulllanıyoruz
                .log().all()
                .statusCode(200);

    }
}
