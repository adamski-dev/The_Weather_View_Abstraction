package WeatherView.controller;

public abstract class BaseController {

    private final String fxmlFileName;

    public BaseController(String fxmlFileName) {
        this.fxmlFileName = fxmlFileName;
    }

    public String getFxmlFileName() {
        return fxmlFileName;
    }
}
