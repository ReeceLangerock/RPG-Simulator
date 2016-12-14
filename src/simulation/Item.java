package simulation;

import java.util.ArrayList;

public class Item {

	// Item Types
	private String headItem[] = { "Cap", "Skull Cap", "Helm", "Helmet", "Great Helm", "Bone Helm", "Crown", "Full Helm",
			"Mask", "Spired Helm", "Bone Visage" };
	private String bodyItem[] = { "Cuirrass", "Armor", "Scale Mail", "Ring Mail", "Studded Armor", "Chain Mail",
			"Breast Plate", "Splint Mail", "Light Plate", "Field Plate", " Gothic Plate", "Plate Mail",
			"Full Plate Mail", "Ancient Armor" };
	private String legItem[] = { "Greaves", "Cuisses", "Breeches", "Leg Plate", "Leggings", "Leg Guards", "Pants",
			"War Greaves", "Light Leg Plate" };
	private String footItem[] = { "Boots", "Mesh Boots", "War Boots", "Battle Boots", "Heavy Boots", "Chain Boots",
			"Light Plated Boots", "Greaves" };
	private String handItem[] = { "Gloves", "Heavy Gloves", "Chain Gloves", "Light Gauntlets", "Gauntlets",
			"Heavy Gauntlets", "Bracers", "War Gauntlets", "Heavy Bracers" };
	private String beltItem[] = { "Belt", "Sash", "Light Belt", "Heavy Belt", "Plated Belt", "Mesh Belt", "War Belt" };
	private String ringItem[] = { "Ring", "Amulet", "Charm", "Band" };
	private String meleeWeaponItem[] = { "Hammer", "Axe", "Dagger", "Mace", "Polearm", "Spear", "Sword", "Cutlass",
			"Rune Sword", "Crystal Sword", "Gladius", "Gothic Sword", "Claymore", "Sabre", "Bastard Sword",
			"Falchion" };
	private String rangedWeaponItem[] = { "Bow", "Edge Bow", "Razor Bow", "Double Bow", "Short Siege Bow",
			"Large Siege Bow", "Ruen Bow", "Gothic Bow", "Crossbow", "Arbalest", "Ballista", "Seige Crossbow" };

	private String magicWeaponItem[] = { "Wand", "Petrified Wand", "Tomb Wand", "Grave Wand", "Yew Wand", "Grim Wamd",
			"Burnt Wand" };
	private String shieldItem[] = { "Buckler", "Small Shield", "Large Shield", "Kite Shield", "Spike Shield",
			"Tower Shield", "Bone Shield", "Gothic Shield" };

	private String itemTypes[] = { "head", "body", "leg", "foot", "hand", "belt", "ring", "melee weapon",
			"ranged weapon", "magic weapon", "shield" };

	// Item name Modifiers
	private String minorPrefixes[] = { "Fine", "Jagged", "Septic", "Snowy", "Fiery", "Smoldering", "Static", "Lunar",
			"Arcadian", "Lord's", "Sturdy", "Strong" };
	private String normalPrefixes[] = { "Silver", "Gold", "Platinum", "Shivering", "Boreal", "Smoking", "Glowing",
			"Buzzing", "Unearthly", "Astral", "King's", "Glorious", "Blessed" };
	private String greaterPrefixes[] = { "Meteoric", "Strange", "Weird", "Hibernal", "Flaming", "Consdensing", "Arcing",
			"Shocking", "Elysian", "Celestial", "Master's", "Saintly", "Holy", "Godly" };

	private String minorSuffixes[] = { "of Light", "of Worth", "of Measure", "of Craftsmanship", "of Quality",
			"of Flame", "of Fire", "of Frost", "of Shock", "of the Bat", "of Readiness", "of Balance", "of Equilibrium",
			"of Chance" };
	private String normalSuffixes[] = { "of Radiance", "of Excellence", "of Maiming", "of Slaying", "of Gore",
			"of Carnage", "of Burning", "of Icicle", "of Glacier", "of Lighning", "of the Wraith", "of Alacrity",
			"of Stability", "of Greed", "of Fortune" };
	private String greaterSuffixes[] = { "of the Sun", "of Performance", "of Transcendence", "of Slaughter",
			"of Butchery", "of Evisceration", "of Incineration", "of Winter", "of Storms", "of Thunder",
			"of the Vampire", "of Swiftness", "of Quickness", "of Luck" };

	// variables for generated items

