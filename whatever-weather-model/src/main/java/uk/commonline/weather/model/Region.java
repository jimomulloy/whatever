package uk.commonline.weather.model;

//@XmlRootElement
public class Region {
    public double longitude;
    public double latitude;
    public long region;

    public Region() {
    }

    @Override
    public String toString() {
        return "Region [longitude=" + longitude + ", latitude=" + latitude + ", region=" + region + "]";
    }
}