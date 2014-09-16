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

import org.codehaus.jackson.annotate.JsonBackReference;

import uk.commonline.data.model.BaseEntity;

//@XmlRootElement
@Table(name = "WEATHERCONDITION")
@Entity
public class Condition extends BaseEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String text;
    private String description;
    private String code;
    private String icon;
    private double minTemp = 0.0;
    private double maxTemp = 0.0;
    private Date fromTime = new Date();
    private Date toTime = new Date();
    @JsonBackReference
    private Weather weather;

    public Condition() {
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FROMTIME")
    public Date getFromTime() {
        return fromTime;
    }

    @Column(name = "ICON")
    public String getIcon() {
        return icon;
    }

    @Column(name = "MAXTEMP")
    public double getMaxTemp() {
        return maxTemp;
    }

    @Column(name = "MINTEMP")
    public double getMinTemp() {
        return minTemp;
    }

    @Column(name = "TEXT")
    public String getText() {
        return text;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TOTIME")
    public Date getToTime() {
        return toTime;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WEATHER_ID", referencedColumnName = "id")
    public Weather getWeather() {
        return weather;
    }

    public void setCode(String newCode) {
        this.code = newCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public void setText(String newText) {
        this.text = newText;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "Condition [text=" + text + ", description=" + description + ", code=" + code + ", icon=" + icon + ", minTemp=" + minTemp
                + ", maxTemp=" + maxTemp + ", fromTime=" + fromTime + ", toTime=" + toTime + ", id=" + id + "]";
    }

}