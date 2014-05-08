package uk.commonline.weather.persist;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import uk.commonline.weather.model.Location;

public class LocationDAO extends AbstractHibernateDAO<Location> {

	public LocationDAO() {
		setClazz(Location.class);
	}

	public Location findByZip(final String zip) {
		Query query = getCurrentSession().getNamedQuery("Location.uniqueByZip");
		query.setString("zip", zip);
		Location location = (Location) query.uniqueResult();
		return location;
	}

}