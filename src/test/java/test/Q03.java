package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class Q03 {

    /*
    {
    "firstName": "John",
    "lastName": "doe",
    "age": 26,
    "address":      {
                    "streetAddress": "naist street",
                    "city": "Nara",
                    "postalCode": "630-0192"
                    },
    "phoneNumbers": [
                        {
                            "type": "iPhone",
                            "number": "0123-4567-8888"
                        },
                        {
                            "type": "home",
                            "number": "0123-4567-8910"
                        }
                    ]
    }
     */


    @Test
    public void jsonObject01(){

        JSONObject cepTel = new JSONObject();

            cepTel.put("type", "iPhone");
            cepTel.put("number", "0123-4567-8888");


        JSONObject evTel = new JSONObject();

            evTel.put("type", "home");
            evTel.put("number", "0123-4567-8910");


        JSONArray phoneNumbers = new JSONArray();

            phoneNumbers.put(0,cepTel);
            phoneNumbers.put(1,evTel);


        /*
                    {
                    "streetAddress": "naist street",
                    "city": "Nara",
                    "postalCode": "630-0192"
                    }
         */

        JSONObject address = new JSONObject();

        address.put("streetAddress", "naist street");
        address.put("city", "Nara");
        address.put("postalCode", "630-0192");


        JSONObject kisiBilgileri = new JSONObject();

        kisiBilgileri.put("firstName", "John");
        kisiBilgileri.put("lastName", "doe");
        kisiBilgileri.put("age", 26);
        kisiBilgileri.put("address", address);
        kisiBilgileri.put("phoneNumbers", phoneNumbers);


        System.out.println(kisiBilgileri.get("firstName"));

        System.out.println(kisiBilgileri.get("age"));

        System.out.println(kisiBilgileri.getJSONObject("address").get("streetAddress"));

        System.out.println(kisiBilgileri.getJSONArray("phoneNumbers").getJSONObject(0).get("type"));

        System.out.println(kisiBilgileri.getJSONArray("phoneNumbers").getJSONObject(1).get("number"));

    }

}
