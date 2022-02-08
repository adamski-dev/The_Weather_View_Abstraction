package WeatherView.model;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ForecastWeatherData extends ForecastWeather {

    private static final int DAY_ONE_STAMP = 0;
    private static final int DAY_TWO_STAMP = 86400;
    private static final int DAY_THREE_STAMP = 172800;
    private static final int DAY_FOUR_STAMP = 259200;
    private static final int END_OF_DAY_INCREMENT = 10800;
    private final String dayOneData;
    private final String dayTwoData;
    private final String dayThreeData;
    private final String dayFourData;
    private final String data;

    public ForecastWeatherData(String data) {

        this.data = data;
        this.dayOneData = getForecast(getStartDataStamp(DAY_ONE_STAMP));
        this.dayTwoData = getForecast(getStartDataStamp(DAY_TWO_STAMP));
        this.dayThreeData = getForecast(getStartDataStamp(DAY_THREE_STAMP));
        this.dayFourData = getForecast(getStartDataStamp(DAY_FOUR_STAMP));
        this.forecastWeatherData = createForecastObjectList();
    }

    private String getTomorrowNoon() {
        String tomorrow = String.valueOf(LocalDate.now().plusDays(1));
        return tomorrow + " 12:00:00";
    }

    private String getStartDataStamp(int increment) {
        int intValue = Integer.parseInt(data.substring(data.indexOf(getTomorrowNoon()) + 28, data.indexOf(getTomorrowNoon()) + 38)) + increment;
        return String.valueOf(intValue);
    }

    private String getForecastEnd(String thisDay) {
        int intValue = Integer.parseInt(thisDay) + END_OF_DAY_INCREMENT;
        return String.valueOf(intValue);
    }

    private String getForecast(String thisDay) {
        return data.substring(data.indexOf(thisDay), data.indexOf(getForecastEnd(thisDay)));
    }

    private List<Forecast> createForecastObjectList(){

        Forecast forecast1 = new Forecast(dayOneData);
        Forecast forecast2 = new Forecast(dayTwoData);
        Forecast forecast3 = new Forecast(dayThreeData);
        Forecast forecast4 = new Forecast(dayFourData);

        return Arrays.asList(forecast1, forecast2, forecast3, forecast4);
    }
}