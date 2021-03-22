package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import javax.swing.JFormattedTextField;
import java.awt.Dimension;
import java.time.LocalTime;

import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JSeparator;

public class ScheduleTaskPanel extends JPanel {
	String name = "Task Name";
	String description = "Task description.";
	String startTime = "Start Time";
	String endTime = "End Time";
	boolean isCompleted;
	int rating;
	
	/**
	 * Create the panel.
	 */
	public ScheduleTaskPanel() {
		super();
		
		this.name = "Task Name";
		this.description = "Task description.";
		this.isCompleted = false;
		this.rating = 0;
		
		makeGUI();	
	}
	
	public ScheduleTaskPanel(String name, String description, LocalTime start, LocalTime end, boolean isCompleted, int rating) {
		super();
		
		this.name = name;
		this.description = description;
		this.isCompleted = isCompleted;
		this.rating = rating;
		
		this.startTime = start.toString();
		this.endTime = end.toString();
		
		makeGUI();	
	}
	
	public void makeGUI() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblTaskName = new JLabel(this.name);
		springLayout.putConstraint(SpringLayout.NORTH, lblTaskName, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblTaskName, 10, SpringLayout.WEST, this);
		add(lblTaskName);
		
		JFormattedTextField frmtdtxtfldTaskDescriptionArea = new JFormattedTextField();
		springLayout.putConstraint(SpringLayout.NORTH, frmtdtxtfldTaskDescriptionArea, 6, SpringLayout.SOUTH, lblTaskName);
		springLayout.putConstraint(SpringLayout.WEST, frmtdtxtfldTaskDescriptionArea, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, frmtdtxtfldTaskDescriptionArea, -30, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, frmtdtxtfldTaskDescriptionArea, 323, SpringLayout.WEST, lblTaskName);
		frmtdtxtfldTaskDescriptionArea.setText(this.description);
		
		// disable editing for now
		frmtdtxtfldTaskDescriptionArea.setEditable(false);
		
		add(frmtdtxtfldTaskDescriptionArea);
		
		JLabel lblTaskState = new JLabel();
		lblTaskState.setHorizontalAlignment(SwingConstants.RIGHT);
		springLayout.putConstraint(SpringLayout.NORTH, lblTaskState, 0, SpringLayout.NORTH, lblTaskName);
		springLayout.putConstraint(SpringLayout.WEST, lblTaskState, 143, SpringLayout.EAST, lblTaskName);
		
		lblTaskState.setText("Not Completed");
		if (this.isCompleted) {
			lblTaskState.setText("Completed");
		}
		add(lblTaskState);
		
		JLabel lblStartTime = new JLabel(startTime);
		springLayout.putConstraint(SpringLayout.NORTH, lblStartTime, 0, SpringLayout.NORTH, lblTaskName);
		springLayout.putConstraint(SpringLayout.EAST, lblStartTime, -10, SpringLayout.EAST, this);
		add(lblStartTime);
		
		JLabel lblEndTime = new JLabel(endTime);
		springLayout.putConstraint(SpringLayout.SOUTH, lblEndTime, 0, SpringLayout.SOUTH, frmtdtxtfldTaskDescriptionArea);
		springLayout.putConstraint(SpringLayout.EAST, lblEndTime, 0, SpringLayout.EAST, lblStartTime);
		add(lblEndTime);
		
		// TODO: display rating
		
		// prevent vertical resize
		setPreferredSize(new Dimension(431, 200));
		
		// add border
		//this.setBorder(BorderFactory.createDashedBorder(Color.white));
		//this.repaint();
	}
}
