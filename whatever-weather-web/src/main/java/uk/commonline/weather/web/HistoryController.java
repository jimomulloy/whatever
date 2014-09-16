package uk.commonline.weather.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.commonline.weather.geo.client.jaxrs.GeoLocationClient;
import uk.commonline.weather.man.client.jaxrs.WeatherManClient;
import uk.commonline.weather.model.Weather;
import uk.commonline.weather.model.WeatherReport;

@Controller
// @RequestMapping(value = "/history")
public class HistoryController {

    @Inject
    private GeoLocationClient geoLocationClient;

    @Inject
    private WeatherManClient weatherManClient;

    public GeoLocationClient getGeoLocationService() {
        return geoLocationClient;
    }

    public WeatherManClient getWeatherManService() {
        return weatherManClient;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");

        List<Weather> weathers;
        WeatherReport weatherReport = weatherManClient.updateWeather(Long.parseLong(latitude), Long.parseLong(longitude));
        WeatherReport report = weatherManClient.updateWeather(Double.parseDouble(latitude), Double.parseDouble(longitude));
        Double latValue = 0.0, longValue = 0.0;
        try {
            latValue = Double.parseDouble(latitude);
        } catch (Exception ex) {

        }
        try {
            longValue = Double.parseDouble(longitude);
        } catch (Exception ex) {

        }
        report.setLatitude(latValue);
        report.setLongitude(longValue);
        return new ModelAndView("history", "history", report);
    }

    public void setGeoLocationService(GeoLocationClient geoLocationClient) {
        this.geoLocationClient = geoLocationClient;
    }

    public void setWeatherManService(WeatherManClient weatherManClient) {
        this.weatherManClient = weatherManClient;
    }

}