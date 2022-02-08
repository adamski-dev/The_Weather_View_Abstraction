package WeatherView.controller.services.open_weather_map;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class OpenWMLoader {

    private final OpenWM openWM;

    public OpenWMLoader() {
        this.openWM = new OpenWM();
    }

    private static String writeProviderResponseAsDataString(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int integerValue;
        while ((integerValue = reader.read()) >= 0) {
            stringBuilder.append((char) integerValue);
        }
        return stringBuilder.toString();
    }

    private static String getWeatherData(String url) throws IOException {
        InputStream inputStream = new URL(url).openStream();
        String data;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            data = writeProviderResponseAsDataString(bufferedReader);
        } finally {
            inputStream.close();
        }
        return data;
    }

    public String requestActualWeatherDataFromProvider(String city) {
        String actualDataString = "";
        try {
            actualDataString = getWeatherData(openWM.getActualWeatherCall(city));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actualDataString;
    }

    public String requestForecastWeatherDataFromProvider(String city) {
        String forecastDataString = "";
        try {
            forecastDataString = getWeatherData(openWM.getForecastWeatherCall(city));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return forecastDataString;
    }
}
