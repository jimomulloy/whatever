package uk.commonline.weather.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import uk.commonline.data.model.ListWrapper;
import uk.commonline.data.model.NamedEntity;

//@XmlRootElement
@SuppressWarnings("serial")
public class Location extends NamedEntity {

    private String postal;
    private String sourceid;
    private String type;
    private String region;
    private double altitude = 0.0;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private String geobase;
    private long geobaseid = 0;
    private String country;

    public Location() {
    }

    public String getRegion() {
	return region;
    }

    public void setRegion(String region) {
	this.region = region;
    }

    public String getCountry() {
	return country;
    }

    public void setCountry(String country) {
	this.country = country;
    }

    @Override
    public String toString() {
	return "Location [postal=" + postal + ", sourceid=" + sourceid + ", type=" + type + ", region=" + region + ", altitude=" + altitude
		+ ", latitude=" + latitude + ", longitude=" + longitude + ", geobase=" + geobase + ", geobaseid=" + geobaseid + ", country="
		+ country + ", id=" + id + "]";
    }

    public String getPostal() {
	return postal;
    }

    public void setPostal(String postal) {
	this.postal = postal;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public double getAltitude() {
	return altitude;
    }

    public void setAltitude(double altitude) {
	this.altitude = altitude;
    }

    public double getLatitude() {
	return latitude;
    }

    public void setLatitude(double latitude) {
	this.latitude = latitude;
    }

    public double getLongitude() {
	return longitude;
    }

    public void setLongitude(double longitude) {
	this.longitude = longitude;
    }

    public String getGeobase() {
	return geobase;
    }

    public void setGeobase(String geobase) {
	this.geobase = geobase;
    }

    public long getGeobaseid() {
	return geobaseid;
    }

    public void setGeobaseid(long geobaseid) {
	this.geobaseid = geobaseid;
    }

    public String getSourceid() {
	return sourceid;
    }

    public void setSourceid(String sourceid) {
	this.sourceid = sourceid;
    }
}