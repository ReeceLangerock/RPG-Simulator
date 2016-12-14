package simulation;

import java.util.ArrayList;

public class Inventory {
	Main m = new Main();
	private Player thePlayer;

	private Item headItemEquipped = new Item();
	private Item bodyItemEquipped = new Item();
	private Item legItemEquipped = new Item();
	private Item footItemEquipped = new Item();
	private Item handItemEquipped = new Item();
	private Item beltItemEquipped = new Item();
	private Item ringItemEquipped = new Item();
	private Item meleeWeaponItemEquipped = new Item();
	private Item rangedWeaponItemEquipped = new Item();
	private Item magicWeaponItemEquipped = new Item();
	private Item shieldItemEquipped = new Item();

	private int totalDexBonus = 0;
	private int totalStrBonus = 0;
	private int totalVitBonus = 0;
	private int totalEngBonus = 0;

	private int maxBackpackSize = 5;
	private ArrayList<Item> backpack = new ArrayList();

	private int maxHealthPotion = 4;
	private ArrayList<Potion> healthPotions = new ArrayList();

	private int maxManaPotion = 4;
	private ArrayList<Potion> manaPotions = new ArrayList();

	// Constructor - Set up player with 2 health and mana potions

	public Inventory() {
		Potion healthPotion1 = new Potion().healthPotion(1);
		Potion healthPotion2 = new Potion().healthPotion(1);
		Potion manaPotion1 = new Potion().healthPotion(1);
		Potion manaPotion2 = new Potion().healthPotion(1);
		healthPotions.add(healthPotion1);
		healthPotions.add(healthPotion1);
		thePlayer =  m.getThePlayer();

		manaPotions.add(manaPotion1);
		manaPotions.add(manaPotion1);

	}

	public String addItem(Item theItem) {
		// possible return strings: equipped (new item is equipped), added (new
		// item added to backpack), both (new item
		// equipped and old added to backpack) or full (item not equipped and no
		// room in backpack)
		String returnString = "";
		// possible item types - "head", "body", "leg", "foot", "hand", "belt",
		// "ring", "melee weapon", "ranged weapon", "magic weapon", "shield"
		thePlayer =  m.getThePlayer();
		String itemType = theItem.getItemType();
		switch (itemType) {
		case "head":
			if (headItemEquipped.getName() == "null") {
				headItemEquipped = theItem;
				returnString = "equipped";
			}

			else if (headItemEquipped.getItemBonusTotal() <= theItem.getItemBonusTotal()) {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(headItemEquipped);
					returnString = "both";
				} else {
					returnString = "full";
				}
				headItemEquipped = theItem;

			} else {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(theItem);
					returnString = "added";
				} else {
					returnString = "full";
				}
			}
			break;

		case "body":
			if (bodyItemEquipped.getName() == "null") {
				bodyItemEquipped = theItem;
				returnString = "equipped";
			}

