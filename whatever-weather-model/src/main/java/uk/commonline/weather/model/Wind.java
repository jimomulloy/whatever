package uk.commonline.weather.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import uk.commonline.data.model.BaseEntity;

@Entity
public class Wind extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chill;
	private String direction;
	private String speed;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "weather_id", nullable = false)
	private Weather weather;

	public Wind() {
	}

	public String getChill() {
		return chill;
	}

	public void setChill(String newChill) {
		this.chill = newChill;
	}

	public final String getDirection() {
		return direction;
	}

	public final void setDirection(final String newDirection) {
		this.direction = newDirection;
	}

	public final String getSpeed() {
		return speed;
	}

	public final void setSpeed(final String newSpeed) {
		this.speed = newSpeed;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

}