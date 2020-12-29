package BaseTests;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import org.junit.Test;

public class Countries {

    @Test
    public void getCountryDetail() {

        RestAssured.baseURI = "http://restcountries.eu/rest/v1/name/";

        RequestSpecification request = RestAssured

                .given()
                .config(RestAssured.config().sslConfig(new SSLConfig().allowAllHostnames()));

        Response response = request

                .when()
                .get("Turkey")

                .then()
                .statusCode(200)
                .extract().response().prettyPeek();

        int responseCode = response.getStatusCode();
        ResponseBody responseBody = response.getBody();

        String name = response.jsonPath().getString("name");
        String alpha2Code = response.jsonPath().getString("alpha2Code");
        String callingCodes = response.jsonPath().getString("callingCodes");

        System.out.println("---------------------");
        System.out.println(responseCode);

        System.out.println("---------------------");
        System.out.println(name);
        System.out.println(alpha2Code);
        System.out.println(callingCodes);

        System.out.println(responseBody.asString());



        assert responseBody.asString() != null;

    }
}
