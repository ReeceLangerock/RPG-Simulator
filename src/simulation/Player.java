package simulation;

import java.util.ArrayList;
import java.util.Observable;

public class Player extends Observable {

	private static Inventory inventory = new Inventory();
	private PlayerCombat combat = new PlayerCombat();
	private String name = "";

	// attributes

	private int strength = 0;
	private int dexterity = 0;
	private int vitality = 0;
	private int energy = 0;
	private int strengthBonus = 0;
	private int dexterityBonus = 0;
	private int vitalityBonus = 0;
	private int energyBonus = 0;
	private int combinedStrength = 0;
	private int combinedDexterity = 0;
	private int combinedVitality = 0;
	private int combinedEnergy = 0;

	private float energyMultiplier;
	private float vitalityMultiplier;
	private float dexIncrease = 0;
	private float strIncrease = 0;
	private int baseHealth;
	private int health = 0; // life
	private int maxHealth;
	private int baseMana;
	private int mana = 0;
	private int maxMana;
	private int stamina = 0;
	private int playerLevel = 1;
	private int critChance = 0;
	private int hitFactor = 0;
	private String playerClass = "";

	private double attackRating = 0;
	private double defenseRating = 0;
	private double chanceToHit = 0;

	private int experienceEarned = 0;
	private int experienceNeeded = 0;
	private int distanceTraveled = 0;
	private int distanceFromHome = 0;
	private int gold = 50;
	private String location = "Sanctuary";
	private String locationType = "";
	private String currentAbility = "";
	private String currentAbilityDescription = "";
	private String attackDescription = "";
	private int abilityCost = 0;

	public Player() {
		selectClass();
		calculateExpNeeded();
		calculateRatings();
	}

	public void selectClass() {
		int choice = (int) (Math.random() * 3);
		System.out.println(choice);
		switch (choice) {

		case 0:
			playerClass = "Barbarian";
			name = "The Barbarian";
			currentAbilityDescription = " used ";
			
			strength = 30;
			dexterity = 20;
			vitality = 25;
			energy = 10;
			baseMana = 10;
			baseHealth = 70;
			energyMultiplier = 1;
			vitalityMultiplier = 4;
			dexIncrease = 2;
			strIncrease = 4;

			maxMana = (int) (baseMana + (energyMultiplier * energy));
			maxHealth = (int) (baseHealth + (vitalityMultiplier * vitality));
			health = maxHealth;
			mana = maxMana;
			break;
		case 1:
			playerClass = "Rogue";
			name = "The Rogue";
			currentAbilityDescription = " shot a ";
			strength = 20;
			dexterity = 30;
			vitality = 20;
			energy = 15;
			baseMana = 22;
			baseHealth = 45;
			energyMultiplier = (float) 2.5;
			vitalityMultiplier = (float) 2.5;
			dexIncrease = 3;
			strIncrease = 3;

			maxMana = (int) (baseMana + (energyMultiplier * energy));
			maxHealth = (int) (baseHealth + (vitalityMultiplier * vitality));
			health = maxHealth;
			mana = maxMana;
			break;
		case 2:
			playerClass = "Sorcerer";
			name = "The Sorcerer";
			currentAbilityDescription = " cast ";
			strength = 15;
			dexterity = 30;
			vitality = 20;
			baseHealth = 30;
			baseMana = 70;
			energy = 35;
			energyMultiplier = 3;
			vitalityMultiplier = 2;
			dexIncrease = 4;
			strIncrease = 2;

			maxMana = (int) (baseMana + (energyMultiplier * energy));
			maxHealth = (int) (baseHealth + (vitalityMultiplier * vitality));
			health = maxHealth;
			mana = maxMana;
			break;
		}

	}

	public void calculateExpNeeded() {
		experienceNeeded = (int) (-0.4 * Math.pow(playerLevel, 3) + (40.4 * Math.pow(playerLevel, 2))
				+ (350 * playerLevel));
		experienceNeeded = (experienceNeeded / 100) * 100;

	}

