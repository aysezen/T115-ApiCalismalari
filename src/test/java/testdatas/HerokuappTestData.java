package testdatas;

import org.json.JSONObject;

public class HerokuappTestData {


    public JSONObject bookingDatesOlusturJson(){

        JSONObject bookingdates = new JSONObject();

        bookingdates.put( "checkin" , "2021-06-01");
        bookingdates.put( "checkout" , "2021-06-10");

        return bookingdates;
    }

    public JSONObject bookingOlusturJson(){

        JSONObject reqBody = new JSONObject();

        reqBody.put("firstname" , "Ali");
        reqBody.put("lastname" , "Bak");
        reqBody.put("totalprice" , 500);
        reqBody.put("depositpaid" , false);
        reqBody.put("bookingdates" , bookingDatesOlusturJson());
        reqBody.put("additionalneeds" , "Breakfast");

        return reqBody;
    }

    /*
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
    public JSONObject expDataJson(){

        JSONObject expData = new JSONObject();

        expData.put("bookingid", 1);
        expData.put("booking", bookingOlusturJson());

        return expData;
    }
}
