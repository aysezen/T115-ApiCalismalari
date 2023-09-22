package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q02 {

    @Test
    public void post01(){

        /*
        {
            "firstname" : "Jim",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }
         */

        // 1 - URL ve Request body hazirligi

            String url= "https://restful-booker.herokuapp.com/booking";

            JSONObject bookingdates = new JSONObject();

            bookingdates.put( "checkin" , "2018-01-01");
            bookingdates.put( "checkout" , "2019-01-01");

            JSONObject reqBody = new JSONObject();

            reqBody.put("firstname" , "Jim");
            reqBody.put("lastname" , "Brown");
            reqBody.put("totalprice" , 111);
            reqBody.put("depositpaid" , true);
            reqBody.put("bookingdates" , bookingdates);
            reqBody.put("additionalneeds" , "Breakfast");



        // 2 - Expected Data hazirligi

        /*
        {
            "bookingid": 1,
            "booking": {
                "firstname": "Jim",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                                },
                "additionalneeds": "Breakfast"
                         }
        }
         */

        JSONObject expData = new JSONObject();

        expData.put("bookingid", 1);
        expData.put("booking", reqBody);

        // 3 - Response' i kaydet

        Response response = given()
                                .contentType(ContentType.JSON)
                            .when()
                                .body(reqBody.toString())
                                .post(url);

        response.prettyPrint();

        // 4 - Assertion

        // Donen Response body'sinin icerisindeki degerlere ulasabilmek icin restassured
        // kutuphanesinin bize sunmus oldugu JsonPath methodunu kullanmak zorundayiz.
        JsonPath resJP = response.jsonPath();

        assertEquals( 200 , response.getStatusCode() );
        assertEquals( expData.getJSONObject("booking").get("firstname")  , resJP.get("booking.firstname")   );

        // Expected Datayi JSONOBJECT olarak hazirladik. Nested JSONOBJECT'te herhangi bir key
        // degerine ulasmak icin her katmanda bir method call ederiz.
        // Donen Response Body'mizi ise ancak JsonPath formatina donusterebildigimizi yukarida
        // ifade ettik. JsonPath'de ise istedigimiz key degerine araqlara nokta koyarak tek bir
        // get methodu ile erisebiliyoruz.

        assertEquals(  expData.getJSONObject("booking").get("lastname")     , resJP.get("booking.lastname")   );
        assertEquals(  expData.getJSONObject("booking").get("totalprice")     , resJP.get("booking.totalprice")   );
        assertEquals(  expData.getJSONObject("booking").get("depositpaid")     , resJP.get("booking.depositpaid")   );
        assertEquals(  expData.getJSONObject("booking").get("additionalneeds")     , resJP.get("booking.additionalneeds")   );

        assertEquals(  expData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin")  ,
                       resJP.get("booking.bookingdates.checkin"));
        assertEquals(  expData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout")  ,
                resJP.get("booking.bookingdates.checkout"));

    }

}
