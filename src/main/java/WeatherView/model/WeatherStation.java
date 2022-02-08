package WeatherView.model;

public abstract class WeatherStation {

    CurrentWeather currentWeather;
    ForecastWeather forecastWeather;

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }
    public ForecastWeather getForecastWeather() { return forecastWeather;}
}
