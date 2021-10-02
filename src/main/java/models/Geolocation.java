package models;

import java.util.Objects;

public class Geolocation {
    private String lat;
    private String lng;

    public Geolocation() {
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geolocation that = (Geolocation) o;
        return lat.equals(that.lat)
                && lng.equals(that.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }
}
