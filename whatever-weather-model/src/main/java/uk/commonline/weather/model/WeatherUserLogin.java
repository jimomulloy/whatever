package uk.commonline.weather.model;

import javax.validation.constraints.Size;

public class WeatherUserLogin {

    // @NotEmpty
    @Size(min = 4, max = 20)
    private String userName;

    // @NotEmpty
    @Size(min = 4, max = 8)
    private String password;

    private double latitude, longitude;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
