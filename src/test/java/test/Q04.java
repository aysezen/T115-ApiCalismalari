package test;

import baseUrl.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testdatas.HerokuappTestData;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q04 extends HerokuappBaseUrl {

    /*
        Class icinde 2 Test metodu olusturun ve asagidaki testleri yapin
    */


    @Test

    public void get01(){
    /*
        1-  https://restful-booker.herokuapp.com/booking endpointine bir GET
            request gonderdigimizde donen response’un status code’unun 200 oldugunu
            ve Response’ta 1 id'sine sahip bir booking oldugunu test edin
    */
        // 1 - Url ve body hazirligi

        specHerokuapp.pathParam("ahmet","booking");

        // 2 - Expected Data Hazirligi

        // 3 - Response'i kaydet

        Response response = given().spec(specHerokuapp).when().get("/{ahmet}");

        response.prettyPrint();

        // 4 - Assertion

        response.then().assertThat().statusCode(200).body("bookingid", Matchers.hasItem(1));


    }

    @Test

    public void get02(){
    /*
        2- https://restful-booker.herokuapp.com/booking endpointine asagidaki
         body’ye sahip bir POST request gonderdigimizde donen response’un status
         code’unun 200 oldugunu ve donen response'in asagidaki gibi oldugunu test edin

      {
      "firstname" : "Ali",
      "lastname" : “Bak",
      "totalprice" : 500,
      "depositpaid" : false,
      "bookingdates" : {
                    "checkin" : "2021-06-01",
                    "checkout" : "2021-06-10"
                    },
      "additionalneeds" : "wi-fi"
       }

       Response Body :
        {
            "bookingid": 1,
            "booking": {
                  "firstname" : "Ali",
                  "lastname" : “Bak",
                  "totalprice" : 500,
                  "depositpaid" : false,
                  "bookingdates" : {
                                "checkin" : "2021-06-01",
                                "checkout" : "2021-06-10"
                                },
                  "additionalneeds" : "wi-fi"
                         }
        }
         */

        // 1 - URL ve Body hazirligi

        specHerokuapp.pathParam("pp1","booking");

        HerokuappTestData reqBody = new HerokuappTestData();

        JSONObject requestBody = reqBody.bookingOlusturJson();

        // 2 - Expected Data hazirla

        JSONObject expectedBody=reqBody.expDataJson();

        // 3 - Response'i kaydet

        Response response = given()
                                .spec(specHerokuapp)
                                .contentType(ContentType.JSON)
                            .when()
                                .body(requestBody.toString())
                                .post("/{pp1}");

        // response.prettyPrint();

        // 4 - Assertion

        JsonPath resJP = response.jsonPath();

        assertEquals( 200 , response.getStatusCode()  );
        assertEquals( expectedBody.getJSONObject("booking").get("firstname") , resJP.get("booking.firstname")  );
        assertEquals( expectedBody.getJSONObject("booking").get("lastname") , resJP.get("booking.lastname")  );
        assertEquals( expectedBody.getJSONObject("booking").get("totalprice") , resJP.get("booking.totalprice")  );
        assertEquals( expectedBody.getJSONObject("booking").get("depositpaid") , resJP.get("booking.depositpaid")  );
        assertEquals( expectedBody.getJSONObject("booking").get("additionalneeds") , resJP.get("booking.additionalneeds")  );
        assertEquals( expectedBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin") ,
                      resJP.get("booking.bookingdates.checkin")  );
        assertEquals( expectedBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout") ,
                resJP.get("booking.bookingdates.checkout")  );

    }

}
