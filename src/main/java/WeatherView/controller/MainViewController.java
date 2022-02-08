package WeatherView.controller;

import WeatherView.ErrorMessages;
import WeatherView.controller.services.location.Location;
import WeatherView.controller.services.location.Loader;
import WeatherView.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.TextFields;

public class MainViewController extends BaseController implements Initializable {

    private Location location;
    private ForecastWeather forecastWeather;
    private CurrentWeather currentWeather;
    private WeatherStation weatherStation;

    public MainViewController(String fxml) {
        super(fxml);
    }

    @FXML
    private Label actualLocation;
    @FXML
    private Label destinationLocation;

    @FXML
    private ImageView weatherIcon;
    @FXML
    private ImageView destinationWeatherIcon;

    @FXML
    private Label actualTemperature;
    @FXML
    private Label destinationActualTemperature;

    @FXML
    private Label feelsLike;
    @FXML
    private Label destinationFeelsLike;

    @FXML
    private Label weatherDescription;
    @FXML
    private Label destinationWeatherDescription;

    @FXML
    private Label humidity;
    @FXML
    private Label destinationHumidity;

    @FXML
    private Label pressure;
    @FXML
    private Label destinationPressure;

    @FXML
    private Label wind;
    @FXML
    private Label destinationWind;

    @FXML
    private Label visibility;
    @FXML
    private Label destinationVisibility;

    @FXML
    private Label date;
    @FXML
    private Label destinationDate;

    @FXML
    private HBox forecast;
    @FXML
    private HBox destinationForecast;

    @FXML
    private Label generalError;
    @FXML
    private Label destinationGeneralError;

    @FXML
    private TextField locationEntry;
    @FXML
    private TextField destinationEntry;

    @FXML
    void getMyLocation() {
        setLocation();
        clearMyLocationView();
        setUpMyLocationWeatherViews();

    }

    @FXML
    void getMyDestination() {
        setDestination();
        clearDestinationView();
        setUpDestinationWeatherViews();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAvailableLocations();
        loadDefaultCity();
        clearMyLocationView();
        setUpMyLocationWeatherViews();
        clearDestinationView();
        setUpDestinationWeatherViews();
    }

    private void loadAvailableLocations() {
        Loader loader = new Loader();
        TextFields.bindAutoCompletion(locationEntry, loader.getListOfCities().values());
        TextFields.bindAutoCompletion(destinationEntry, loader.getListOfCities().values());
    }

    private void loadDefaultCity() {
        location = new Location("Athlone, IE");
    }

    private void clearMyLocationView() {
        generalError.setText("");
        actualTemperature.setText("");
        actualLocation.setText("");
        feelsLike.setText("");
        weatherDescription.setText("");
        date.setText("");
        weatherIcon.setImage(null);
        humidity.setText("");
        pressure.setText("");
        wind.setText("");
        visibility.setText("");
        forecast.getChildren().clear();
    }

    private void setUpMyLocationWeatherViews() {

        if (location.isValidEntry()) {

            try {
                weatherStation = new WeatherStationData(location);
                currentWeather = weatherStation.getCurrentWeather();
                forecastWeather = weatherStation.getForecastWeather();
                setCurrentWeatherParameters();
                setForecastWeatherParameters(forecast);

            } catch (Exception e) {
                clearMyLocationView();
                generalError.setText(ErrorMessages.CITY_NOT_FOUND);
            }
        } else {
            if (location.isEmptyFlag()) {
                clearMyLocationView();
                generalError.setText(ErrorMessages.LOCATION_FIELD_EMPTY);
            } else {
                clearMyLocationView();
                generalError.setText(ErrorMessages.CITY_NOT_FOUND);
            }
        }
    }

    private void setCurrentWeatherParameters() {

        actualLocation.setText(location.getCityAndCountry());
        date.setText(currentWeather.getDate());
        actualTemperature.setText(currentWeather.getTemperature());
        feelsLike.setText(currentWeather.getFeelsLike());
        weatherDescription.setText(currentWeather.getDescription());
        humidity.setText(currentWeather.getHumidity());
        pressure.setText(currentWeather.getPressure());
        wind.setText(currentWeather.getWind());
        visibility.setText(currentWeather.getActualVisibility());
        weatherIcon.setImage(new Image(currentWeather.getIcon()));
    }

    private void setForecastWeatherParameters (HBox forecast){

        for (Forecast oneDayForecast : forecastWeather.getForecastWeatherData()){

            VBox dayVBox = new VBox();

            Label dateLabel = new Label(oneDayForecast.getDate());
            Label temperatureLabel = new Label(oneDayForecast.getTemperature());
            Label humidityLabel = new Label(oneDayForecast.getHumidity());
            Label descriptionLabel = new Label(oneDayForecast.getDescription());
            Label pressureLabel = new Label(oneDayForecast.getPressure());

            dateLabel.getStyleClass().add("forecast-date-label");
            descriptionLabel.getStyleClass().add("desc-label");
            temperatureLabel.getStyleClass().add("temp-label");
            humidityLabel.getStyleClass().add("fields");
            pressureLabel.getStyleClass().add("fields");

            dayVBox.getChildren().addAll(
                    dateLabel,
                    new ImageView(new Image(oneDayForecast.getIcon())),
                    descriptionLabel,
                    pressureLabel,
                    temperatureLabel,
                    humidityLabel
            );
            forecast.getChildren().add(dayVBox);
            dayVBox.setAlignment(Pos.CENTER);
            forecast.setSpacing(55);
        }
        
    }

    private void setLocation() {
        location = new Location(locationEntry.getText());
    }

    private void setDestination() {
        location = new Location(destinationEntry.getText());
    }

    private void clearDestinationView() {

        destinationGeneralError.setText("");
        destinationActualTemperature.setText("");
        destinationLocation.setText("");
        destinationFeelsLike.setText("");
        destinationWeatherDescription.setText("");
        destinationDate.setText("");
        destinationWeatherIcon.setImage(null);
        destinationHumidity.setText("");
        destinationPressure.setText("");
        destinationWind.setText("");
        destinationVisibility.setText("");
        destinationForecast.getChildren().clear();
    }

    private void setUpDestinationWeatherViews() {

        if (location.isValidEntry()) {

            try {
                weatherStation = new WeatherStationData(location);
                currentWeather = weatherStation.getCurrentWeather();
                forecastWeather = weatherStation.getForecastWeather();
                setDestinationCurrentWeatherParameters();
                setForecastWeatherParameters(destinationForecast);

            } catch (Exception e) {
                clearDestinationView();
                destinationGeneralError.setText(ErrorMessages.CITY_NOT_FOUND);
            }
        } else {
            if (location.isEmptyFlag()) {
                clearDestinationView();
                destinationGeneralError.setText(ErrorMessages.LOCATION_FIELD_EMPTY);
            } else {
                clearDestinationView();
                destinationGeneralError.setText(ErrorMessages.CITY_NOT_FOUND);
            }
        }
    }

    private void setDestinationCurrentWeatherParameters() {

        destinationLocation.setText(location.getCityAndCountry());
        destinationDate.setText(currentWeather.getDate());
        destinationActualTemperature.setText(currentWeather.getTemperature());
        destinationFeelsLike.setText(currentWeather.getFeelsLike());
        destinationWeatherDescription.setText(currentWeather.getDescription());
        destinationHumidity.setText(currentWeather.getHumidity());
        destinationPressure.setText(currentWeather.getPressure());
        destinationWind.setText(currentWeather.getWind());
        destinationVisibility.setText(currentWeather.getActualVisibility());
        destinationWeatherIcon.setImage(new Image(currentWeather.getIcon()));
    }

}
