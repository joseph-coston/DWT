package gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;

public class MainWindow {

	private JFrame frame;
	public ScheduleView schedule;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		makeGUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void makeGUI() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		springLayout.putConstraint(SpringLayout.NORTH, tabbedPane, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, tabbedPane, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, tabbedPane, 560, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tabbedPane, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(tabbedPane);
		
		schedule = new ScheduleView();
		tabbedPane.addTab("Schedule", schedule);
		
		
		frame.setVisible(true);	
	}
}
