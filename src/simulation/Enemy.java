package simulation;

import java.util.ArrayList;

public abstract class Enemy {

	private int health;
	private int experienceGiven;
	private int baseDamage;
	private int mana;
	private int level;
	private int stamina;
	private int dodgeChance;
	private int goldDropped;
	private double attackRating;
	private double defenseRating;
	private double chanceToHit;
	private ArrayList<Item> items = new ArrayList<Item>();
	String name;
	private Potion potionDropped;
	private Item itemDropped;
		
	
	public int meleeRoll(double defenseRatingPlayer, int playerLevel){		
		int damageDone = 0;
		return (int) damageDone;
	}
	
	public int getExperienceGiven() {
		return experienceGiven;
	}
	public void setExperienceGiven(int experienceGiven) {
		this.experienceGiven = experienceGiven;
	}
	
	
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
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
		// TODO Auto-generated method stub
		return 0;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setGoldDropped(int goldDropped) {
		this.goldDropped = goldDropped;
	}




	public double getChanceToHit() {
		return chanceToHit;
	}

	public void setChanceToHit(double chanceToHit) {
		this.chanceToHit = chanceToHit;
	}


	public int getBaseDamage() {
		return baseDamage;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
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
	
	public Item getItemDropped() {
		return itemDropped;
	}

	public void setItemDropped(Item itemDropped) {
		this.itemDropped = itemDropped;
	}

	public Potion getPotionDropped() {
		return potionDropped;
	}

	public void setPotionDropped(Potion potionDropped) {
		this.potionDropped = potionDropped;
	}

	@Override
	public String toString() {
		
		return "Enemy [ name= " + name + ", health= " + health + ", experienceGiven= " + experienceGiven + ", baseDamage= " + baseDamage
				+ ", level= " + level + ", goldDropped= " + goldDropped + ", attackRating= " + attackRating + ", defenseRating= " + defenseRating+ "]";
	}

	
	
	
	
	
}
