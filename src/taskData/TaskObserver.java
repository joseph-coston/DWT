package taskData;

import java.time.*;
import java.util.ArrayList;

public class TaskObserver {
	/** variable definitions **/
	public ArrayList<Task> tasks;
	public LocalDate date;
	
	/** constructor definitions **/
	public TaskObserver(LocalDate date) {
		this.date = date;
		tasks = new ArrayList<Task>();
	}
	
	/** method definitions **/
	public void addTask(Task t) {
		// TODO : make sure task does not collide with any others
		// TODO : keep list of tasks sorted by time
		tasks.add(t);
	}
	
	public Task getTaskIndex(int i) {
		return tasks.get(i);
	}
	
	public Task getTaskStartTime(LocalTime t) {
		// TODO : search for a task with start time t
		return tasks.get(0);
	}
	
	public Task getTaskEndTime(LocalTime t) {
		// TODO : search for a task with end time t
		return tasks.get(0);
	}
	
	public void updateTasksCompleted() {
		LocalTime now = LocalTime.now();
		for (Task t : tasks) {
			Duration d = t.getTimeRemaining(now);
			
			if (d.isNegative()) {
				setTaskCompleted(t);
			}
		}		
	}
	
	private void setTaskCompleted(Task t) {
		t.isCompleted = true;
		
		// TODO send notifications, update GUI, etc
	}
	
	public double getAverageScore() {
		double avg = 0;
		int count = 0;
		
		for (Task t : tasks) {
			if (t.isCompleted) {
				avg += (double) t.score;
				count += 1;
			}
		}	
		
		return avg / (double) count;
	}
}
