package simulation;

public class PlayerCombat {
	
	private String [] barbarianAbilityNames= {"Bash", "Double Swing", "Stun", "Leap Attack", "Whirlwind", "Frenzy", "Berzerk"};

	private String [] rogueAbilityNames= {"Magic Arrow", "Fire Arrow", "Cold Arrow", "Multiple Arrow", "Ice Arrow", "Exploding Arrow", "Guided Arrow", "Immolation Arrow", "Freezing Arrow"};
	private String [] sorcererAbilityNames= {"Ice Bolt", "Frost Nova", "Ice Blast", "Glacial Spike", "Blizzard", "Inferno", "Fireball", "Blaze", "Meteor", "Hydra", "Charged Bolt","Lightning", "Chain Lightning", "Thunderstorm"};
	
	public String barbarianAbility(){

		int choice = (int) (Math.random() * barbarianAbilityNames.length);
		return barbarianAbilityNames[choice];
	}

	
	public int barbarianAbilityCost(int playerMana){
		int maxCost = (int) (playerMana * .06);
		int minCost = (int) (playerMana * .02); 
		if (minCost == 0)
			minCost = 1;
		int cost = (int) (minCost + (Math.random() * maxCost));
		return cost;
	}
	
	public String rogueAbility(){
		int choice = (int) (Math.random() * rogueAbilityNames.length);
		return rogueAbilityNames[choice];
	}

	
	public int rogueAbilityCost(int playerMana){
		int maxCost = (int) (playerMana * .06);
		int minCost = (int) (playerMana * .02); 
		if (minCost == 0)
			minCost = 1;
		int cost = (int) (minCost + (Math.random() * maxCost));
		
		return cost;
	}
	
	public String sorcererAbility(){
		int choice = (int) (Math.random() * sorcererAbilityNames.length);
		return sorcererAbilityNames[choice];
	}

	
	public int sorcererAbilityCost(int playerMana){
		int maxCost = (int) (playerMana * .06 );
		int minCost = (int) (playerMana * .02); 
		if (minCost == 0)
			minCost = 1;
		int cost = (int) (minCost + (Math.random() * maxCost));
		
		return cost;
	}
}
