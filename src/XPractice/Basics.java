package XPractice;

import Files.ReusableMethods;
import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {

    public static void main(String[] args) throws IOException {
//
//        given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
//               .body(payload.AddPlace())
//
//       .when().post("maps/api/place/add/json")
//
//       .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).header("server","Apache/2.4.18 (Ubuntu)");


        // Add Place --> Update Place with new Address

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
        .body(Files.readAllBytes(Paths.get("")))
        .when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();

        System.out.println(response);
        //Parse JSON class to get the Place id
//        JsonPath js = new JsonPath(response); // Values obtained from Response are in JSON and we need to Parse it to get the values
//        String place_id = js.getString("place_id");
//        System.out.println(place_id);
        JsonPath js2 = ReusableMethods.rawToJson(response);
        String place_id = js2.getString("place_id");
        System.out.println(place_id);

        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");


////        Update Place()

        String new_address = "Summer walk Africa 45000";

        given().log().all().queryParam("key", "qaclick123").header("Content-type", "application/json")
                .body("{\n" +
                        "\n" +
                        "\"place_id\":\"" + place_id + "\",\n" +
                        "\"name\": \"New Name\",\n" +
                        "\"address\":\"" + new_address + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "\n" +
                        "}")
                .when().put("maps/api/place/update/json")

                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));


        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("--*/--------------------------------------------------------------------------------------------------------------------------------------------------------");

    }
}
////        //GET Place API
////
//        String getPlaceResponse= given().log().all().queryParam("key","qaclick123").queryParam("place_id",place_id)
//        .when().get("maps/api/place/get/json")
//        .then().assertThat().log().all().statusCode(300).extract().response().asString();
//
//        System.out.println(getPlaceResponse);
//
//
//        JsonPath js2= ReusableMethods.rawToJson(getPlaceResponse);
//        String currentAddress= js2.getString("address");
//        System.out.println(currentAddress);
//
//
//        System.out.println("------------------------");
//        System.out.println("Current address:  "+currentAddress +"  Updated Address: " +new_address);
//        System.out.println("------------------------");
//
//        Assert.assertEquals(currentAddress,new_address);
//        //Cucumber , JUNIT, TestNG
//                }
//                }
////
////
////
////
////    }
////}
