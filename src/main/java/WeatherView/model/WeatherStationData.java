package WeatherView.model;

import WeatherView.controller.services.location.Location;
import WeatherView.controller.services.open_weather_map.OpenWMLoader;

public class WeatherStationData extends WeatherStation {

    private final Location location;

    public WeatherStationData(Location location){
        this.location = location;
        this.currentWeather = requireCurrentWeatherData();
        this.forecastWeather = requireForecastWeatherData();
    }

    private String getCurrentWeatherDataFromProvider(){
        OpenWMLoader openWMLoader = new OpenWMLoader();
        return openWMLoader.requestActualWeatherDataFromProvider(location.getCityAndCountry());
    }

    private String getForecastWeatherDataFromProvider(){
        OpenWMLoader openWMLoader = new OpenWMLoader();
        return openWMLoader.requestForecastWeatherDataFromProvider(location.getCityAndCountry());
    }

    private CurrentWeatherData requireCurrentWeatherData(){
        return new CurrentWeatherData(getCurrentWeatherDataFromProvider());
    }

    private ForecastWeatherData requireForecastWeatherData(){
        return new ForecastWeatherData(getForecastWeatherDataFromProvider());
    }

}
