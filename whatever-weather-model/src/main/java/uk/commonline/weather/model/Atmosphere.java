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
	public final String getHumidity() {
		return humidity;
	}

	public final void setHumidity(final String newHumidity) {
		this.humidity = newHumidity;
	}

	@Column(name="VISIBILITY")
	public final String getVisibility() {
		return visibility;
	}

	public final void setVisibility(final String newVisibility) {
		this.visibility = newVisibility;
	}

	@Column(name="PRESSURE")
	public final String getPressure() {
		return pressure;
	}

	public final void setPressure(final String newPressure) {
		this.pressure = newPressure;
	}

	@Column(name="RISING")
	public final String getRising() {
		return rising;
	}

	public final void setRising(final String newRising) {
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