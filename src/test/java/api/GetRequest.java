package api;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static java.lang.Math.log;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest {
    public String urlGetUsers = "https://fakerestapi.azurewebsites.net/api/v1/Users";
    public String urlGetAuthorsId = "https://fakerestapi.azurewebsites.net/api/v1/Authors/300";

    @Test (priority = 1)
    public void validateGetUsers(){
        given()
                .when()
                .get(urlGetUsers)
                .then()
                .statusCode(200)
                .log().status(); //Logs status code. To log everything use: .log().all()
    }

    @Test (priority = 2)
    public void validateGetUsersList(){
        given()
                .when()
                .get(urlGetUsers)
                .then()
                .statusCode(200)
                .log().body() //Logs the entire body. (Alternate) .body(true); //Logs the entire body
                .log().status(); //logs the status code.

    }

    @Test (priority = 3)
    public void validateGetAuthorsById(){
        given()
                .when()
                .get(urlGetAuthorsId)
                .then()
                .statusCode(200)
                .log()
                .body()
                .body("id", equalTo(300)) //Display details for Author ID 300
                .log().status(); //Logs status code
    }





}
