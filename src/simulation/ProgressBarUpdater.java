package simulation;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class ProgressBarUpdater implements Observer {

	Observable observable;

	
	public ProgressBarUpdater(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}
	
	public void update(Observable obs, Object obj){
		
		Player playerInfo = (Player) obj;
		ArrayList<JProgressBar> bars = GUIsetup.getBars();
		bars.get(0).setValue(playerInfo.getHealth());
		String h = Integer.toString(playerInfo.getHealth());
		bars.get(0).setMaximum(playerInfo.getMaxHealth());
		bars.get(0).setString(h);
		
		bars.get(1).setValue(playerInfo.getMana());
		String m = Integer.toString(playerInfo.getMana());
		bars.get(1).setMaximum(playerInfo.getMaxMana());
		bars.get(1).setString(m);
		
		bars.get(2).setValue(playerInfo.getStamina());
		String s = Integer.toString(playerInfo.getStamina());
		bars.get(2).setString(s);
		
		bars.get(3).setValue(playerInfo.getExperienceEarned());		
		bars.get(3).setMaximum(playerInfo.getExperienceNeeded());
		String eEarned = Integer.toString(playerInfo.getExperienceEarned());
		String eNeeded = Integer.toString(playerInfo.getExperienceNeeded());
		String eCombined = eEarned + " / " + eNeeded;
		bars.get(3).setString(eCombined);
		
		
		

	
}
	
	

}
