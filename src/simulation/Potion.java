package simulation;

public class Potion extends Item {

	private String name;
	private int restored;
	private String type;
	private int cost;

	private String minorHealth = "Minor Health Potion";
	private String normalHealth = "Health Potion";
	private String greaterHealth = "Greater Health Potion";
	private int minorHealthRestored = 25;
	private int normalHealthRestored = 75;
	private int greaterHealthRestored = 150;

	private String minorMana = "Minor Mana Potion";
	private String normalMana = "Mana Potion";
	private String greaterMana = "Greater Mana Potion";
	private int minorManaRestored = 25;
	private int normalManaRestored = 75;
	private int greaterManaRestored = 150;
	private int minorPotionCost = 25;
	private int normalPotionCost = 75;
	private int majorPotionCost = 150;
	
	public Potion healthPotion(int playerLevel) {
		Potion temp = new Potion();
		temp.type = "health";
		if (playerLevel <= 5) {
			temp.name = minorHealth;
			temp.restored = minorHealthRestored;
			temp.cost = minorPotionCost;
		} else if (playerLevel > 5 && playerLevel <= 10) {

			temp.name = normalHealth;
			temp.restored = normalHealthRestored;
			temp.cost = normalPotionCost;
		} else {
			temp.name = greaterHealth;
			temp.restored = greaterHealthRestored;
			temp.cost = majorPotionCost;
		}
		return temp;

	}
	
	public Potion manaPotion(int playerLevel) {
		Potion temp = new Potion();
		temp.type = "mana";
		if (playerLevel <= 5) {
			temp.name = minorMana;
			temp.restored = minorManaRestored;
			temp.cost = minorPotionCost;
		} else if (playerLevel > 5 && playerLevel <= 10) {

			temp.name = normalMana;
			temp.restored = normalManaRestored;
			temp.cost = normalPotionCost;
		} else {
			temp.name = greaterMana;
			temp.restored = greaterManaRestored;
			temp.cost = majorPotionCost;
		}
		return temp;

	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getMinorPotionCost() {
		return minorPotionCost;
	}

	public void setMinorPotionCost(int minorPotionCost) {
		this.minorPotionCost = minorPotionCost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRestored() {
		return restored;
	}

	public void setRestored(int restored) {
		this.restored = restored;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
