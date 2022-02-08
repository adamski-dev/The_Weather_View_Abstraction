package WeatherView.controller.services.location;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Location {

    private static final Pattern PATTERN = Pattern.compile("([A-z])\\w+, [A-Z]\\w+");
    private String name;
    private String country;
    private boolean emptyFlag = false;

    private final String cityAndCountry;

    public Location(String cityAndCountry) {
        this.cityAndCountry = cityAndCountry;
    }

    public String getCity() {return name;}
    public String getCountry() {return country;}
    public String getCityAndCountry() {
        return cityAndCountry;
    }

    private boolean entryValidation(){

        try {
            if(cityAndCountry.equals("")){
                emptyFlag = true;
            }

            Matcher matcher = PATTERN.matcher(cityAndCountry);

            if(matcher.find()){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isValidEntry(){
        return entryValidation();
    }

    public boolean isEmptyFlag(){
        return emptyFlag;
    }
}

