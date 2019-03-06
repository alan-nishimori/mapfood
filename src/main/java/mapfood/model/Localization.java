package mapfood.model;

public class Localization {

    private Double longitude;

    private Double latitude;

    private String type = "Point";

    public Localization() {
    }

    public Localization(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

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

    @Override
    public String toString() {
        return "Localization{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
