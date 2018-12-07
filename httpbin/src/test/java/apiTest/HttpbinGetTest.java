package apiTest;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static com.jayway.restassured.RestAssured.given;

@FixMethodOrder(value = MethodSorters.DEFAULT)
public class HttpbinGetTest {
    @Test
    public void httpGet() {
        Response res = given().when().get("http://httpbin.org/get");
        JsonPath jsonPath = new JsonPath(res.asString());
        System.out.println(res.asString());

        Assert.assertEquals("httpbin.org",jsonPath.get("headers.Host"));
    }
}
