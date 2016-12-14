package simulation;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class ReturnHome {
	Main m = new Main();
	Player thePlayer = m.getThePlayer();
	JTextArea eventTextArea = GUIsetup.getEventTextArea();
	ArrayList<String> locations = new ArrayList();
	String action = "returning home";

	public String returningHome() {
		eventTextArea.append(thePlayer.getName() + " is returning home\n");
		eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());

		delay();
		int distanceTraveled = thePlayer.getDistanceFromHome();
		int distanceReduced = distanceTraveled / 10;
		if (distanceReduced <= 0)
			distanceReduced = 1;
		while (distanceTraveled > 0) {
			eventTextArea.append(thePlayer.getName() + " is going home. Currently " + thePlayer.getDistanceFromHome()
					+ " away from Home.\n");
			eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());

			distanceTraveled = distanceTraveled - distanceReduced;
			thePlayer.setDistanceFromHome(distanceTraveled);
			thePlayer.statusChanged();
			delay();
		}
		emptyBackpack();
		buyPotions();
		thePlayer.setDistanceFromHome(0);
		thePlayer.setLocation("Sanctuary");
		thePlayer.setHealth(thePlayer.getMaxHealth());
		thePlayer.setMana(thePlayer.getMaxMana());
		thePlayer.statusChanged();

		return "traveling";

	}

	public void emptyBackpack() {
		Inventory inventory = thePlayer.getInventory();
		ArrayList<Item> backpack = inventory.getBackpack();
		eventTextArea.append("\n" +thePlayer.getName() + " is empyting the backpack. \n");
		eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
		delay();
		for (int i = 0; i < backpack.size(); i++) {
			eventTextArea.append(thePlayer.getName() + " sold the " + backpack.get(i).getName() + " for "
					+ backpack.get(i).getGoldValue() + " gold\n");
			eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
			delay();
			thePlayer.setGold(thePlayer.getGold() + backpack.get(i).getGoldValue());
			thePlayer.statusChanged();

		}
		for (int i = 0; i < backpack.size(); i++)
			backpack.remove(i);

		eventTextArea.append(thePlayer.getName() + "'s backpack is now empty! \n");
		eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
		delay();
		
	}
	
	public void buyPotions(){
		
		eventTextArea.append("\n" + thePlayer.getName() + " is buying potions! \n");
		eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
		delay();
		int hpBought = 0;
		int mpBought = 0;
		int goldSpent = 0;
		int hpNeeded = thePlayer.getInventory().getMaxHealthPotion() - thePlayer.getInventory().getHealthPotions().size();
		int mpNeeded = thePlayer.getInventory().getMaxManaPotion() - thePlayer.getInventory().getManaPotions().size();
		
		while (true){
			Potion hp = new Potion().healthPotion(thePlayer.getPlayerLevel());
			if (thePlayer.getGold() >= hp.getCost() &&  thePlayer.getInventory().getHealthPotions().size() < thePlayer.getInventory()
					.getMaxHealthPotion()){
				thePlayer.getInventory().getHealthPotions().add(hp);
				hpBought++;
				thePlayer.setGold(thePlayer.getGold() - hp.getCost());
				goldSpent = goldSpent + hp.getCost();
				thePlayer.statusChanged();
			}			
			else 
				break;
		}
			while (true){
			
			Potion mp = new Potion().manaPotion(thePlayer.getPlayerLevel());
			if (thePlayer.getGold() >= mp.getCost() &&  thePlayer.getInventory().getManaPotions().size() < thePlayer.getInventory()
					.getMaxManaPotion()){
				thePlayer.getInventory().getManaPotions().add(mp);
				mpBought++;
				thePlayer.setGold(thePlayer.getGold() - mp.getCost());
				goldSpent = goldSpent + mp.getCost();
				thePlayer.statusChanged();
			}			
			else 
				break;
		
			}
		
			
			eventTextArea.append(thePlayer.getName() + " spent " + goldSpent + " gold 	and bought " + hpBought + " health potions and " + mpBought + " mana potions!\n\n");
			eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
			delay();
		
		
	}

	public void delay() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
