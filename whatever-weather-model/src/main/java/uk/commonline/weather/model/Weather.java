package uk.commonline.weather.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonManagedReference;

import uk.commonline.data.model.BaseEntity;

@SuppressWarnings("serial")
@Table(name = "WEATHER")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// @NamedQueries({ @NamedQuery(name = "Weather.byRegion", query =
// "from Weather w where w.region = :region and w.writeTime >= :fromTime and not exists (from WeatherForecast wf where wf.id = w.id) "),
// @NamedQuery(name = "Weather.range", query =
// "from Weather w where w.region = :region and w.writeTime >= :fromTime and not exists (from WeatherForecast wf where wf.id = w.id) "
// )})
@NamedQueries({
        @NamedQuery(name = "Weather.byRegion", query = "from Weather w where w.region = :region and w.writeTime >= :fromTime and w.forecast = false"),
        @NamedQuery(name = "Weather.range", query = "from Weather w where w.region = :region and w.writeTime >= :fromTime and w.writeTime <= :toTime and w.forecast = false") })
public class Weather extends BaseEntity {

    private long region = 0;

    @JsonManagedReference
    private Condition condition;

    @JsonManagedReference
    private Wind wind;

    @JsonManagedReference
    private Atmosphere atmosphere;

    private Date writeTime = new Date();

    private Date sourceTime = new Date();

    private String source;

    private boolean forecast = false;

    @JsonManagedReference
    private Precipitation precipitation;

    public Weather() {
    }

    public void clearBackReferences() {
        if (atmosphere != null) {
            atmosphere.setWeather(null);

        }
        if (condition != null) {
            condition.setWeather(null);

        }
        if (wind != null) {
            wind.setWeather(null);

        }
        if (precipitation != null) {
            precipitation.setWeather(null);

        }
    }

    @OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    @OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
    public Condition getCondition() {
        return condition;
    }

    @OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
    public Precipitation getPrecipitation() {
        return precipitation;
    }

    @Column(name = "REGION")
    public long getRegion() {
        return region;
    }

    @Column(name = "SOURCE")
    public String getSource() {
        return source;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SOURCETIME")
    public Date getSourceTime() {
        return sourceTime;
    }

    @OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
    public Wind getWind() {
        return wind;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "WRITETIME")
    public Date getWriteTime() {
        return writeTime;
    }

    @Column(name = "FORECAST")
    public boolean isForecast() {
        return forecast;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public void setBackReferences() {
        if (atmosphere != null) {
            atmosphere.setWeather(this);

        }
        if (condition != null) {
            condition.setWeather(this);

        }
        if (wind != null) {
            wind.setWeather(this);

        }
        if (precipitation != null) {
            precipitation.setWeather(this);

        }
    }

    public void setCondition(Condition newCondition) {
        this.condition = newCondition;
    }

    public void setForecast(boolean forecast) {
        this.forecast = forecast;
    }

    public void setPrecipitation(Precipitation precipitation) {
        this.precipitation = precipitation;
    }

    public void setRegion(long region) {
        this.region = region;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setSourceTime(Date sourceTime) {
        this.sourceTime = sourceTime;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setWriteTime(Date writeTime) {
        this.writeTime = writeTime;
    }

    @Override
    public String toString() {
        return "Weather [region=" + region + ", condition=" + condition + ", wind=" + wind + ", atmosphere=" + atmosphere + ", writeTime="
                + writeTime + ", sourceTime=" + sourceTime + ", source=" + source + ", precipitation=" + precipitation + ", id=" + id + "]";
    }
}
