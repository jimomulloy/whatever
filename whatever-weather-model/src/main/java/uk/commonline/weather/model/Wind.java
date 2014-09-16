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
@Table(name = "WIND")
@Entity
public class Wind extends BaseEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private double chill = 0.0;
    private String direction;
    private double speed = 0.0;
    @JsonBackReference
    private Weather weather;

    public Wind() {
    }

    @Column(name = "CHILL")
    public double getChill() {
        return chill;
    }

    @Column(name = "DIRECTION")
    public String getDirection() {
        return direction;
    }

    @Column(name = "SPEED")
    public double getSpeed() {
        return speed;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WEATHER_ID", referencedColumnName = "id")
    public Weather getWeather() {
        return weather;
    }

    public void setChill(double newChill) {
        this.chill = newChill;
    }

    public void setDirection(String newDirection) {
        this.direction = newDirection;
    }

    public void setSpeed(double newSpeed) {
        this.speed = newSpeed;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "Wind [chill=" + chill + ", direction=" + direction + ", speed=" + speed + ", id=" + id + "]";
    }

}