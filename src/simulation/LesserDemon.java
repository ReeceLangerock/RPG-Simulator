package simulation;

import java.util.ArrayList;

public class LesserDemon extends Enemy {

	private int health;
	private int experienceGiven;
	private int baseDamage;
	private int mana;
	private int stamina;
	private int level;
	private int dodgeChance;
	private String name;
	private int goldDropped;
	private int dexterity;
	private double attackRating;
	private double defenseRating;
	private double chanceToHit;
	private int strength;
	private Item itemDropped;
	private Potion potionDropped;
	private ArrayList<Item> items = new ArrayList();
	private String[] swampNameChoices = { "Frog Demon", "Thorned Hulk", "Willowisp", "Tentacle Beast", "Fetish Shaman",
			"Rotting Husk", "Festing Ghoul" };
	private String[] forestNameChoices = { "Skeleton Archer", "Skeleton Mage", "Skeleton", "Wraith", "Vampire",
			"Goatman", "Corrupt Rogue", "Fallen Shaman" };
	private String[] desertNameChoices = { "Scarab Demon", "Greater Mummy", "Sand Raider", "Mummy", "Bone Fetish",
			"Sand Maggot", "Vulture Demon", "Pain Worm" };
	private String[] frozenNameChoices = { "Frozen Horror", "Abominable", "Skeleton", "Ice Zombie", "Wight",
			"White Walker" };
	private String[] hauntedNameChoices = { "Skeleton Archer", "Skeleton Mage", "Skeleton", "Wraith", "Vampire",
			"Ghost" };

	public LesserDemon(int playerLevel, String location) {

		int levelSpread = (int) (Math.random() * 4);
		System.out.println(levelSpread);
		if (levelSpread == 0)
			level = playerLevel - 2;
		else if (levelSpread == 1)
			level = playerLevel - 1;
		else if (levelSpread == 2)
			level = playerLevel;
		else if (levelSpread == 3)
			level = playerLevel + 1;
		else if (levelSpread == 4)
			level = playerLevel + 2;

		if (level == 0)
			level++;

		health = (int) (((2 + Math.random() * 4) / level) + (level * ((6 + Math.random() * 6) * Math.floorMod(3, 15))));

		name = nameChooser(location);
		experienceGiven = (int) ((20 + (Math.random() * 30)) + Math.pow((level*2.25), 2));
		defenseRating = level * ((Math.log(3) / Math.log(10)) * (3 + Math.random() * 2));
		attackRating = level * ((Math.log(3) / Math.log(12)) * (4) + Math.random() * 3);
		goldDropped = (int) (3 + ((.5 * Math.pow(level, 2)) + (Math.random() * 6)));
		baseDamage = (int) ((5 + Math.random() * 5) + (attackRating * 3 * (Math.log(3) / Math.log(15))));
		int itemRoll = (int) (Math.random() * 50);
		if (itemRoll == 0)
			itemDropped = new Item().itemRoll(level);
		
		int potionRoll = (int) (Math.random() * 40);
		int potionPick = (int) (Math.random() * 2);
		if (potionRoll == 0)
		{
			if (potionPick == 0)
				potionDropped = new Potion().healthPotion(level);
			if (potionPick == 1)
				potionDropped = new Potion().manaPotion(level);
		}
	}

	public String nameChooser(String location) {

		String assignedName = "";
		int pickedName = 0;
		switch (location) {

		case "Desert":
			pickedName = (int) (Math.random() * desertNameChoices.length);
			assignedName = desertNameChoices[pickedName];
			break;

		case "Forest":
			pickedName = (int) (Math.random() * forestNameChoices.length);
			assignedName = forestNameChoices[pickedName];
			break;

		case "Swamp":
			pickedName = (int) (Math.random() * swampNameChoices.length);
			assignedName = swampNameChoices[pickedName];
			break;
		case "Frozen":
			pickedName = (int) (Math.random() * frozenNameChoices.length);
			assignedName = frozenNameChoices[pickedName];
			break;
		case "Haunted":
			pickedName = (int) (Math.random() * hauntedNameChoices.length);
			assignedName = hauntedNameChoices[pickedName];
			break;
		default:
			int temp = (int) (Math.random() * 4);
			if (temp == 0)
				assignedName = swampNameChoices[(int) (Math.random() * swampNameChoices.length)];
			else if (temp == 1)
				assignedName = forestNameChoices[(int) (Math.random() * forestNameChoices.length)];
			else if (temp == 2)
				assignedName = desertNameChoices[(int) (Math.random() * desertNameChoices.length)];
			else if (temp == 3)
				assignedName = frozenNameChoices[(int) (Math.random() * frozenNameChoices.length)];
			else if (temp == 4)
				assignedName = hauntedNameChoices[(int) (Math.random() * hauntedNameChoices.length)];

		}

		return assignedName;
	}

	public int meleeRoll(double defenseRatingPlayer, int playerLevel) {

		chanceToHit = (attackRating / (attackRating + defenseRatingPlayer)) * 2
				* ((double) playerLevel / ((double) level + (double) playerLevel));
		if (chanceToHit > .95)
			chanceToHit = .95;
		else if (chanceToHit < .05)
			chanceToHit = .05;
		baseDamage = (int) ((5 + Math.random() * 5) + (attackRating * 3 * (Math.log(3) / Math.log(15))));
		double damageDone = chanceToHit * baseDamage;
		int critRoll = (int) (Math.random() * 100);
		// if (critRoll <= critChance){
		// damageDone = damageDone *1.5;
		// }

		return (int) damageDone;
	}

	public void inventoryRoll() {
		int roll = (int) (Math.random() * 20);
		if (roll > 18) {
			items.add(new Item());
		}
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getExperienceGiven() {
		return experienceGiven;
	}

	public void setExperienceGiven(int experienceGiven) {
		this.experienceGiven = experienceGiven;
	}

	public int getBaseDamage() {
		return baseDamage;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getDodgeChance() {
		return dodgeChance;
	}

	public void setDodgeChance(int dodgeChance) {
		this.dodgeChance = dodgeChance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGoldDropped() {
		return goldDropped;
	}

	public void setGoldDropped(int goldDropped) {
		this.goldDropped = goldDropped;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public double getAttackRating() {
		return attackRating;
	}

	public void setAttackRating(double attackRating) {
		this.attackRating = attackRating;
	}

	public double getDefenseRating() {
		return defenseRating;
	}

	public void setDefenseRating(double defenseRating) {
		this.defenseRating = defenseRating;
	}

	public double getChanceToHit() {
		return chanceToHit;
	}

	public void setChanceToHit(double chanceToHit) {
		this.chanceToHit = chanceToHit;
	}

	public int getStrength() {
		return strength;
	}

	@Override
	public String toString() {
		return "Enemy [ name= " + name + ", health= " + health + ", experienceGiven= " + experienceGiven
				+ ", baseDamage= " + baseDamage + ", level= " + level + ", goldDropped= " + goldDropped
				+ ", attackRating= " + attackRating + ", defenseRating= " + defenseRating + "]";
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}
}
