package taskData;

import java.time.LocalTime;

public class DeepTask extends Task {
	public String title;
	public String description;
	
	public DeepTask() {
		this.isCompleted = false;
		this.score = 0;
		this.title = "Deep Task";
		this.description = "Deep Description";
		
		this.start = LocalTime.of(0, 0);
		this.end   = LocalTime.of(1, 30);
	}
	
	public DeepTask(String title, String description, LocalTime start, LocalTime end) {
		this.title = "Deep: " + title;
		this.description = description;
		
		this.start = start;
		this.end = end;
		
		this.score = 0;
		this.isCompleted = false;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

}
