package simulation;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextArea;

public class InventoryUpdater implements Observer {
	
Observable observable;

	
	public InventoryUpdater(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}
	
	public void update(Observable obs, Object obj){
		
		Player playerInfo = (Player) obj;
		JTextArea gold = GUIsetup.getGoldField();
		String g = Integer.toString(playerInfo.getGold());
		gold.setText("                        " +g);
		
	}

}
