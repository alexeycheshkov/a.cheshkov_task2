package models;

import java.util.Objects;

public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geolocation geo;

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Geolocation getGeo() {
        return geo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return street.equals(address.street)
                && suite.equals(address.suite)
                && city.equals(address.city)
                && zipcode.equals(address.zipcode)
                && geo.equals(address.geo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, suite, city, zipcode, geo);
    }
}
