package taskData;

import java.time.*;
import java.util.ArrayList;
import java.util.Collections;

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
		Collections.sort(tasks, 
			(a, b) -> a.start.compareTo(b.start)

			);
	}
	
	public void addTask(String taskType, String title, String description, LocalTime start, LocalTime end) {
		switch (taskType) {
			case "Shallow":
				tasks.add(new ShallowTask(title, description, start, end));
			break;
			case "Deep":
				tasks.add(new DeepTask(title, description, start, end));
			break;
			case "Break":
				tasks.add(new Break(title, description, start, end));
			break;	
			default:
				System.out.println("Invalid taskType.");
			break;
		}
		Collections.sort(tasks, 
				(a, b) -> a.start.compareTo(b.start)

				);
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
