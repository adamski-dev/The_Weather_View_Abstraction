package WeatherView.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Forecast {

    private static final DateTimeFormatter FORECAST_DATE_FORMAT = DateTimeFormatter.ofPattern("E dd");
    String date;
    String temperature;
    String humidity;
    String description;
    String pressure;
    String icon;

    public Forecast(String data){
        this.date = pullDate(data);
        this.temperature = pullTemperature(data);
        this.humidity = pullHumidity(data);
        this.description = pullDescription(data);
        this.pressure = pullPressure(data);
        this.icon = pullIcon(data);
    }

    private String pullDate(String data){
        String date = data.substring(data.indexOf("dt_txt") + 9, data.indexOf("dt_txt") + 19);
        LocalDate localDate = LocalDate.parse(date);
        return localDate.format(FORECAST_DATE_FORMAT);
    }

    private String pullTemperature(String data) {
        int intValue;
        intValue = getNumberRounded(data.substring(data.indexOf("temp_max") + 10, data.indexOf("pressure") - 2));
        return intValue + "\u00b0" + " C";
    }

    private int getNumberRounded(String number){
        return (int)(Math.round(Double.parseDouble(number)));
    }

    private String pullHumidity(String data) {
        int intValue;
        intValue = Integer.parseInt(data.substring(data.indexOf("humidity") + 10, data.indexOf("humidity") + 12));
        return intValue + " %";
    }

    private String pullDescription(String data) {
        return data.substring(data.indexOf("main\":\"") + 7, data.indexOf("description") - 3);
    }

    private String pullPressure(String data) {
        int intValue;
        intValue = Integer.parseInt(data.substring(data.indexOf("pressure") + 10, data.indexOf("sea_level") - 2));
        return intValue + " hPa";
    }

    private String pullIcon(String data) {
        String base = "https://openweathermap.org/img/w/";
        return base + data.substring(data.indexOf("icon") + 7, data.indexOf("icon") + 10) + ".png";
    }

    public String getDate() {
        return date;
    }
    public String getTemperature() {
        return temperature;
    }
    public String getHumidity() {
        return humidity;
    }
    public String getDescription() {
        return description;
    }
    public String getPressure() {
        return pressure;
    }
    public String getIcon(){
        return icon;
    }
}
