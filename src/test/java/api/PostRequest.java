package api;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest {

    public String urlPostUsers = "https://fakerestapi.azurewebsites.net/api/v1/Users";
    public String contentType = "application/json";
    //public static Map payload = null; //Static variable of null Map
    //public HashMap payload = null; //Instance variable of null HashMap

    @Test
    public void validatePostNewUser(){

        HashMap payload = new HashMap(); //Local variable of Hash Map used to create payload
        payload.put("id", "1");
        payload.put("userName", "Alphae");
        payload.put("password", "aUT0mAt!0n");
        given().contentType(contentType).body(payload)
                .when()
                .post(urlPostUsers)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("id", equalTo(1)).log().status(); //Logs body & status code. To log all data use: .log().all()

    }
}
