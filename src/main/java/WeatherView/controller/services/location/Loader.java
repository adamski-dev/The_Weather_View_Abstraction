package WeatherView.controller.services.location;

import WeatherView.App;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Loader {

    private static final String LIST_OF_CITIES_ACCESS_PATH = "json/city.list.min.json";
    private final Gson json;

    public Loader() {
        this.json = new Gson();
    }

    public Map<String, String> getListOfCities() {
        return loadListOfCitiesFromJson();
    }

    private Map<String, String> loadListOfCitiesFromJson() {

        Type LOCATION_TYPE = new TypeToken<List<Location>>() {}.getType();
        InputStreamReader inputStreamReader = new InputStreamReader(App.class.getResourceAsStream(LIST_OF_CITIES_ACCESS_PATH), StandardCharsets.UTF_8);

        List<Location> jsonListOfCities = json.fromJson(inputStreamReader, LOCATION_TYPE);

        return jsonListOfCities.stream()
                .collect(Collectors.toMap(
                        Location::getCity,
                        place -> place.getCity() + ", " + place.getCountry(),
                        (a1, a2) -> a1
                ));
    }
}
