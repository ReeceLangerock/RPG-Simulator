package simulation;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextArea;

public class TextUpdater implements Observer {
	Observable observable;

	public TextUpdater(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}

	public void update(Observable obs, Object obj){
		
		JTextArea text = GUIsetup.getEventTextArea();
		for(int i = 0; i < 10; i++)
			text.append("\nAppending");
	}
		
}
