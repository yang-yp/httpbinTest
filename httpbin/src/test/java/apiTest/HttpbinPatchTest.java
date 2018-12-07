package apiTest;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.jayway.restassured.RestAssured.given;

@FixMethodOrder(value = MethodSorters.DEFAULT)
public class HttpbinPatchTest {
    @Test
    public void httpPatch() {
        Response res = given().when().patch("https://httpbin.org/patch");
        JsonPath jsonPath = new JsonPath(res.asString());
        System.out.println(res.asString());

        Assert.assertEquals("https://httpbin.org/patch",jsonPath.get("url"));
    }
}
