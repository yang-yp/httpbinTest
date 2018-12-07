package apiTest;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.jayway.restassured.RestAssured.given;

@FixMethodOrder(value = MethodSorters.DEFAULT)
public class HttpbinPutTest {
    @Test
    public void httpPut() {
        Response res = given().when().put("http://httpbin.org/put");
        JsonPath jsonPath = new JsonPath(res.asString());
        System.out.println(res.asString());

        Assert.assertEquals("http://httpbin.org/put",jsonPath.get("url"));
    }
}
