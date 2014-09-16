package uk.commonline.weather.web;

import java.util.List;

import uk.commonline.weather.model.Weather;

public class Report {

    private String latitude;
    private String longitude;
    private Weather current;

    private List<Weather> forecasts;

    public Weather getCurrent() {
        return current;
    }

    public List<Weather> getForecasts() {
        return forecasts;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setCurrent(Weather current) {
        this.current = current;
    }

    public void setForecasts(List<Weather> forecasts) {
        this.forecasts = forecasts;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
