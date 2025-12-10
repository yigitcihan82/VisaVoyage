package model.user;

import model.trip.Trip;
import java.util.ArrayList;
import java.util.List;

public class User {
    private Profile profile;
    private List<Preference> preferences; // Liste burada var
    private List<Trip> trips;

    public User(Profile profile) {
        this.profile = profile;
        this.preferences = new ArrayList<>(); // Burada başlatılıyor
        this.trips = new ArrayList<>();
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public Profile getProfile() {
        return profile;
    }

    // --- EKSİK OLAN METOT BUYDU, BUNU EKLE ---
    public List<Preference> getPreferences() {
        return preferences;
    }
}