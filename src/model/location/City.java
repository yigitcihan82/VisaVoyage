package model.location;

public class City {
    private String name;
    private Country country;

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public String getName() { return name; }
    public Country getCountry() { return country; }

    @Override
    public String toString() { return name + ", " + country.getName(); }
}
