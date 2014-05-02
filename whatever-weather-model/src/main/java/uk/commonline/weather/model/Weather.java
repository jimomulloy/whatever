package uk.commonline.weather.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import uk.commonline.data.model.BaseEntity;

@Entity
@NamedQueries({ @NamedQuery(name = "Weather.byLocation", query = "from Weather w where w.location = :location") })
public class Weather extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.ALL)
	private Location location;

	@OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
	private Condition condition;

	@OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
	private Wind wind;

	@OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
	private Atmosphere atmosphere;

	private Date date;

	public Weather() {
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public final Condition getCondition() {
		return condition;
	}

	public final void setCondition(final Condition newCondition) {
		this.condition = newCondition;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Atmosphere getAtmosphere() {
		return atmosphere;
	}

	public void setAtmosphere(Atmosphere atmosphere) {
		this.atmosphere = atmosphere;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
