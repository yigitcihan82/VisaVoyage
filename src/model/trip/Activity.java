package model.trip;

import java.time.LocalDateTime;

public abstract class Activity implements Schedulable {
    protected String description;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public Activity(String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public LocalDateTime getStartTime() { return startTime; }

    @Override
    public LocalDateTime getEndTime() { return endTime; }

    // Alt sınıflar maliyeti hesaplamak ZORUNDA
    public abstract double calculateCost();
}