			else if (bodyItemEquipped.getItemBonusTotal() <= theItem.getItemBonusTotal()) {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(bodyItemEquipped);
					returnString = "both";
				} else {
					returnString = "full";
				}
				bodyItemEquipped = theItem;

			} else {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(theItem);
					returnString = "added";
				} else {
					returnString = "full";
				}
			}
			break;
		case "leg":
			if (legItemEquipped.getName() == "null") {
				legItemEquipped = theItem;
				returnString = "equipped";
			}

			else if (legItemEquipped.getItemBonusTotal() <= theItem.getItemBonusTotal()) {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(legItemEquipped);
					returnString = "both";
				} else {
					returnString = "full";
				}
				legItemEquipped = theItem;

			} else {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(theItem);
					returnString = "added";
				} else {
					returnString = "full";
				}
			}
			break;
		case "foot":
			if (footItemEquipped.getName() == "null") {
				footItemEquipped = theItem;
				returnString = "equipped";
			}

			else if (footItemEquipped.getItemBonusTotal() <= theItem.getItemBonusTotal()) {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(footItemEquipped);
					returnString = "both";
				} else {
					returnString = "full";
				}
				footItemEquipped = theItem;

			} else {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(theItem);
					returnString = "added";
				} else {
					returnString = "full";
				}
			}
			break;
		case "hand":
			if (handItemEquipped.getName() == "null") {
				handItemEquipped = theItem;
				returnString = "equipped";
			}

			else if (handItemEquipped.getItemBonusTotal() <= theItem.getItemBonusTotal()) {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(handItemEquipped);
					returnString = "both";
				} else {
					returnString = "full";
				}
				handItemEquipped = theItem;

			} else {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(theItem);
					returnString = "added";
				} else {
					returnString = "full";
				}
			}
			break;
		case "belt":
			if (beltItemEquipped.getName() == "null") {
				beltItemEquipped = theItem;
				returnString = "equipped";
			}

			else if (beltItemEquipped.getItemBonusTotal() <= theItem.getItemBonusTotal()) {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(beltItemEquipped);
					returnString = "both";
				} else {
					returnString = "full";
				}
				beltItemEquipped = theItem;

			} else {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(theItem);
					returnString = "added";
				} else {
					returnString = "full";
				}
			}
			break;
		case "ring":
			if (ringItemEquipped.getName() == "null") {
				ringItemEquipped = theItem;
				returnString = "equipped";
			}

			else if (ringItemEquipped.getItemBonusTotal() <= theItem.getItemBonusTotal()) {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(ringItemEquipped);
					returnString = "both";
				} else {
					returnString = "full";
				}
				ringItemEquipped = theItem;

			} else {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(theItem);
					returnString = "added";
				} else {
					returnString = "full";
				}
			}
			break;
		case "melee weapon":
			System.out.println("rWeapon name : " + meleeWeaponItemEquipped.getName());
			System.out.println("Player Class: " +thePlayer.getPlayerClass());
			if (meleeWeaponItemEquipped.getName() == "null" && thePlayer.getPlayerClass() == "Barbarian") {
				meleeWeaponItemEquipped = theItem;
				returnString = "equipped";
			}

			else if (meleeWeaponItemEquipped.getItemBonusTotal() <= theItem.getItemBonusTotal()
					&& thePlayer.getPlayerClass() == "Barbarian") {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(meleeWeaponItemEquipped);
					returnString = "both";
				} else {
					returnString = "full";
				}
				meleeWeaponItemEquipped = theItem;

			} else {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(theItem);
					returnString = "added";
				} else {
					returnString = "full";
				}
			}
			break;
		case "ranged weapon":
			System.out.println("rWeapon name : " + meleeWeaponItemEquipped.getName());
			System.out.println("Player Class: " +thePlayer.getPlayerClass());
			if (rangedWeaponItemEquipped.getName() == "null" && thePlayer.getPlayerClass() == "Rogue") {
				rangedWeaponItemEquipped = theItem;
				returnString = "equipped";
			}

			else if (rangedWeaponItemEquipped.getItemBonusTotal() <= theItem.getItemBonusTotal()
					&& thePlayer.getPlayerClass() == "Rogue") {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(rangedWeaponItemEquipped);
					returnString = "both";
				} else {
					returnString = "full";
				}
				rangedWeaponItemEquipped = theItem;

			} else {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(theItem);
					returnString = "added";
				} else {
					returnString = "full";
				}
			}
			break;
		case "magic weapon":
			System.out.println("rWeapon name : " + meleeWeaponItemEquipped.getName());
			System.out.println("Player Class: " +thePlayer.getPlayerClass());
			if (magicWeaponItemEquipped.getName() == "null" && thePlayer.getPlayerClass() == "Sorcerer") {
				magicWeaponItemEquipped = theItem;
				returnString = "equipped";
			}

			else if (magicWeaponItemEquipped.getItemBonusTotal() <= theItem.getItemBonusTotal()
					&& thePlayer.getPlayerClass() == "Sorcerer") {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(magicWeaponItemEquipped);
					returnString = "both";
				} else {
					returnString = "full";
				}
				magicWeaponItemEquipped = theItem;

			} else {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(theItem);
					returnString = "added";
				} else {
					returnString = "full";
				}
			}
			break;
		case "shield":
			if (shieldItemEquipped.getName() == "null") {
				shieldItemEquipped = theItem;
				returnString = "equipped";
			}

			else if (shieldItemEquipped.getItemBonusTotal() <= theItem.getItemBonusTotal()) {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(shieldItemEquipped);
					returnString = "both";
				} else {
					returnString = "full";
				}
				shieldItemEquipped = theItem;

			} else {
				if (backpack.size() < maxBackpackSize) {
					backpack.add(theItem);
					returnString = "added";
				} else {
					returnString = "full";
				}
			}
			break;

		}
		calculateBonuses();
		return returnString;

	}

	public Item getMeleeWeaponItemEquipped() {
		return meleeWeaponItemEquipped;
	}

	public void setMeleeWeaponItemEquipped(Item meleeWeaponItemEquipped) {
		this.meleeWeaponItemEquipped = meleeWeaponItemEquipped;
	}

	public Item getRangedWeaponItemEquipped() {
		return rangedWeaponItemEquipped;
	}

	public void setRangedWeaponItemEquipped(Item rangedWeaponItemEquipped) {
		this.rangedWeaponItemEquipped = rangedWeaponItemEquipped;
	}

	public Item getMagicWeaponItemEquipped() {
		return magicWeaponItemEquipped;
	}

	public void setMagicWeaponItemEquipped(Item magicWeaponItemEquipped) {
		this.magicWeaponItemEquipped = magicWeaponItemEquipped;
	}

	public void calculateBonuses() {
		totalDexBonus = headItemEquipped.getDexterityBonus() + bodyItemEquipped.getDexterityBonus()
				+ legItemEquipped.getDexterityBonus() + footItemEquipped.getDexterityBonus()
				+ handItemEquipped.getDexterityBonus() + beltItemEquipped.getDexterityBonus()
				+ ringItemEquipped.getDexterityBonus() + meleeWeaponItemEquipped.getDexterityBonus()
				+ rangedWeaponItemEquipped.getDexterityBonus() + magicWeaponItemEquipped.getDexterityBonus()
				+ shieldItemEquipped.getDexterityBonus();

		totalStrBonus = headItemEquipped.getStrengthBonus() + bodyItemEquipped.getStrengthBonus()
				+ legItemEquipped.getStrengthBonus() + footItemEquipped.getStrengthBonus()
				+ handItemEquipped.getStrengthBonus() + beltItemEquipped.getStrengthBonus()
				+ ringItemEquipped.getStrengthBonus() + meleeWeaponItemEquipped.getStrengthBonus()
				+ rangedWeaponItemEquipped.getStrengthBonus() + magicWeaponItemEquipped.getStrengthBonus()
				+ shieldItemEquipped.getStrengthBonus();

		totalVitBonus = headItemEquipped.getVitalityBonus() + bodyItemEquipped.getVitalityBonus()
				+ legItemEquipped.getVitalityBonus() + footItemEquipped.getVitalityBonus()
				+ handItemEquipped.getVitalityBonus() + beltItemEquipped.getVitalityBonus()
				+ ringItemEquipped.getVitalityBonus() + meleeWeaponItemEquipped.getVitalityBonus()
				+ rangedWeaponItemEquipped.getVitalityBonus() + magicWeaponItemEquipped.getVitalityBonus()
				+ shieldItemEquipped.getVitalityBonus();
		totalEngBonus = headItemEquipped.getEnergyBonus() + bodyItemEquipped.getEnergyBonus()
				+ legItemEquipped.getEnergyBonus() + footItemEquipped.getEnergyBonus()
				+ handItemEquipped.getEnergyBonus() + beltItemEquipped.getEnergyBonus()
				+ ringItemEquipped.getEnergyBonus() + meleeWeaponItemEquipped.getEnergyBonus()
				+ rangedWeaponItemEquipped.getEnergyBonus() + magicWeaponItemEquipped.getEnergyBonus()
				+ shieldItemEquipped.getEnergyBonus();
	}

	// -------------------- Getters and Setters ----------------

	public int getMaxBackpackSize() {
		return maxBackpackSize;
	}

	public void setMaxBackpackSize(int maxBackpackSize) {
		this.maxBackpackSize = maxBackpackSize;
	}

	public ArrayList<Item> getBackpack() {
		return backpack;
	}

	public void setBackpack(ArrayList<Item> backpack) {
		this.backpack = backpack;
	}

	public int getMaxHealthPotion() {
		return maxHealthPotion;
	}

	public void setMaxHealthPotion(int maxHealthPotion) {
		this.maxHealthPotion = maxHealthPotion;
	}

	public int getMaxManaPotion() {
		return maxManaPotion;
	}

	public void setMaxManaPotion(int maxManaPotion) {
		this.maxManaPotion = maxManaPotion;
	}

	public ArrayList<Potion> getHealthPotions() {
		return healthPotions;
	}

	public void setHealthPotions(ArrayList<Potion> healthPotions) {
		this.healthPotions = healthPotions;
	}

	public ArrayList<Potion> getManaPotions() {
		return manaPotions;
	}

	public void setManaPotions(ArrayList<Potion> manaPotions) {
		this.manaPotions = manaPotions;
	}

	public int getTotalDexBonus() {
		return totalDexBonus;
	}

	public void setTotalDexBonus(int totalDexBonus) {
		this.totalDexBonus = totalDexBonus;
	}

	public int getTotalStrBonus() {
		return totalStrBonus;
	}

	public void setTotalStrBonus(int totalStrBonus) {
		this.totalStrBonus = totalStrBonus;
	}

	public int getTotalVitBonus() {
		return totalVitBonus;
	}

	public void setTotalVitBonus(int totalVitBonus) {
		this.totalVitBonus = totalVitBonus;
	}

	public int getTotalEngBonus() {
		return totalEngBonus;
	}

	public void setTotalEngBonus(int totalEngBonus) {
		this.totalEngBonus = totalEngBonus;
	}

}
