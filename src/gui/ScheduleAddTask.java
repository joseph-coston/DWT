package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import taskData.Task;
import taskData.TaskObserver;

import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JSpinner;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.beans.PropertyChangeEvent;

public class ScheduleAddTask extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JPanel panel = new JPanel();
	private JLabel lblTaskType;
	private JTextField textFieldTitle;
	
	private static TaskObserver taskDay;
	private static JLabel lblTaskStart;
	private static JLabel lblTaskEnd;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		try {
			ScheduleAddTask dialog = new ScheduleAddTask(new TaskObserver(LocalDate.now()));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ScheduleAddTask(TaskObserver observer) {
		this.taskDay = observer;
		makeGUI();
	}
	
	public void makeGUI() {
		setBounds(100, 100, 450, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 20));
		{
			lblTaskType = new JLabel("Task Type");
			contentPanel.add(lblTaskType);
		}
		
		
		String[] taskTypes = { "Break", "Shallow", "Deep" };
		JComboBox taskTypeSelect = new JComboBox(taskTypes);
		contentPanel.add(taskTypeSelect);
		
		lblTaskStart = new JLabel("Task Start");
		contentPanel.add(lblTaskStart);
		
		JSpinner startTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm");
		startTimeSpinner.setEditor(timeEditor);
		startTimeSpinner.setValue(new Date());
		contentPanel.add(startTimeSpinner);
		
		lblTaskEnd = new JLabel("Task End");
		contentPanel.add(lblTaskEnd);
		
		JSpinner endTimeSpinner = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
		endTimeSpinner.setEditor(timeEditor2);
		endTimeSpinner.setValue(new Date());
		contentPanel.add(endTimeSpinner);
	
		
		JLabel lblTaskTitle = new JLabel("Task Title");
		contentPanel.add(lblTaskTitle);
		
		textFieldTitle = new JTextField("Insert Title");
		
		contentPanel.add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		JLabel lblTaskTitle_1 = new JLabel("Task Description");
		contentPanel.add(lblTaskTitle_1);
		
		JTextArea textAreaDescription = new JTextArea();
		textAreaDescription.setText("Insert description here");
		contentPanel.add(textAreaDescription);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						{
							// TODO: create task object, add to DWT
							// TODO; validate task input, make sure no collisions
							// taskDay.add
						}
						
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
				
			}
		}
		getContentPane().add(panel, BorderLayout.NORTH);
		{
			JLabel lblAddTask = new JLabel("Add Task");
			panel.add(lblAddTask);
		}
	}
	
	
}