	public void levelUp() {
		playerLevel++;
		experienceEarned = experienceEarned - experienceNeeded;
		dexterity = (int) (dexterity + dexIncrease);
		vitality = vitality + 4;
		energy = energy + 4;
		strength = (int) (strength + strIncrease);
		maxHealth = (int) (baseHealth + ((vitalityBonus + vitality) * vitalityMultiplier));
		maxMana = (int) (baseMana + ((energyBonus + energy) * energyMultiplier));
		health = maxHealth;
		mana = maxMana;
		
		if (playerLevel % 5 == 0){
			inventory.setMaxBackpackSize(inventory.getMaxBackpackSize()+1);
			inventory.setMaxManaPotion(inventory.getMaxManaPotion() + 1);
			inventory.setMaxHealthPotion(inventory.getMaxHealthPotion() + 1);			
			
		}
		System.out.println("\nPLAYER IS NOW LEVEL " + playerLevel);
		System.out.println("\nPLAYER IS NOW LEVEL " + playerLevel);
		System.out.println("\nPLAYER IS NOW LEVEL " + playerLevel);
		System.out.println("\nPLAYER IS NOW LEVEL " + playerLevel);
		calculateExpNeeded();
		calculateRatings();
		statusChanged();

		System.out.println("dexterity: " + dexterity + " / dex bonus: " + dexterityBonus + " / strength: " + strength
				+ " / str bonus: " + strengthBonus);
		System.out.println("vitality: " + vitality + " /vit bonus: " + vitalityBonus + " / energy: " + energy
				+ " /eng bonus: " + energyBonus);

	}

	public void calculateRatings() {
		bonusCount();
		maxHealth = (int) (baseHealth + ((vitalityBonus + vitality) * vitalityMultiplier));
		maxMana = (int) (baseMana + ((energyBonus + energy) * energyMultiplier));
		combinedDexterity = dexterity + dexterityBonus;
		combinedStrength = strength + strengthBonus;
		combinedVitality = vitality + vitalityBonus;
		combinedEnergy = energy + energyBonus;
		attackRating = (combinedDexterity * 5) - 35 + hitFactor;
		defenseRating = combinedDexterity / 4;
	}

	public String combatDecision() {
		if (playerClass == "Barbarian") {
			currentAbility = combat.barbarianAbility();
			abilityCost = combat.barbarianAbilityCost(maxMana);
			int roll = (int) (Math.random() * 6);
			if (mana >= abilityCost && roll == 0)
				return "magic";
			else
				return "melee";
		} else if (playerClass == "Rogue") {
			currentAbility = combat.rogueAbility();
			abilityCost = combat.rogueAbilityCost(maxMana);
			int roll = (int) (Math.random() * 4);
			if (mana >= abilityCost && roll == 0)
				return "magic";
			else
				return "ranged";
		} else if (playerClass == "Sorcerer") {
			currentAbility = combat.sorcererAbility();
			abilityCost = combat.sorcererAbilityCost(maxMana);
			int roll = (int) (Math.random() * 2);
			if (mana >= abilityCost && roll == 0)
				return "magic";
			else
				return "melee";
		} else
			return "melee";

	}

	public int getAbilityCost() {
		return abilityCost;
	}

	public void setAbilityCost(int abilityCost) {
		this.abilityCost = abilityCost;
	}

	public String getCurrentAbility() {
		return currentAbility;
	}

	public void setCurrentAbility(String currentAbility) {
		this.currentAbility = currentAbility;
	}

	public String getCurrentAbilityDescription() {
		return currentAbilityDescription;
	}

	public void setCurrentAbilityDescription(String currentAbilityDescription) {
		this.currentAbilityDescription = currentAbilityDescription;
	}

	public int meleeRoll(double defenseRatingDefender, int defenderLevel) {
		calculateRatings();
		chanceToHit = ((attackRating / (attackRating + defenseRatingDefender)) * 2
				* ((double) playerLevel / ((double) defenderLevel + (double) playerLevel)));
		if (chanceToHit > .95)
			chanceToHit = .95;
		else if (chanceToHit < .05)
			chanceToHit = .05;
		double baseDamage = (combinedStrength + combinedDexterity) / 10;
		double damageDone = baseDamage * chanceToHit + (playerLevel * Math.random() * 3);
		System.out.println(chanceToHit);
		int critRoll = (int) (Math.random() * 100);
		if (critRoll <= critChance) {
			damageDone = damageDone * 1.5;
		}

		return (int) damageDone;
	}

	public int rangedRoll(double defenseRatingDefender, int defenderLevel) {
		calculateRatings();
		chanceToHit = ((attackRating / (attackRating + defenseRatingDefender)) * 2
				* ((double) playerLevel / ((double) defenderLevel + (double) playerLevel)));
		if (chanceToHit > .95)
			chanceToHit = .95;
		else if (chanceToHit < .05)
			chanceToHit = .05;
		System.out.println(chanceToHit);
		double baseDamage = (combinedStrength + combinedDexterity) / 10;
		double damageDone = baseDamage * chanceToHit + (playerLevel * Math.random() * 3);
		int critRoll = (int) (Math.random() * 100);
		if (critRoll <= critChance) {
			damageDone = damageDone * 1.5;
		}

		return (int) damageDone;
	}

