package main;

import taskData.*;
import database.*;
import gui.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.time.LocalDate;
import java.util.HashMap;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

public class DeepWorkTracker {
	/** variable definitions **/
	private static HashMap<LocalDate, TaskObserver> taskDays;
	
	/** constructor definitions **/
	public DeepWorkTracker() {
		taskDays = new HashMap<LocalDate, TaskObserver>();
		
		TaskObserver newTaskDay = addTaskObserver(LocalDate.now());
		
		// TODO: remove
		newTaskDay.addTask(new DeepTask());
		newTaskDay.addTask(new DeepTask());
		newTaskDay.addTask(new DeepTask());
		newTaskDay.addTask(new DeepTask());
		
	}
	
	/** method definitions **/
	/**
	 * method to add a task observer to the list at the given date
	 * @param date
	 */
	public static TaskObserver addTaskObserver(LocalDate date) {
		TaskObserver newTaskDay = new TaskObserver(date);
		
		taskDays.put(date, newTaskDay);
		return newTaskDay;
	}
	
	
	/**
	 * method to get or create the task observer for a date
	 * @param date
	 * @return taskObserver
	 */
	public static TaskObserver getTaskObserver(LocalDate date) {
		if (taskDays.containsKey(date) == false) {
			return DeepWorkTracker.addTaskObserver(date);
		}
		
		return taskDays.get(date);
	}
	
	/**
	 * method to add a task to the task observer for a date
	 * @param date
	 * @param task
	 */
	public static void addTask(LocalDate date, Task task) {
		TaskObserver tasksToday = getTaskObserver(date);
		
		tasksToday.addTask(task);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// create static deepworktracker inst
		DeepWorkTracker DWT = new DeepWorkTracker();
		
		// apply look and feel (LAF)
		try {
		    UIManager.setLookAndFeel( new FlatDarkLaf() );
		    
		    // remove rounding
		    UIManager.put( "Button.arc", 0 );
		    UIManager.put( "Component.arc", 0 );
		    UIManager.put( "ProgressBar.arc", 0 );
		    UIManager.put( "TextComponent.arc", 0 );
		    
		    // add highlighting
		   // UIManager.put( "TabbedPane.selectedBackground", Color.white );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		
		// create event queue, and init and add MainWindow
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
