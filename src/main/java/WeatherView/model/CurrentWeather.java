package WeatherView.model;

public abstract class CurrentWeather {

    String icon;
    String temperature;
    String description;
    String feelsLikeTemperature;
    String date;
    String humidity;
    String pressure;
    String wind;
    String visibility;

    public String getIcon() {
        return icon;
    }
    public String getTemperature() { return temperature; }
    public String getDescription() {
        return description;
    }
    public String getFeelsLike() { return feelsLikeTemperature; }
    public String getDate() { return date; }
    public String getHumidity() { return humidity; }
    public String getPressure() { return pressure; }
    public String getWind() { return wind; }
    public String getActualVisibility() { return visibility; }

}
