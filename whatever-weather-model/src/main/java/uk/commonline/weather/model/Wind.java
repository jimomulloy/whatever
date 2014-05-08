package uk.commonline.weather.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import uk.commonline.data.model.BaseEntity;

@Table(name="WIND")
@Entity
public class Wind extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chill;
	private String direction;
	private String speed;
	private Weather weather;

	public Wind() {
	}

	@Column(name="CHILL")
	public String getChill() {
		return chill;
	}

	public void setChill(String newChill) {
		this.chill = newChill;
	}

	@Column(name="DIRECTION")
	public final String getDirection() {
		return direction;
	}

	public final void setDirection(final String newDirection) {
		this.direction = newDirection;
	}

	@Column(name="SPEED")
	public final String getSpeed() {
		return speed;
	}

	public final void setSpeed(final String newSpeed) {
		this.speed = newSpeed;
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