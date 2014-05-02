package uk.commonline.weather.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import uk.commonline.data.model.BaseEntity;

@Entity
public class Condition extends BaseEntity {

	private String text;
	private String code;
	private String temp;
	private String date;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "weather_id", nullable = false)
	private Weather weather;

	public Condition() {
	}

	public String getText() {
		return text;
	}

	public void setText(String newText) {
		this.text = newText;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String newCode) {
		this.code = newCode;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String newTemp) {
		this.temp = newTemp;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String newDate) {
		this.date = newDate;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

}