	public int magicRoll(double defenseRatingDefender, int defenderLevel) {
		calculateRatings();
		chanceToHit = ((attackRating / (attackRating + defenseRatingDefender)) * 2
				* ((double) playerLevel / ((double) defenderLevel + (double) playerLevel)));
		if (chanceToHit > .95)
			chanceToHit = .95;
		else if (chanceToHit < .05)			
			chanceToHit = .05;
		System.out.println(chanceToHit);
		double baseDamage = (combinedStrength + combinedDexterity) / 8;
		double damageDone = baseDamage * chanceToHit + (playerLevel * Math.random() * 3);
		int critRoll = (int) (Math.random() * 100);
		if (critRoll <= critChance) {
			damageDone = damageDone * 1.5;
		}

		return (int) damageDone;
	}

	public void bonusCount() {
		strengthBonus = inventory.getTotalStrBonus();
		dexterityBonus = inventory.getTotalDexBonus();
		vitalityBonus = inventory.getTotalVitBonus();
		energyBonus = inventory.getTotalEngBonus();

	}

	public void statusChanged() {
		calculateRatings();
		setChanged();
		notifyObservers(this);
	}

	// ----------------------------------------------------//

	public int getPlayerLevel() {
		return playerLevel;
	}

	public void setPlayerLevel(int playerLevel) {
		this.playerLevel = playerLevel;
	}

	public int getDistanceTraveled() {
		return distanceTraveled;
	}

	public void setDistanceTraveled(int distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}

	public int getDistanceFromHome() {
		return distanceFromHome;
	}

	public void setDistanceFromHome(int distanceFromHome) {
		this.distanceFromHome = distanceFromHome;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
		if (this.health > maxHealth) {
			this.health = maxHealth;
		}
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
		if (this.mana > maxMana) {
			this.mana = maxMana;
		}
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getExperienceEarned() {
		return experienceEarned;
	}

	public int getExperienceNeeded() {
		return experienceNeeded;
	}

	public void setExperienceNeeded(int experienceNeeded) {
		this.experienceNeeded = experienceNeeded;
	}

	public void setExperienceEarned(int experienceEarned) {
		this.experienceEarned = experienceEarned;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getVitality() {
		return vitality;
	}

	public void setVitality(int vitality) {
		this.vitality = vitality;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getCritChance() {
		return critChance;
	}

	public void setCritChance(int critChance) {
		this.critChance = critChance;
	}

	public int getHitFactor() {
		return hitFactor;
	}

	public void setHitFactor(int hitFactor) {
		this.hitFactor = hitFactor;
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

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}

	public String getPlayerClass() {
		return playerClass;
	}

	public void setPlayerClass(String playerClass) {
		this.playerClass = playerClass;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public static Inventory getInventory() {
		return inventory;
	}

	public static void setInventory(Inventory inventory) {
		Player.inventory = inventory;
	}

	public int getStrengthBonus() {
		return strengthBonus;
	}

	public void setStrengthBonus(int strengthBonus) {
		this.strengthBonus = strengthBonus;
	}

	public int getDexterityBonus() {
		return dexterityBonus;
	}

	public void setDexterityBonus(int dexterityBonus) {
		this.dexterityBonus = dexterityBonus;
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

	public int getCombinedStrength() {
		return combinedStrength;
	}

	public void setCombinedStrength(int combinedStrength) {
		this.combinedStrength = combinedStrength;
	}

	public int getCombinedDexterity() {
		return combinedDexterity;
	}

	public void setCombinedDexterity(int combinedDexterity) {
		this.combinedDexterity = combinedDexterity;
	}

	public int getCombinedVitality() {
		return combinedVitality;
	}

	public void setCombinedVitality(int combinedVitality) {
		this.combinedVitality = combinedVitality;
	}

	public int getCombinedEnergy() {
		return combinedEnergy;
	}

	public void setCombinedEnergy(int combinedEnergy) {
		this.combinedEnergy = combinedEnergy;
	}

	public float getEnergyMultiplier() {
		return energyMultiplier;
	}

	public void setEnergyMultiplier(float energyMultiplier) {
		this.energyMultiplier = energyMultiplier;
	}

	public float getVitalityMultiplier() {
		return vitalityMultiplier;
	}

	public void setVitalityMultiplier(float vitalityMultiplier) {
		this.vitalityMultiplier = vitalityMultiplier;
	}

	public int getBaseHealth() {
		return baseHealth;
	}

	public void setBaseHealth(int baseHealth) {
		this.baseHealth = baseHealth;
	}

	public int getBaseMana() {
		return baseMana;
	}

	public void setBaseMana(int baseMana) {
		this.baseMana = baseMana;
	}

}
