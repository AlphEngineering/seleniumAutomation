package api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class DeleteRequest {
    public String urlDelUsers = "https://fakerestapi.azurewebsites.net/api/v1/Users/9";

    @Test
    public void validateDeleteUser() {

        Response res = given()
                .when()
                .delete(urlDelUsers)
                .then()
                .statusCode(200)
                .log().all().extract().response(); //Response Method used
        String response = res.asString();
        System.out.println("API record deleted: "+ response.isEmpty());
    }
}

