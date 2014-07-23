package uk.commonline.weather.model;

import java.util.Date;
import java.util.List;

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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonManagedReference;

import uk.commonline.data.model.BaseEntity;
import uk.commonline.data.model.EI;
import uk.commonline.data.model.ListWrapper;

@SuppressWarnings("serial")
@Table(name = "WEATHER")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({ @NamedQuery(name = "Weather.byRegion", query = "from Weather w where w.region = :region and w.writeTime >= :date"),
	@NamedQuery(name = "Weather.since", query = "from Weather w where w.writeTime >= :date") })
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

    @JsonManagedReference
    private Precipitation precipitation;

    public Weather() {
    }

    @Column(name = "REGION")
    public long getRegion() {
	return region;
    }

    public void setRegion(long region) {
	this.region = region;
    }

    @OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
    public Condition getCondition() {
	return condition;
    }

    public void setCondition(Condition newCondition) {
	this.condition = newCondition;
    }

    @OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
    public Wind getWind() {
	return wind;
    }

    public void setWind(Wind wind) {
	this.wind = wind;
    }

    @OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
    public Atmosphere getAtmosphere() {
	return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
	this.atmosphere = atmosphere;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "WRITETIME")
    public Date getWriteTime() {
	return writeTime;
    }

    public void setWriteTime(Date writeTime) {
	this.writeTime = writeTime;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name = "SOURCETIME")
    public Date getSourceTime() {
	return sourceTime;
    }

    public void setSourceTime(Date sourceTime) {
	this.sourceTime = sourceTime;
    }

    @Column(name = "SOURCE")
    public String getSource() {
	return source;
    }

    public void setSource(String source) {
	this.source = source;
    }

    @Override
    public String toString() {
	return "Weather [region=" + region + ", condition=" + condition + ", wind=" + wind + ", atmosphere=" + atmosphere + ", writeTime="
		+ writeTime + ", sourceTime=" + sourceTime + ", source=" + source + ", precipitation=" + precipitation + ", id=" + id + "]";
    }

    @OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
    public Precipitation getPrecipitation() {
	return precipitation;
    }

    public void setPrecipitation(Precipitation precipitation) {
	this.precipitation = precipitation;
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
}
