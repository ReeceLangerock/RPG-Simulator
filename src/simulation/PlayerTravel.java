package simulation;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class PlayerTravel {
	Main m = new Main();
	Player thePlayer = m.getThePlayer();
	JTextArea eventTextArea = GUIsetup.getEventTextArea();
	Location location = new Location();
	String action = "traveling";

	public String traveling() {

		eventTextArea.append(thePlayer.getName() + " is leaving the " + thePlayer.getLocation() + "\n");
		eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
		delay();
		location = new Location();
		int distanceTraveled = thePlayer.getDistanceFromHome();

		while (true) {
			eventTextArea.append(thePlayer.getName() + " is " + location.getWalkingDescription() + " through the "
					+ location.getLocationDescription() + ". \n");
			eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
			if (distanceTraveled % 4 == 0) {
				potionRoll();
				healthAndManaRegain();
			}

			if (distanceTraveled % 5 == 0) {
				thePlayer.setLocation(locationCheck());
			}

			if (distanceTraveled % 7 == 0) {
				boolean combat = combatCheck();
				if (combat == true) {
					action = "combat";
					return action;
				}
			}
			if (distanceTraveled % 10 == 0) {
				String rollResult = itemRoll();
				if (rollResult != "traveling")
				return rollResult;
			}

			delay();

			distanceTraveled++;
			thePlayer.setDistanceFromHome(distanceTraveled);

		}

	}

	public void healthAndManaRegain() {

		int currentHealth = thePlayer.getHealth();
		int currentMana = thePlayer.getMana();
		if (currentHealth < thePlayer.getMaxHealth() * .75) {
			currentHealth++;
			thePlayer.setHealth(currentHealth);
			thePlayer.statusChanged();
		}
		if (currentMana < thePlayer.getMaxMana()*.6) {
			if ((Math.random() * 5) < 2) {
				currentMana++;
				thePlayer.setMana(currentMana);
				thePlayer.statusChanged();
			}
		}

	}

	public String itemRoll() {

		int rollChance = (int) (Math.random() * 25);
		if (rollChance == 0) {
			Item foundItem = new Item().itemRoll(thePlayer.getPlayerLevel());
			eventTextArea.append("\n"+ thePlayer.getName() + " found " + foundItem.getName() + "\n");
			eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
			delay();
			String addItemResult = thePlayer.getInventory().addItem(foundItem);
			if (addItemResult == "equipped") {
				eventTextArea.append(thePlayer.getName() + " equipped the " + foundItem.getName() + "\n\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				delay();
				
				thePlayer.statusChanged();
				return "traveling";
			} else if (addItemResult == "added") {
				eventTextArea.append(thePlayer.getName() + " stored the " + foundItem.getName() + " in the backpack\n\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				delay();
				return "traveling";

			} else if (addItemResult == "both") {
				eventTextArea.append(thePlayer.getName() + " equipped the " + foundItem.getName() + " and\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				delay();
				int lastItemAdded = thePlayer.getInventory().getBackpack().size();
				eventTextArea.append("stored the " + thePlayer.getInventory().getBackpack().get(lastItemAdded-1).getName()
						+ " in the backpack\n\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				delay();
				thePlayer.statusChanged();
				return "traveling";
			}

			else if (addItemResult == "full") {
				eventTextArea.append(thePlayer.getName() + "'s backpack is full! Returning home to empty it out!\n\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				delay();
				return "returning home";
			} else
				return "traveling";

		}
		return "traveling";
	}

	public void potionRoll() {

		int potionRoll = (int) (Math.random() * 15);
		if (potionRoll == 0) {
			int potionPick = (int) (Math.random() * 2);
			if (potionPick == 0 && thePlayer.getInventory().getHealthPotions().size() < thePlayer.getInventory()
					.getMaxHealthPotion()) {
				Potion newPotion = new Potion().healthPotion(thePlayer.getPlayerLevel());
				thePlayer.getInventory().getHealthPotions().add(newPotion);
				eventTextArea.append(thePlayer.getName() + " found a " + newPotion.getName() + "\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());

			} else if (potionPick == 1
					&& thePlayer.getInventory().getManaPotions().size() < thePlayer.getInventory().getMaxManaPotion()) {
				Potion newPotion = new Potion().manaPotion(thePlayer.getPlayerLevel());
				thePlayer.getInventory().getManaPotions().add(newPotion);
				eventTextArea.append(thePlayer.getName() + " found a " + newPotion.getName() + "\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				
			}

		}

	}

	public boolean combatCheck() {

		int enterCombat = (int) (Math.random() * 100);
		if (enterCombat > 55)
			return true;
		else
			return false;

	}

	public String locationCheck() {
		boolean newLoc;

		// random True/False
		int randomTF = (int) (Math.random() * 2);
		if (randomTF == 1)
			newLoc = false;
		else
			newLoc = true;

		if (newLoc) {
			location = new Location();
			delay();
			thePlayer.setLocation(location.getLocationDescription());
		
			thePlayer.setLocationType(location.getLocationType());
			eventTextArea.append("\n" + thePlayer.getName() + " is entering the " + thePlayer.getLocation() + "\n");
			eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());

			return location.getLocationDescription();
		} else {
			delay();
			return thePlayer.getLocation();

		}
	}

	public void delay() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
