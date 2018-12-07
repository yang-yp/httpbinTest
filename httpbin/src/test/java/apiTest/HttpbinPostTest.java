package apiTest;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.jayway.restassured.RestAssured.given;

@FixMethodOrder(value = MethodSorters.DEFAULT)
public class HttpbinPostTest {
    @Test
    public void httpPost() {
        Response res = given().when().post("http://httpbin.org/post");
        JsonPath jsonPath = new JsonPath(res.asString());
        System.out.println(res.asString());

        Assert.assertEquals("http://httpbin.org/post",jsonPath.get("url"));
    }
}
