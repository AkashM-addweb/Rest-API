package LibraryAPI;

import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Files.payload;
import static io.restassured.RestAssured.given;

public class LibraryAPI {

    @Test(dataProvider = "booksData")
    public void addBook(String isbn, String aisle){

//        RestAssured.baseURI="";
        RestAssured.baseURI="http://216.10.245.166";
//        Response response=given().log().all().header("Content-type","application/json").
        String response=given().log().all().header("Content-type","application/json").
                body(payload.AddBook(isbn,aisle))
//                .when().post("/api/users")
                .when().post("Library/Addbook.php")
                .then().assertThat().statusCode(404).extract().response().asString();

        JsonPath js= ReusableMethods.rawToJson(response); // When using response the asString() functionality will not do.
        System.out.println("JSON path ==> js = " + js);
//        JsonPath js=new JsonPath(response);
        String id= js.get("ID");
        System.out.println("ID = " + id);
    }

    @Test(dataProvider = "booksData")
    public void deleteBook(String isbn, String aisle) {


    }




    @DataProvider(name = "booksData")
    public Object[][] getData(){
        return new Object[][] {{"mnop","009988"},{"uvwx","22334455"},{"jklm","77889900"}};
    }

}
