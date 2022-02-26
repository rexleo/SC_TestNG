package MyTests;

import org.testng.Assert;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class API_Add_Pet{
    String bodOwnerName = "Busani";
    String bodPetName = "Scruffles";
    String respOwnerName;
    String respPetName;

    @Test(priority = 1)
    public void testAddPet() {

        SoftAssert softAssert = new SoftAssert();

        baseURI=("https://petstore.swagger.io/v2/pet");
        String response =
        given().relaxedHTTPSValidation().
                header("accept","application/json").
                header("Content-Type", "application/json").
                body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \""+bodOwnerName+"\"\n" +
                        "  },\n" +
                        "  \"name\": \""+bodPetName+"\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}").
                when().post().then().log().all().extract().response().asString();

        JsonPath jso = new JsonPath(response);
        respOwnerName = jso.getString("category.name");
        System.out.println("Response Owner Name: "+respOwnerName);
        respPetName = jso.getString("name");
        System.out.println("Response Pet Name: "+respPetName);
    }

    @Test(priority = 2)
    public void checkOwnerName(){
        Assert.assertEquals(bodOwnerName,respOwnerName,"Owner name is: "+respOwnerName);
    }

    @Test(priority = 3)
    public void checkPetName(){
        Assert.assertEquals(bodPetName,respPetName,"Pet name is: "+respPetName);
    }

    @Test(priority = 4)
    public void checkFailedPetName(){
        Assert.assertEquals("Fail test","negative", "This is meant to fail");
    }
}
