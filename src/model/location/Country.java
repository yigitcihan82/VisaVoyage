package model.location;

public class Country {
    private String name;
    private String visaRequirementType; // "Schengen", "US", "None"

    public Country(String name, String visaRequirementType) {
        this.name = name;
        this.visaRequirementType = visaRequirementType;
    }

    public String getName() { return name; }
    public String getVisaRequirementType() { return visaRequirementType; }

    @Override
    public String toString() { return name; }
}