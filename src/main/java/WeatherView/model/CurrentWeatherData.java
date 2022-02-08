package WeatherView.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CurrentWeatherData extends CurrentWeather {

    private static final DateTimeFormatter ACTUAL_DATE_FORMAT = DateTimeFormatter.ofPattern("E, MMM dd");
    private int intValue = 0;

    public CurrentWeatherData(String data) {
        this.icon = pullIcon(data);
        this.temperature = pullTemperature(data);
        this.description = pullDescription(data);
        this.feelsLikeTemperature = pullFeelsLikeTemperature(data);
        this.date = pullActualDate();
        this.humidity = pullHumidity(data);
        this.pressure = pullPressure(data);
        this.wind = pullWind(data);
        this.visibility = pullActualVisibility(data);
    }

    private String pullIcon(String data) {
        String base = "https://openweathermap.org/img/w/";
        return base + data.substring(data.indexOf("icon") + 7, data.indexOf("icon") + 10) + ".png";
    }

    private String pullTemperature(String data) {
        intValue = roundNumber(data.substring(data.indexOf("temp_max") + 10, data.indexOf("pressure") - 2));
        return intValue  + "\u00b0";
    }

    private int roundNumber(String number){
        return (int)(Math.round(Double.parseDouble(number)));
    }

    private String pullDescription(String data){
        String text = data.substring(data.indexOf("description") + 14, data.indexOf("icon") - 3);
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    private String pullFeelsLikeTemperature(String data) {
        int intValue;
        intValue = roundNumber(data.substring(data.indexOf("feels_like") + 12, data.indexOf("temp_min") - 2));
        return "Feels like: " + intValue + "\u00b0";
    }

    private String pullActualDate() {
        return LocalDate.now().format(ACTUAL_DATE_FORMAT);
    }

    private String pullHumidity(String data){
        intValue = Integer.parseInt(data.substring(data.indexOf("humidity") + 10, data.indexOf("humidity") + 12));
        return "Humidity: " + intValue + " %";
    }

    private String pullPressure(String data){
        intValue = Integer.parseInt(data.substring(data.indexOf("pressure") + 10, data.indexOf("humidity") - 2));
        return "Pressure: " + intValue + " hPa";
    }

    private String pullWind(String data){
        intValue = roundNumber(data.substring(data.indexOf("speed") + 7, data.indexOf("deg") - 2));
        return "Wind: " + intValue + " m/s";
    }

    private String pullActualVisibility(String data){
        intValue = roundNumber(data.substring(data.indexOf("visibility") + 12, data.indexOf("wind") - 2)) / 1000;
        return "Visibility: " + intValue + " km";
    }
}
