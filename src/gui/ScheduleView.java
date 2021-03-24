package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.DeepWorkTracker;
import taskData.Task;
import taskData.TaskObserver;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.List;
import java.util.ArrayList;
import java.awt.Label;

public class ScheduleView extends JPanel {
	public ArrayList<ScheduleTaskPanel> taskPanels;
	public LocalDate day;
	
	public JPanel scrollView;
	public JScrollPane scrollPane;
	private JLabel dateLabel;
	/**
	 * Create the panel.
	 */
	public ScheduleView() {	
		day = LocalDate.now(); // default to today		
		taskPanels = new ArrayList<ScheduleTaskPanel>();
		makeGUI();		
	}
	
	public void makeGUI() {
		// set layout
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		// add "add task" button
	
		JButton btnAddTask = new JButton("Add Task");
		springLayout.putConstraint(SpringLayout.WEST, btnAddTask, 10, SpringLayout.WEST, this);
		btnAddTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {	
					ScheduleAddTask dialog = new ScheduleAddTask((ScheduleView) btnAddTask.getParent());
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		add(btnAddTask);
		
		JButton btnRemoveTask = new JButton("Remove Task");
		springLayout.putConstraint(SpringLayout.NORTH, btnRemoveTask, 342, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, btnRemoveTask, 10, SpringLayout.WEST, this);
		btnRemoveTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(btnRemoveTask);
		
		JButton btnEditTask = new JButton("Edit Task");
		springLayout.putConstraint(SpringLayout.WEST, btnEditTask, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnAddTask, -6, SpringLayout.NORTH, btnEditTask);
		springLayout.putConstraint(SpringLayout.SOUTH, btnEditTask, -6, SpringLayout.NORTH, btnRemoveTask);
		add(btnEditTask);
		
		CalendarPanel daySelector = new CalendarPanel(this);
		springLayout.putConstraint(SpringLayout.NORTH, daySelector, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, daySelector, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, daySelector, -29, SpringLayout.NORTH, btnAddTask);
		springLayout.putConstraint(SpringLayout.EAST, daySelector, 0, SpringLayout.EAST, btnAddTask);
		add(daySelector);
		
		scrollView = new JPanel();
		scrollPane = new JScrollPane(scrollView);
		springLayout.putConstraint(SpringLayout.EAST, btnEditTask, -6, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, btnRemoveTask, -6, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, btnAddTask, -6, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 32, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 550, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 365, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, this);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		scrollView.setLayout(new BoxLayout(scrollView, BoxLayout.PAGE_AXIS));
		
		dateLabel = new JLabel("2021-03-21");
		springLayout.putConstraint(SpringLayout.WEST, dateLabel, 12, SpringLayout.EAST, daySelector);
		springLayout.putConstraint(SpringLayout.SOUTH, dateLabel, -7, SpringLayout.NORTH, scrollPane);
		add(dateLabel);
		
		updateDay(day);
		
	}
	
	public void updateDay(LocalDate date) {
		day = date;
		taskPanels.clear();
		
		dateLabel.setText(date.toString());
		
		// remove old
		scrollView.removeAll();
		
		TaskObserver taskObserver = DeepWorkTracker.getTaskObserver(date);
		
		if (taskObserver.tasks.isEmpty()) {
			JLabel emptyLabel = new JLabel("No tasks for this day.", SwingConstants.CENTER);
			scrollView.add(emptyLabel);
		} else {
			for (Task t : taskObserver.tasks) {
				ScheduleTaskPanel tPanel = new ScheduleTaskPanel(
					t.getTitle(),
					t.getDescription(),
					t.start,
					t.end,
					t.isCompleted,
					t.score
				);
				
				taskPanels.add(tPanel);
			}
			
			// add the taskpanes to the scroll view
			for (ScheduleTaskPanel tPanel : taskPanels) {
				scrollView.add(tPanel);
			}
		}
		
		scrollView.updateUI();
		
	}
}

