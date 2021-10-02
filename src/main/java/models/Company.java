package models;

import java.util.Objects;

public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public Company() {
    }

    public String getName() {
        return name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return name.equals(company.name)
                && catchPhrase.equals(company.catchPhrase)
                && bs.equals(company.bs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, catchPhrase, bs);
    }
}
