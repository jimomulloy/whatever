package uk.commonline.weather.model;

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

    public double getAltitude() {
        return altitude;
    }

    public String getCountry() {
        return country;
    }

    public String getGeobase() {
        return geobase;
    }

    public long getGeobaseid() {
        return geobaseid;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getPostal() {
        return postal;
    }

    public String getRegion() {
        return region;
    }

    public String getSourceid() {
        return sourceid;
    }

    public String getType() {
        return type;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setGeobase(String geobase) {
        this.geobase = geobase;
    }

    public void setGeobaseid(long geobaseid) {
        this.geobaseid = geobaseid;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Location [postal=" + postal + ", sourceid=" + sourceid + ", type=" + type + ", region=" + region + ", altitude=" + altitude
                + ", latitude=" + latitude + ", longitude=" + longitude + ", geobase=" + geobase + ", geobaseid=" + geobaseid + ", country="
                + country + ", id=" + id + "]";
    }
}