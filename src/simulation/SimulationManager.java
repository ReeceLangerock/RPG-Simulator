package simulation;

import javax.swing.JTextArea;

public class SimulationManager implements Runnable{
	static Main m = new Main();
	private static boolean paused = false;
	private static String currentAction;
	private static String lastAction;


	public void go() {
		Player thePlayer = m.getThePlayer();
		PlayerTravel pt = new PlayerTravel();
		Combat c = new Combat();
		currentAction = "traveling";
		ReturnHome rh = new ReturnHome();

		while (!paused) {
			while (currentAction == "traveling") {
				currentAction = pt.traveling();
			}
			while (currentAction == "combat") {
				currentAction = c.start();
			}
			while (currentAction == "returning home") {
				currentAction = rh.returningHome();
			}
		
			
		}
	}

	public static String getLastAction() {
		return lastAction;
	}

	public static void setLastAction(String lastAction) {
		SimulationManager.lastAction = lastAction;
	}

	public static String getCurrentAction() {
		return currentAction;
	}

	public static void setCurrentAction(String currentAction) {
		SimulationManager.currentAction = currentAction;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	@Override
	public void run() {
		go();
		
	}
}