	private String name;
	private String itemType;
	private ArrayList<Integer> bonusOptions = new ArrayList();
	private ArrayList<Integer> bonuses = new ArrayList();
	private int dexterityBonus;
	private int strengthBonus;
	private int vitalityBonus;
	private int energyBonus;
	private int itemBonusTotal;
	private int goldValue;

	public Item() {
		name = "null";
		itemType = "null";
		bonuses = new ArrayList();
		dexterityBonus = 0;
		strengthBonus = 0;
		vitalityBonus = 0;
		energyBonus = 0;
		itemBonusTotal = 0;
		goldValue = 0;
	}

	public Item itemRoll(int playerLevel) {
		for (int i = 0; i < 4; i++)
			bonusOptions.add(i);

		Item foundItem = new Item();
		int randomBonus1 = (int) (Math.random() * bonusOptions.size());
		bonusOptions.remove(randomBonus1);
		bonuses.add(randomBonus1);
		if (playerLevel >= 5) {
			int randomBonus2 = (int) (Math.random() * bonusOptions.size());
			bonusOptions.remove(randomBonus2);
			bonuses.add(randomBonus2);
		}
		if (playerLevel >= 10) {
		int randomBonus3 = (int) (Math.random() * bonusOptions.size());
		bonusOptions.remove(randomBonus3);
		bonuses.add(randomBonus3);
		}

		int randomItemType = (int) (Math.random() * itemTypes.length);
		foundItem.itemType = itemTypes[randomItemType];
		System.out.println(foundItem.itemType);
		switch (foundItem.itemType) {
		case "head":
			foundItem.name = headItem[(int) (Math.random() * headItem.length)];
			break;
		case "body":
			foundItem.name = bodyItem[(int) (Math.random() * bodyItem.length)];
			break;
		case "leg":
			foundItem.name = legItem[(int) (Math.random() * legItem.length)];
			break;
		case "hand":
			foundItem.name = handItem[(int) (Math.random() * handItem.length)];
			break;
		case "foot":
			foundItem.name = footItem[(int) (Math.random() * footItem.length)];
			break;
		case "belt":
			foundItem.name = beltItem[(int) (Math.random() * beltItem.length)];
			break;
		case "ring":
			foundItem.name = ringItem[(int) (Math.random() * ringItem.length)];
			break;
		case "melee weapon":
			foundItem.name = meleeWeaponItem[(int) (Math.random() * meleeWeaponItem.length)];
			break;
		case "ranged weapon":
			foundItem.name = rangedWeaponItem[(int) (Math.random() * rangedWeaponItem.length)];
			break;
		case "magic weapon":
			foundItem.name = magicWeaponItem[(int) (Math.random() * magicWeaponItem.length)];
			break;
		case "shield":
			foundItem.name = shieldItem[(int) (Math.random() * shieldItem.length)];
			break;

		}

		if (playerLevel < 5) {
			for (Integer i : bonuses) {
				switch (i) {
				case 0:
					foundItem.dexterityBonus = (int) (1 + (Math.random() * 2));
					break;
				case 1:
					foundItem.strengthBonus = (int) (1 + (Math.random() * 2));
					break;
				case 2:
					foundItem.vitalityBonus = (int) (1 + (Math.random() * 2));
					break;
				case 3:
					foundItem.energyBonus = (int) (1 + (Math.random() * 2));
					break;
				}
			}

			int randomPrefix = (int) (Math.random() * minorPrefixes.length);
			int randomSuffix = (int) (Math.random() * minorSuffixes.length);
			foundItem.name = minorPrefixes[randomPrefix] + " " + foundItem.name + " " + minorSuffixes[randomSuffix];
		}

		else if (playerLevel > 5 && playerLevel <= 10) {
			for (Integer i : bonuses) {
				switch (i) {
				case 0:
					foundItem.dexterityBonus = (int) (2 + (Math.random() * 2));
					break;
				case 1:
					foundItem.strengthBonus = (int) (2 + (Math.random() * 2));
					break;
				case 2:
					foundItem.vitalityBonus = (int) (2 + (Math.random() * 2));
					break;
				case 3:
					foundItem.energyBonus = (int) (2 + (Math.random() * 2));
					break;
				}
			}
			int randomPrefix = (int) (Math.random() * normalPrefixes.length);
			int randomSuffix = (int) (Math.random() * normalSuffixes.length);
			foundItem.name = normalPrefixes[randomPrefix] + " " + foundItem.name + " " + normalSuffixes[randomSuffix];
		} else if (playerLevel > 10 && playerLevel <= 20) {
			int randomChance = (int) (Math.random() * 2);
			if (randomChance == 0) {
				for (Integer i : bonuses) {
					switch (i) {
					case 0:
						foundItem.dexterityBonus = (int) (2 + (Math.random() * 3));
						break;
					case 1:
						foundItem.strengthBonus = (int) (2 + (Math.random() * 3));
						break;
					case 2:
						foundItem.vitalityBonus = (int) (2 + (Math.random() * 3));
						break;
					case 3:
						foundItem.energyBonus = (int) (2 + (Math.random() * 3));
						break;
					}
				}
				int randomPrefix = (int) (Math.random() * normalPrefixes.length);
				int randomSuffix = (int) (Math.random() * normalSuffixes.length);
				foundItem.name = normalPrefixes[randomPrefix] + " " + foundItem.name + " "
						+ normalSuffixes[randomSuffix];
			} else {
				for (Integer i : bonuses) {
					switch (i) {
					case 0:
						foundItem.dexterityBonus = (int) (3 + (Math.random() * 4));
						break;
					case 1:
						foundItem.strengthBonus = (int) (3 + (Math.random() * 4));
						break;
					case 2:
						foundItem.vitalityBonus = (int) (3 + (Math.random() * 4));
						break;
					case 3:
						foundItem.energyBonus = (int) (3 + (Math.random() * 4));
						break;
					}
				}
				int randomPrefix = (int) (Math.random() * greaterPrefixes.length);
				int randomSuffix = (int) (Math.random() * greaterSuffixes.length);
				foundItem.name = greaterPrefixes[randomPrefix] + " " + foundItem.name + " "
						+ greaterSuffixes[randomSuffix];
			}

		} else {
			for (Integer i : bonuses) {
				switch (i) {
				case 0:
					foundItem.dexterityBonus = (int) (4 + (Math.random() * 5));
					break;
				case 1:
					foundItem.strengthBonus = (int) (4 + (Math.random() * 5));
					break;
				case 2:
					foundItem.vitalityBonus = (int) (4 + (Math.random() * 5));
					break;
				case 3:
					foundItem.energyBonus = (int) (4 + (Math.random() * 5));
					break;
				}
			}
			int randomPrefix = (int) (Math.random() * greaterPrefixes.length);
			int randomSuffix = (int) (Math.random() * greaterSuffixes.length);
			foundItem.name = greaterPrefixes[randomPrefix] + " " + foundItem.name + " " + greaterSuffixes[randomSuffix];
		}
		foundItem.itemBonusTotal = foundItem.dexterityBonus + foundItem.strengthBonus + foundItem.energyBonus
				+ foundItem.vitalityBonus;
		foundItem.goldValue = (int) ((1+Math.random()*10)*(playerLevel * foundItem.itemBonusTotal));
		System.out.println("Item Type " + foundItem.itemType + " -- Item Name " + foundItem.name);
		Main m = new Main();
		m.writeItemToFile(foundItem);
		return foundItem;

	}

