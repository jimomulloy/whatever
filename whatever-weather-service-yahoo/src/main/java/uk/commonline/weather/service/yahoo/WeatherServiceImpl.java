package uk.commonline.weather.service.yahoo;

import java.io.InputStream;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import uk.commonline.weather.model.Location;
import uk.commonline.weather.model.Weather;
import uk.commonline.weather.persist.WeatherDAO;
import uk.commonline.weather.service.WeatherService;

public class WeatherServiceImpl implements WeatherService {

	private YahooRetriever yahooRetriever;
	private YahooParser yahooParser;
	private WeatherDAO weatherDAO;

	public WeatherServiceImpl() {
	}

    @Transactional
	public Weather updateForecast(String zip) throws Exception {
    	Weather weather = retrieveForecast(zip);
    	System.out.println("!Update forcast for zip:"+zip);
		weatherDAO.update(weather);
		return weather;
	}
    
    @Transactional(readOnly = true)
    public List<Weather> getRecentWeather(Location location) throws Exception {
		List<Weather> weathers = weatherDAO.recentForLocation( location );
		return weathers;
	}

    @Transactional(readOnly = true)
    public Weather retrieveForecast(String zip) throws Exception {
		// Retrieve Data
		InputStream dataIn = yahooRetriever.retrieve(zip);

		// Parse DataS
		Weather weather = yahooParser.parse(zip, dataIn);

		return weather;
	}
	
	public YahooRetriever getYahooRetriever() {
		return yahooRetriever;
	}

	public void setYahooRetriever(YahooRetriever yahooRetriever) {
		this.yahooRetriever = yahooRetriever;
	}

	public YahooParser getYahooParser() {
		return yahooParser;
	}

	public void setYahooParser(YahooParser yahooParser) {
		this.yahooParser = yahooParser;
	}

	public WeatherDAO getWeatherDAO() {
		return weatherDAO;
	}

	public void setWeatherDAO(WeatherDAO weatherDAO) {
		this.weatherDAO = weatherDAO;
	}
}
