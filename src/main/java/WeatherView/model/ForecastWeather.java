package WeatherView.model;
import java.util.List;

public abstract class ForecastWeather {

    List<Forecast> forecastWeatherData;

    public List<Forecast> getForecastWeatherData(){
        return forecastWeatherData;
    }
}
