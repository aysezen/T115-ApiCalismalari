package test;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Q01 {

    @Test
    public void get01(){

        // 1 - URL ve Body hazirligi

        String url = "https://restful-booker.herokuapp.com/booking";

        // 2 - Expected Data hazirligi

        // 3 - Response'i kaydet

        Response response = given().when().get(url);

        // 4 - Assertion

        response.prettyPrint();

        System.out.println("Status Code :" + response.getStatusCode());

        response.then().assertThat().statusCode(200);


    }











}
