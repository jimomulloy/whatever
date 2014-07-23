package uk.commonline.weather.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

import uk.commonline.data.model.BaseEntity;

//@XmlRootElement
@Table(name = "PRECIPITATION")
@Entity
public class Precipitation extends BaseEntity {

    @Override
    public String toString() {
	return "Precipitation [text=" + text + ", code=" + code + ", rate=" + rate + ", id=" + id + "]";
    }

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String text;
    private String code;
    private double rate = 0.0;

    @JsonBackReference
    private Weather weather;

    public Precipitation() {
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WEATHER_ID", referencedColumnName = "id")
    public Weather getWeather() {
	return weather;
    }

    public void setWeather(Weather weather) {
	this.weather = weather;
    }

    @Column(name = "TEXT")
    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    @Column(name = "RATE")
    public double getRate() {
	return rate;
    }

    public void setRate(double rate) {
	this.rate = rate;
    }

    @Column(name = "CODE")
    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

}