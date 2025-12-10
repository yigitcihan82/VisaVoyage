package model.user;

public class Preference {
    private String tag; // Örn: "History", "Nature", "Nightlife"

    public Preference(String tag) {
        this.tag = tag;
    }

    public String getTag() { return tag; }
}