package simulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class GUIsetup {
	private Player thePlayer;
	public static ArrayList<JProgressBar> bars = new ArrayList();
	// public ProgressBarUpdater pbu = new ProgressBarUpdater(thePlayer);
	// public InventoryUpdater iu = new InventoryUpdater(thePlayer);
	// public TextUpdater tu = new TextUpdater(thePlayer);
	JProgressBar healthBar, manaBar, staminaBar, experienceBar;
	public static JTextArea goldField = new JTextArea();
	public static JTextArea eventTextArea = new JTextArea();
	Main m = new Main();

	public void createAndShowGUI(Player thePlayer) {
		this.thePlayer = thePlayer;
		final ProgressBarUpdater pbu = new ProgressBarUpdater(thePlayer);
		final InventoryUpdater iu = new InventoryUpdater(thePlayer);
		JFrame frame = new JFrame();
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setTitle("Adventure Sim");

		GUIsetup demo = new GUIsetup();
		frame.setContentPane(demo.start());

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		thePlayer.statusChanged();

	}

	public JPanel start() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(5, 5));
		mainPanel.setOpaque(true);

		JPanel simPanel = textPanel();
		simPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(simPanel, BorderLayout.CENTER);

		JPanel infoPanel = createInfoPanel();
		mainPanel.add(infoPanel, BorderLayout.EAST);

		JPanel buttonPanel = createButtonPanel();
		buttonPanel.add(Box.createRigidArea(new Dimension(5, 5)));
		mainPanel.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);

		return mainPanel;
	}

	public JPanel createInfoPanel() {

		thePlayer = m.getThePlayer();
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(5, 2, 5, 5));

		JLabel healthLabel = new JLabel();
		healthLabel.setText("Health");
		int healthBarMax = thePlayer.getHealth();
		healthBar = new JProgressBar(0, healthBarMax);
		healthBar.setStringPainted(true);
		bars.add(healthBar);
		infoPanel.add(healthLabel);
		infoPanel.add(healthBar);

		JLabel manaLabel = new JLabel();
		manaLabel.setText("Mana");
		int manaBarMax = thePlayer.getMana();
		JProgressBar manaBar = new JProgressBar(0, manaBarMax);
		manaBar.setStringPainted(true);
		infoPanel.add(manaLabel);
		infoPanel.add(manaBar);
		bars.add(manaBar);

		JLabel staminaLabel = new JLabel();
		staminaLabel.setText("Stamina");
		staminaBar = new JProgressBar(0, 100);
		staminaBar.setStringPainted(true);
		bars.add(staminaBar);
		infoPanel.add(staminaLabel);
		infoPanel.add(staminaBar);

		JLabel goldLabel = new JLabel();
		goldLabel.setText("Gold");
		goldField = new JTextArea();
		goldField.setEditable(false);
		infoPanel.add(goldLabel);
		infoPanel.add(goldField);

		JLabel experienceLabel = new JLabel();
		experienceLabel.setText("Experience");
		System.out.println(thePlayer.getExperienceNeeded());
		experienceBar = new JProgressBar(0, 100);
		bars.add(experienceBar);
		experienceBar.setStringPainted(true);
		infoPanel.add(experienceLabel);
		infoPanel.add(experienceBar);

		return infoPanel;
	}

	public JPanel createButtonPanel() {

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 3, 5, 5));

		JButton returnButton = new JButton("Return");
		JButton pauseButton = new JButton("Pause");
		JButton quitButton = new JButton("Quit");
		buttonPanel.add(Box.createVerticalGlue());
		buttonPanel.add(returnButton);
		buttonPanel.add(pauseButton);

		 pauseButton.addActionListener(new pauseButtonListener());
		buttonPanel.add(quitButton);
		buttonPanel.add(Box.createVerticalGlue());

		return buttonPanel;
	}

	public class pauseButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			m.pauseSim();
	}}

	public JPanel textPanel() {

		JScrollPane scroller = new JScrollPane();

		JPanel textPanel = new JPanel();
		textPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		eventTextArea = new JTextArea();
		eventTextArea.setLineWrap(false);
		eventTextArea.setText("");
		scroller.setVisible(true);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setPreferredSize(new Dimension(525, 550));
		scroller.setViewportView(eventTextArea);
		scroller.setAutoscrolls(true);
		textPanel.add(scroller);
		return textPanel;
	}

	public static ArrayList getBars() {
		return bars;
	}

	public static JTextArea getGoldField() {
		return goldField;
	}

	public static JTextArea getEventTextArea() {
		return eventTextArea;
	}
}
