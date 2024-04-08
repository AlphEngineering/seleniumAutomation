package api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutRequest {

    public String urlPutUsers = "https://fakerestapi.azurewebsites.net/api/v1/Users/1";
    public String contentType = "application/json";
    //public static Map payload = null; //Static variable of null Map
    //public HashMap payload = null; //Instance variable of null HashMap

    @Test
    public void validatePutUpdateUser() {

        HashMap payload = new HashMap(); //Local variable of Hash Map used to create payload
        payload.put("id", "1");
        payload.put("userName", "Alphae");
        payload.put("password", "n0!tAm0TUa");

        Response res = given().contentType(contentType).body(payload)
                .when()
                .put(urlPutUsers)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("id", equalTo(1))
                .log().status().extract().response(); //Logs status code & response code.
        // Alternate confirmation using Response and Assertion code.
        String response =  res.asString();
        Assert.assertTrue(response.contains("n0!tAm0TUa"));
        System.out.println("Record updated: "+response.contains("n0!tAm0TUa"));
    }
}
