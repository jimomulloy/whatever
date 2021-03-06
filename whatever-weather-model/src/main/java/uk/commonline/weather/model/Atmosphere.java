package uk.commonline.weather.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

//import org.hibernate.annotations.CacheConcurrencyStrategy;
import uk.commonline.data.model.BaseEntity;

@Table(name = "ATMOSPHERE")
@Entity
// @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Atmosphere extends BaseEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private double humidity = 0.0;
    private double visibility = 0.0;
    private double pressure = 0.0;
    private String rising;
    @JsonBackReference
    private Weather weather;

    public Atmosphere() {
    }

    @Column(name = "HUMIDITY")
    public double getHumidity() {
        return humidity;
    }

    @Column(name = "PRESSURE")
    public double getPressure() {
        return pressure;
    }

    @Column(name = "RISING")
    public String getRising() {
        return rising;
    }

    @Column(name = "VISIBILITY")
    public double getVisibility() {
        return visibility;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WEATHER_ID", referencedColumnName = "id")
    public Weather getWeather() {
        return weather;
    }

    public void setHumidity(double newHumidity) {
        this.humidity = newHumidity;
    }

    public void setPressure(double newPressure) {
        this.pressure = newPressure;
    }

    public void setRising(String newRising) {
        this.rising = newRising;
    }

    public void setVisibility(double newVisibility) {
        this.visibility = newVisibility;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "Atmosphere [humidity=" + humidity + ", visibility=" + visibility + ", pressure=" + pressure + ", rising=" + rising + ", id=" + id
                + "]";
    }

}