package models;

import java.util.Objects;

public class User {
    private long id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public Company getCompany() {
        return company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id
                && name.equals(user.name)
                && username.equals(user.username)
                && email.equals(user.email)
                && address.equals(user.address)
                && phone.equals(user.phone)
                && website.equals(user.website)
                && company.equals(user.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, email, address, phone, website, company);
    }
}
