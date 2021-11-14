package resources;

import java.io.*;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

    public static RequestSpecification rs;

    public RequestSpecification requestSpecification() throws IOException {

        if (rs == null) {

            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            if ((getGlobalValues("baseUrl").equalsIgnoreCase("PaytmTest"))) {
                rs = new RequestSpecBuilder().setBaseUri(getGlobalValues("PaytmTest")).addQueryParam("key", "qaclick123")
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
            } else if ((getGlobalValues("baseUrl").equalsIgnoreCase("RahulShetty"))) {
                rs = new RequestSpecBuilder().setBaseUri(getGlobalValues("RahulShetty")).addQueryParam("key", "qaclick123")
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
            }
        }

        return rs;
    }

    public static String getGlobalValues(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(new File("src\\test\\java\\resources\\global.properties").getAbsolutePath());
        prop.load(fis);
        return prop.getProperty(key);

    }

    public String getJsonPath(Response response, String key) {

        String respo = response.asString();
        JsonPath js = new JsonPath(respo);

        return js.get(key);

    }

}
