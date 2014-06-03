package uk.commonline.weather.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uk.commonline.data.model.BaseEntity;

@Table(name="WEATHER")
@Entity
@NamedQueries({ @NamedQuery(name = "Weather.byLocation", query = "from Weather w where w.location = :location") })
public class Weather extends BaseEntity {

	/**
	 * 
	 */
	private static long serialVersionUID = 1L;

	private Location location;

	private Condition condition;

	private Wind wind;

	private Atmosphere atmosphere;

	private Date date;

	public Weather() {
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LOCATION_ZIP", nullable = false)
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition newCondition) {
		this.condition = newCondition;
	}

	@OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	@OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
	public Atmosphere getAtmosphere() {
		return atmosphere;
	}

	public void setAtmosphere(Atmosphere atmosphere) {
		this.atmosphere = atmosphere;
	}
	 
	@Temporal(TemporalType.DATE)
	@Column(name="DATE")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
