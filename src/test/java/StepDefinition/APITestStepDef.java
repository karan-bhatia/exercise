package test.java.StepDefinition;

import cucumber.api.java.en.Given;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APITestStepDef {

    private float latitude;
    private float longitude;
    private String url = "http://api.citybik.es/v2/networks";

    @Given("^As a user I want to verify that the city \"([^\"]*)\" is in \"([^\"]*)\" and return their corresponded latitude and longitude$")
    public void as_a_user_I_want_to_verify_that_the_city_is_in_and_return_their_corresponded_latitude_and_longitude(String arg1, String arg2) throws Throwable {
        try {
            Response response = given().when().get(url);
            Assert.assertEquals(200, response.getStatusCode());
            String countryCode = getCountryCode(arg2);
            List<Map<String, Map<String, Object>>> jsonAsArrayList = response.jsonPath().getList("networks");
            for (int i = 0; i < jsonAsArrayList.size(); i++) {
                if (jsonAsArrayList.get(i).get("location").get("city").equals(arg1) && jsonAsArrayList.get(i).get("location").get("country").equals(countryCode)) {
                    longitude = (float) jsonAsArrayList.get(i).get("location").get("longitude");
                    latitude = (float) jsonAsArrayList.get(i).get("location").get("latitude");
                    System.out.println("\nThe city '"+arg1+"' is in '"+arg2+"' with Latitude '"+latitude+"' and Longitude '"+longitude+"'.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String getCountryCode(String country) {
        Map<String, String> countries = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("", iso);
            countries.put(l.getDisplayCountry(), iso);
        }
        return countries.get(country);
    }
}
