package uk.commonline.weather.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//import org.hibernate.annotations.CacheConcurrencyStrategy;

import uk.commonline.data.model.BaseEntity;

@Table(name="ATMOSPHERE")
@Entity
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Atmosphere extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String humidity;
	private String visibility;
	private String pressure;
	private String rising;
	private Weather weather;

	public Atmosphere() {
	}

	@Column(name="HUMIDITY")
	public String getHumidity() {
		return humidity;
	}

	public void setHumidity( String newHumidity) {
		this.humidity = newHumidity;
	}

	@Column(name="VISIBILITY")
	public String getVisibility() {
		return visibility;
	}

	public void setVisibility( String newVisibility) {
		this.visibility = newVisibility;
	}

	@Column(name="PRESSURE")
	public String getPressure() {
		return pressure;
	}

	public void setPressure( String newPressure) {
		this.pressure = newPressure;
	}

	@Column(name="RISING")
	public String getRising() {
		return rising;
	}

	public void setRising( String newRising) {
		this.rising = newRising;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "WEATHER_ID", nullable = false)
	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

}