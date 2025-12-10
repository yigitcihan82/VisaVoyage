package model.trip;

import java.time.LocalDateTime;

public interface Schedulable {
    LocalDateTime getStartTime();
    LocalDateTime getEndTime();
}
