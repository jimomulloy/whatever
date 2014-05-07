package uk.commonline.weather.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uk.commonline.data.model.BaseEntity;

@Table(name="WEATHERCONDITION")
@Entity
public class Condition extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	private String code;
	private String temp;
	private Date date;
	
	private Weather weather;

	public Condition() {
	}

	@Column(name="TEXT")
	public String getText() {
		return text;
	}

	public void setText(String newText) {
		this.text = newText;
	}

	@Column(name="CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String newCode) {
		this.code = newCode;
	}

	@Column(name="TEMP")
	public String getTemp() {
		return temp;
	}

	public void setTemp(String newTemp) {
		this.temp = newTemp;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE")
	public Date getDate() {
		return date;
	}

	public void setDate(Date newDate) {
		this.date = newDate;
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