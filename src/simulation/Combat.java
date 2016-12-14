package simulation;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class Combat {

	Main m = new Main();
	Player thePlayer = m.getThePlayer();
	JTextArea eventTextArea = GUIsetup.getEventTextArea();
	ArrayList<String> locations = new ArrayList();
	String action = "combat";
	Enemy theEnemy;

	public void setupCombat() {

	}

	public String start() {

		setupCombat();
		theEnemy = enemyGenerater();
		eventTextArea.append("\n" + thePlayer.getName() + " is entering combat against a " + theEnemy.getName() + "\n");
		eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
		delay();
		boolean fighting = true;

		while (fighting) {

			fighting = combat(theEnemy);

		}

		return action;

	}

	public boolean combat(Enemy theEnemy) {
		while (!m.isGlobalPause()) {
			int damage = 0;
			int playerHealth = thePlayer.getHealth();
			int playerMana = thePlayer.getMana();
			String attackType = thePlayer.combatDecision();
			String attackDescription = "";
			String currentWeapon = "";
			
			if (playerMana <= thePlayer.getMaxMana() * .25) {
				int mPotionsAvailable = thePlayer.getInventory().getManaPotions().size();
				if (mPotionsAvailable > 0) {
					int manaToRestore = thePlayer.getInventory().getManaPotions().get(0).getRestored();
					playerMana = playerMana + manaToRestore;
					thePlayer.getInventory().getManaPotions().remove(0);
					thePlayer.setMana(playerMana);
					thePlayer.statusChanged();
					eventTextArea.append(
							thePlayer.getName() + " used a mana potion! " + manaToRestore + " mana restored.\n");
					eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
					delay();
				}
			}
			
			if (thePlayer.getPlayerClass() == "Barbarian"){
				if (thePlayer.getInventory().getMeleeWeaponItemEquipped().getName() != "null")
					currentWeapon = thePlayer.getInventory().getMeleeWeaponItemEquipped().getName();
				else
					currentWeapon = "fist";
			}
			else if (thePlayer.getPlayerClass() == "Rogue"){
				if (thePlayer.getInventory().getRangedWeaponItemEquipped().getName() != "null")
					currentWeapon = thePlayer.getInventory().getRangedWeaponItemEquipped().getName();
				else
					currentWeapon = "bow";
			}else if (thePlayer.getPlayerClass() == "Sorcerer"){
				if (thePlayer.getInventory().getMagicWeaponItemEquipped().getName() != "null")
					currentWeapon = thePlayer.getInventory().getMagicWeaponItemEquipped().getName();
				else
					currentWeapon = "wand";
			}
			
			
			
			if (attackType == "melee"){
				damage = (int) thePlayer.meleeRoll(theEnemy.getDefenseRating(), theEnemy.getLevel());
				attackDescription = thePlayer.getName() + " did " + damage +" damage to the " + theEnemy.getName() + " with his " +currentWeapon;
			}
			else if (attackType == "ranged"){
				damage = (int) thePlayer.rangedRoll(theEnemy.getDefenseRating(), theEnemy.getLevel());
				attackDescription = thePlayer.getName() + " did " + damage +" damage to the " + theEnemy.getName() + " with his " +currentWeapon;
			}
			else if (attackType == "magic"){
				damage = (int) thePlayer.magicRoll(theEnemy.getDefenseRating(), theEnemy.getLevel());
				attackDescription = thePlayer.getName() + "" + thePlayer.getCurrentAbilityDescription() + "" + thePlayer.getCurrentAbility() +" and did " +damage +" damage to the " + theEnemy.getName();
				thePlayer.setMana(thePlayer.getMana() - thePlayer.getAbilityCost());
				
				thePlayer.statusChanged();
				}

			int dodgeChance = (int) theEnemy.getChanceToHit();

			int enemyDamage = theEnemy.meleeRoll(thePlayer.getDefenseRating(), thePlayer.getPlayerLevel());
			int enemyHealth = theEnemy.getHealth();
		
			enemyHealth = enemyHealth - damage;
			theEnemy.setHealth(enemyHealth);
			if (enemyHealth > 0) {

				eventTextArea.append(attackDescription + ". It has " + theEnemy.getHealth() + " health left\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				delay();

				eventTextArea.append("The " + theEnemy.getName() + " did " + enemyDamage + " damage to "
						+ thePlayer.getName() + "\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());

				delay();

				playerHealth -= enemyDamage;
				thePlayer.setHealth(playerHealth);
				thePlayer.statusChanged();
			}

			if (enemyHealth <= 0) {
				action = "traveling";
				eventTextArea
						.append(attackDescription + "\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				delay();
				eventTextArea.append(thePlayer.getName() + " killed the " + theEnemy.getName() + "\n\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				delay();

				int expAdded = theEnemy.getExperienceGiven();
				int currentXP = thePlayer.getExperienceEarned();
				thePlayer.setExperienceEarned(expAdded + currentXP);

				inventoryCheck();

				eventTextArea.append(thePlayer.getName() + " earned " + expAdded + " experience\n\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				thePlayer.statusChanged();
				delay();
				if (thePlayer.getExperienceEarned() >= thePlayer.getExperienceNeeded()) {
					thePlayer.levelUp();
					eventTextArea.append(
							thePlayer.getName() + " leveled up! He is now level " + thePlayer.getPlayerLevel() + "\n");
					eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
					thePlayer.statusChanged();
					delay();
				}

				action = "traveling";

				return false;
			}
			if (playerHealth <= thePlayer.getMaxHealth() * .4) {
				int potionsAvailable = thePlayer.getInventory().getHealthPotions().size();
				if (potionsAvailable > 0) {
					int healthToRestore = thePlayer.getInventory().getHealthPotions().get(0).getRestored();
					playerHealth = playerHealth + healthToRestore;
					thePlayer.getInventory().getHealthPotions().remove(0);
					thePlayer.setHealth(playerHealth);
					thePlayer.statusChanged();
					eventTextArea.append(
							thePlayer.getName() + " used a heath potion! " + healthToRestore + " health restored.\n");
					eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
					delay();

				}
				if (playerHealth <= thePlayer.getMaxHealth() * .25) {
					int fleeRoll = (int) (Math.random() * 10);
					if (fleeRoll != 0) {
						eventTextArea.append(
								thePlayer.getName() + " is fleeing from combat with the " + theEnemy.getName() + ".\n");
						eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
						delay();
						action = "returning home";
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	public Enemy enemyGenerater() {
		Enemy temp = null;
		int playerLevel = thePlayer.getPlayerLevel();
		if (playerLevel == 1) {
			temp = new Critter(playerLevel, thePlayer.getLocationType());
		}

		else if (playerLevel > 1 && playerLevel <= 4) {
			int pick = (int) (Math.random() * 100);
			if (pick > 60) {
				temp = new Beast(playerLevel, thePlayer.getLocationType());
			} else
				temp = new Critter(playerLevel, thePlayer.getLocationType());
		} else if (playerLevel > 4 && playerLevel <= 10) {
			int pick = (int) (Math.random() * 100);
			if (pick > 65) {
				temp = new LesserDemon(playerLevel, thePlayer.getLocationType());
			} else if (pick < 15)
				temp = new Critter(playerLevel, thePlayer.getLocationType());
			else
				temp = new Beast(playerLevel, thePlayer.getLocationType());
		} else if (playerLevel > 10 && playerLevel <= 20) {
			int pick = (int) (Math.random() * 100);
			if (pick > 75) {
				temp = new Demon(playerLevel, thePlayer.getLocationType());
			} else if (pick > 45 && pick <= 75)
				temp = new LesserDemon(playerLevel, thePlayer.getLocationType());
			else if ((pick > 15 && pick <= 45))
				temp = new Beast(playerLevel, thePlayer.getLocationType());
			else
				temp = new Critter(playerLevel, thePlayer.getLocationType());
		}

		m.writeEnemyToFile(temp);
		return temp;
	}

	public void inventoryCheck() {
		int goldAdded = theEnemy.getGoldDropped();
		int currentGold = thePlayer.getGold();
		thePlayer.setGold(currentGold + goldAdded);
		int maxInventory = thePlayer.getInventory().getMaxBackpackSize();
		int itemsInInventory = thePlayer.getInventory().getBackpack().size();
		int inventorySpaceAvailable = maxInventory - itemsInInventory;

		Item itemDropped = theEnemy.getItemDropped();
		Potion potionDropped = theEnemy.getPotionDropped();

		if (itemDropped == null && potionDropped == null) {
			eventTextArea.append("The " + theEnemy.getName() + " dropped " + goldAdded + " gold\n");
			eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
		}

		else if (itemDropped != null && potionDropped == null) {
			eventTextArea.append("The " + theEnemy.getName() + " dropped " + goldAdded + " gold and a "
					+ itemDropped.getName() + ".\n");
			eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
			delay();

			String addItemResult = thePlayer.getInventory().addItem(itemDropped);
			if (addItemResult == "equipped") {
				eventTextArea.append(thePlayer.getName() + " equipped the " + itemDropped.getName() + "\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				thePlayer.statusChanged();

			} else if (addItemResult == "added") {
				eventTextArea
						.append(thePlayer.getName() + " stored the " + itemDropped.getName() + " in the backpack\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());

			} else if (addItemResult == "both") {
				eventTextArea.append(thePlayer.getName() + " equipped the " + itemDropped.getName() + " and\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				int lastItemAdded = thePlayer.getInventory().getBackpack().size();
				eventTextArea
						.append("stored the " + thePlayer.getInventory().getBackpack().get(lastItemAdded - 1).getName()
								+ " in the backpack\n\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				thePlayer.statusChanged();

			}

			else if (addItemResult == "full") {
				eventTextArea.append(thePlayer.getName() + "'s backpack is full! Returning home to empty it out!\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());

			}

		} else if (itemDropped == null && potionDropped != null) {
			eventTextArea.append("The " + theEnemy.getName() + " dropped " + goldAdded + " gold and a "
					+ potionDropped.getName() + ".\n");
			eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
			delay();
			String potionType = potionDropped.getType();
			if (potionType == "health") {
				if (thePlayer.getInventory().getHealthPotions().size() < thePlayer.getInventory()
						.getMaxHealthPotion()) {
					thePlayer.getInventory().getHealthPotions().add(potionDropped);
					eventTextArea.append(thePlayer.getName() + " now has "
							+ thePlayer.getInventory().getHealthPotions().size() + " health potions.\n");
					eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				}
			} else if (potionType == "mana") {
				if (thePlayer.getInventory().getManaPotions().size() < thePlayer.getInventory().getMaxManaPotion()) {
					thePlayer.getInventory().getManaPotions().add(potionDropped);
					eventTextArea.append(thePlayer.getName() + " now has "
							+ thePlayer.getInventory().getManaPotions().size() + " mana potions.\n");
					eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				}

			}
		} else if (itemDropped != null && potionDropped != null) {
			eventTextArea.append("The " + theEnemy.getName() + " dropped " + goldAdded + " gold and a "
					+ potionDropped.getName() + ".\n");
			eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
			delay();
			String addItemResult = thePlayer.getInventory().addItem(itemDropped);
			if (addItemResult == "equipped") {
				eventTextArea.append(thePlayer.getName() + " equipped the " + itemDropped.getName() + "\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				thePlayer.statusChanged();

			} else if (addItemResult == "added") {
				eventTextArea
						.append(thePlayer.getName() + " stored the " + itemDropped.getName() + " in the backpack\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());

			} else if (addItemResult == "both") {
				eventTextArea.append(thePlayer.getName() + " equipped the " + itemDropped.getName() + " and\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				int lastItemAdded = thePlayer.getInventory().getBackpack().size();
				eventTextArea
						.append("stored the " + thePlayer.getInventory().getBackpack().get(lastItemAdded - 1).getName()
								+ " in the backpack\n\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				thePlayer.statusChanged();

			}

			else if (addItemResult == "full") {
				eventTextArea.append(thePlayer.getName() + "'s backpack is full! Returning home to empty it out!\n");
				eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
			}
			delay();

			String potionType = potionDropped.getType();
			if (potionType == "health") {
				if (thePlayer.getInventory().getHealthPotions().size() < thePlayer.getInventory()
						.getMaxHealthPotion()) {
					thePlayer.getInventory().getHealthPotions().add(potionDropped);
					eventTextArea.append(thePlayer.getName() + " now has "
							+ thePlayer.getInventory().getHealthPotions().size() + " health potions ");
					eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				}
			} else if (potionType == "mana") {
				if (thePlayer.getInventory().getManaPotions().size() < thePlayer.getInventory().getMaxManaPotion()) {
					thePlayer.getInventory().getManaPotions().add(potionDropped);
					eventTextArea.append(thePlayer.getName() + " now has "
							+ thePlayer.getInventory().getManaPotions().size() + " mana potions ");
					eventTextArea.setCaretPosition(eventTextArea.getDocument().getLength());
				}

			}

		}

		delay();

	}

	public void delay() {
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
