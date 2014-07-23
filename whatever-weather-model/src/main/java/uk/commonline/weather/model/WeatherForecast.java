package uk.commonline.weather.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uk.commonline.data.model.EI;

@SuppressWarnings("serial")
@Table(name = "WEATHERFORECAST")
@Entity
@PrimaryKeyJoinColumn(name = "WEATHER_ID")
public class WeatherForecast extends Weather  {

    private Date periodFrom = new Date();

    private Date periodTo = new Date();

    public WeatherForecast() {
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "PERIODFROM")
    public Date getPeriodFrom() {
	return periodFrom;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "PERIODTO")
    public Date getPeriodTo() {
	return periodTo;
    }

    public void setPeriodFrom(Date periodFrom) {
	this.periodFrom = periodFrom;
    }

    public void setPeriodTo(Date periodTo) {
	this.periodTo = periodTo;
    }

    @Override
    public String toString() {
	return "WeatherForecast [periodFrom=" + periodFrom + ", periodTo=" + periodTo + ", id=" + id + "]" + super.toString();
    }

}
