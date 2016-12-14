package simulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Main {

	private static boolean globalPause = false;
	public static boolean isGlobalPause() {
		return globalPause;
	}

	public static void setGlobalPause(boolean globalPause) {
		Main.globalPause = globalPause;
	}

	private static Player thePlayer = new Player();
	private static Runnable sim;
	private static Thread simThread;
	
	public static void main(String[] args){

		GUIsetup gui = new GUIsetup();
		gui.createAndShowGUI(thePlayer);
		Runnable sim = new SimulationManager();
		simThread = new Thread(sim);
		simThread.start();
	
	}
	
	
	public Player getThePlayer(){
		return thePlayer;
	}
	
	


	public static void pauseSim(){
		if (globalPause == false){
			globalPause = true;
		simThread.suspend();
		
		}
		else if (globalPause == true){
			globalPause = false;
			simThread.resume();
		}
	}
	public static Thread getSimThread() {
		return simThread;
	}
	
	public void writeEnemyToFile(Enemy e) {

		try {
			FileWriter writer = new FileWriter("Enemy Log.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(e.toString());
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
	}
	
	public void writeItemToFile(Item i) {

		try {
			FileWriter writer = new FileWriter("Item Log.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(i.toString());
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
	}

	public static void setSimThread(Thread simThread) {
		Main.simThread = simThread;
	}
	

}
