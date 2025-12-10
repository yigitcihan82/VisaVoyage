package model.user;

import model.trip.Trip;
import java.util.ArrayList;
import java.util.List;

public class User {
    private Profile profile;
    private List<Preference> preferences;
    private List<Trip> trips;

    public User(Profile profile) {
        this.profile = profile;
        this.preferences = new ArrayList<>();
        this.trips = new ArrayList<>();
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public List<Trip> getTrips() { return trips; }
    public Profile getProfile() { return profile; }
}