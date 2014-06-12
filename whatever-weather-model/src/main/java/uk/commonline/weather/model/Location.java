package uk.commonline.weather.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import uk.commonline.data.model.BaseEntity;
import uk.commonline.data.model.ListWrapper;

@XmlRootElement
@SuppressWarnings("serial")
@Table(name = "LOCATION")
@Entity
@NamedQueries({ @NamedQuery(name = "Location.uniqueByZip", query = "from Location l where l.zip = :zip") })
public class Location extends BaseEntity<Location> {

    private String zip;
    private String woeid;
    private String city;
    private String region;
    private String country;

    private Source source;

    public Location() {
    }

    @Column(name = "ZIP")
    public String getZip() {
	return zip;
    }

    public void setZip(String zip) {
	this.zip = zip;
    }

    @Column(name = "CITY")
    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    @Column(name = "REGION")
    public String getRegion() {
	return region;
    }

    public void setRegion(String region) {
	this.region = region;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
	return country;
    }

    public void setCountry(String country) {
	this.country = country;
    }

    @Column(name = "WOEID")
    public String getWoeid() {
	return woeid;
    }

    public void setWoeid(String woeid) {
	this.woeid = woeid;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SOURCE_ID", nullable = false)
    public Source getSource() {
	return source;
    }

    public void setSource(Source source) {
	this.source = source;
    }

    @XmlRootElement(name = "locations")
    public static class LocationListWrapper implements ListWrapper<Location> {
	private List<Location> list;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.springinpractice.ch11.model.ListWrapper#getList()
	 */
	@Override
	@XmlElement(name = "location")
	public List<Location> getList() {
	    return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.springinpractice.ch11.model.ListWrapper#setList(java.util.List)
	 */
	@Override
	public void setList(List<Location> list) {
	    this.list = list;
	}
    }

    public String toString() {
	return "WTW Location woeid:" + woeid;
    }
}