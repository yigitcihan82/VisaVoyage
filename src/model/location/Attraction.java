package model.location;

public class Attraction {
    private String name;
    private String description;
    private double entryFee;

    public Attraction(String name, String description, double entryFee) {
        this.name = name;
        this.description = description;
        this.entryFee = entryFee;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getEntryFee() { return entryFee; }

    @Override
    public String toString() {
        return name + " (" + description + ")";
    }
}