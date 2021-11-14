package stepDefinations;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pojo.MovieContent;
import pojo.UpcomingMoviesPOJO;
import resources.APIResources;

import resources.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class FetchMovieStepDefination extends Utils {

    RequestSpecification reqSpec;
    ResponseSpecification resSpec;
    UpcomingMoviesPOJO up;
    Response response;

    @Given("^Upcoming movies api is given$")
    public void upcoming_movies_api_is_given() throws IOException {
        resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
        reqSpec = given().spec(requestSpecification());

    }

    @When("^User calls \"([^\"]*)\" API with \"([^\"]*)\" http request$")
    public void user_calls_API_with_http_request(String GetUpcomingMovies, String httpMethod) {
        APIResources apiResources = APIResources.valueOf(GetUpcomingMovies);
        if (httpMethod.equalsIgnoreCase("GET")) {
            up = reqSpec.when().get(apiResources.getResources()).then().spec(resSpec).extract().response()
                    .as(UpcomingMoviesPOJO.class);
            response = reqSpec.when().get(apiResources.getResources()).then().spec(resSpec).extract().response();
        }
    }

    @Then("^Status code should  (.+)$")
    public void status_code_should(Integer expectedStatusCode) {
        Integer actualStatusCode = response.getStatusCode();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("^Movie release date should not be greater than today's date$")
    public void movie_release_date_should_not_be_greater_than_today_s_date() throws ParseException {
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<MovieContent> mc = up.getUpcomingMovieData();
        String s = sdf.format(cal.getTime());
        Date currentDate = sdf.parse(s);
        System.out.println(currentDate);
        for (int i = 0; i < mc.size(); i++) {

            String mString = mc.get(i).getReleaseDate();
            if (mString != null) {
                Date movieDate = sdf.parse(mString);

                if (movieDate.compareTo(currentDate) == 1) {
                    continue;
                } else {
                    assertTrue(false);
                    break;
                }
            }

        }

    }

    @Then("^Movie posted URL should only have \"([^\"]*)\" format$")
    public void movie_posted_URL_should_only_have_format(String string) {

        List<MovieContent> listURL = up.getUpcomingMovieData();

        boolean IsURLContainsJPG;
        for (int i = 0; i < listURL.size(); i++) {
            String URL = listURL.get(i).getMoviePosterUrl();
            System.out.println(listURL.get(i).getMoviePosterUrl());
            if (URL.contains(".jpg") || URL.contains(".png")) {
                IsURLContainsJPG = true;
                assertTrue(IsURLContainsJPG);
            } else {
                IsURLContainsJPG = false;
                assertTrue(IsURLContainsJPG);
            }
        }

    }

    @Then("^Paytm movie code is unique$")
    public void paytm_movie_code_is_unique() {

        boolean isItDuplicate;
        List<MovieContent> paytmCode = up.getUpcomingMovieData();
        for (int i = 0; i < paytmCode.size(); i++) {
            for (int j = i + 1; j < paytmCode.size() - 1; j++) {
                String s1 = paytmCode.get(i).getPaytmMovieCode();
                String s2 = paytmCode.get(j).getPaytmMovieCode();

                if (s1.equals(s2)) {
                    isItDuplicate = true;
                    System.out.println(paytmCode.get(i).getMovie_name() + " And " + paytmCode.get(j).getMovie_name()
                            + "Contain Duplicate Code");
                    assertFalse(isItDuplicate);
                    break;

                } else {
                    isItDuplicate = false;
                    assertFalse(isItDuplicate);

                }

            }

        }

    }

    @Then("^No movie code should have more than 1 language format$")
    public void no_movie_code_should_have_more_than_language_format() {

    }

    @Then("^Write down name of the all the movies whose content available is 0$")
    public void write_down_name_of_the_all_the_movies_whose_content_available_is() throws IOException {

        try {
            List<MovieContent> contentAvailable = up.getUpcomingMovieData();
            File fs = new File("src\\test\\java\\resources\\excelData\\MovieNameList.xlsx");
            FileInputStream fi = new FileInputStream(fs.getAbsolutePath());

            XSSFWorkbook wb = new XSSFWorkbook(fi);
            XSSFSheet sheet = wb.getSheet("MovieNameList");
            Row row;
            Cell cell = null;
            int temporaryVariable = 1;
            for (int i = 0; i < contentAvailable.size(); i++) {
                int contentAvailableValue = contentAvailable.get(i).getIsContentAvailable();
                if (contentAvailableValue < 1) {
                    String setData = contentAvailable.get(i).getMovie_name();
                    row = sheet.createRow(temporaryVariable);
                    cell = row.createCell(0);
                    cell.setCellValue(setData);
                    temporaryVariable++;

                }
            }
            fi.close();
            FileOutputStream fo = new FileOutputStream(fs.getAbsolutePath());
            wb.write(fo);
            fo.flush();
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}