package taskData;

import java.time.*;

public abstract class Task {
	/** variable definitions **/
	// holds completion state
	public boolean isCompleted;
	
	// start and end times
	public LocalTime start;
	public LocalTime end;
	
	// task score (should be in range 0..5)
	public int score;
	
	/** concrete method definitions **/
	public Duration getDuration() {
		return Duration.between(start, end);
	}
	
	public Duration getTimeRemaining(LocalTime currentTime) {
		if (isCompleted) {
			return Duration.ZERO;
		} else {		
			return Duration.between(currentTime, end);
		}
	}
	
	public boolean checkCollision(LocalTime t) {
		return (this.end.compareTo(t) > 0) && (this.start.compareTo(t) < 0);
	}
	
	public boolean checkStartCollision(LocalTime start) {
		return (this.end.compareTo(start) > 0);
	}
	
	public boolean checkEndCollision(LocalTime start) {
		return (this.start.compareTo(end) < 0);
	}
	
	/** abstract definitions **/
	public abstract String getTitle();
	public abstract String getDescription();
	
}
