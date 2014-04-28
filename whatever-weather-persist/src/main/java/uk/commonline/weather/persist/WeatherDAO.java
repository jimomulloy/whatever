package uk.commonline.weather.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import uk.commonline.weather.model.Location;
import uk.commonline.weather.model.Weather;

public class WeatherDAO extends AbstractHibernateDAO<Weather> {

	public WeatherDAO() {
		setClazz(Weather.class);
	}

	@SuppressWarnings("unchecked")
	public List<Weather> recentForLocation(final Location location) {
		Query query = getCurrentSession().getNamedQuery("Weather.byLocation");
		query.setParameter("location", location);
		ArrayList<Weather> weather = new ArrayList<Weather>(query.list());
		return weather;
	}

}