	public int getGoldValue() {
		return goldValue;
	}

	public void setGoldValue(int goldValue) {
		this.goldValue = goldValue;
	}

	public void enemyItemRoll(int playerLevel) {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public int getDexterityBonus() {
		return dexterityBonus;
	}

	public void setDexterityBonus(int dexterityBonus) {
		this.dexterityBonus = dexterityBonus;
	}

	public int getStrengthBonus() {
		return strengthBonus;
	}

	public void setStrengthBonus(int strengthBonus) {
		this.strengthBonus = strengthBonus;
	}

	public int getVitalityBonus() {
		return vitalityBonus;
	}

	public void setVitalityBonus(int vitalityBonus) {
		this.vitalityBonus = vitalityBonus;
	}

	public int getEnergyBonus() {
		return energyBonus;
	}

	public void setEnergyBonus(int energyBonus) {
		this.energyBonus = energyBonus;
	}

	public int getItemBonusTotal() {
		return itemBonusTotal;
	}

	public void setItemBonusTotal(int itemBonusTotal) {
		this.itemBonusTotal = itemBonusTotal;
	}

	@Override
	public String toString() {
		return "Item [name= " + name + ", itemType= " + itemType + ", dexterityBonus= " + dexterityBonus
				+ ", strengthBonus= " + strengthBonus + ", vitalityBonus= " + vitalityBonus + ", energyBonus= "
				+ energyBonus + ", goldValue=" + goldValue + "]";
	}

}
