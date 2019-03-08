package mapfood.model;

import java.util.Locale;

public class Localization {

    private Double latitude;

    private Double longitude;

    private String type = "Point";

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getType() {
        return type;
    }

    public String toUrlValue() {
        return String.format(Locale.ENGLISH, "%.8f,%.8f", this.latitude, this.longitude);
    }

    @Override
    public String toString() {
        return "Localization{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
