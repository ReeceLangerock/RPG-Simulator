package simulation;

public class Location {

	private String locationType;
	private String locationDescription;
	
	private String[] locationTypes = {"Desert", "Forest", "Frozen", "Swamp", "Haunted"};
	private String[] swampLocationDescriptions = { "Swamplands",	"Great Marsh", "Swampy Pit", "Black Marsh",};
	private String[] desertLocationDescriptions = { "Dry Steppes",	"Dry Hills", "Valley of Snakes", "Forgotten Sands", "Rocky Waste"};
	private String[] forestLocationDescriptions = { "Sharval Wilds",	"Bloody Foothills", "Dark Wood", "Festering Woods", "Spider Forest"};
	private String[] frozenLocationDescriptions = { "Glacial Trail",	"Frozen Tundra", "Frigid Highlands", "Cold Plains", "Arreat Summit", "Icy Cellar"};
	private String[] hauntedLocationDescriptions = { "Dreadlands",	"Burial Grounds", "Musty Cellar", "Halls of the Dead", "Cursed Hold", "Chaos Sanctuary"};
	private String[] swampWalkingDescriptions = { "walking","slogging", "trudging", "wading", "splashing", "tromping", "pushing"};
	private String[] desertWalkingDescriptions = { "walking","shuffling", "trudging", "shambling", "wandering", "roaming", "strolling"   };
	private String[] forestWalkingDescriptions = { "walking", "hiking", "tramping", "trekking", "rambling", "wandering", "strolling"};
	private String[] frozenWalkingDescriptions = { "walking","trudging", "plodding", "slogging", "plowing", "stomping", "sliding"};
	private String[] hauntedWalkingDescriptions = { "walking","creeping", "stealing", "sneaking", "slinking", "skulking", "prowling"};



	public String getWalkingDescription(){
		String description;
		
		switch(locationType){
		case "Swamp":
			description = walkingSwamp();
			break;
		case "Desert":
			description = walkingDesert();
			break;
		case "Forest":
			description = walkingForest();
			break;
		case "Frozen":
			description = walkingFrozen();
			break;
		case "Haunted":
			description = walkingHaunted();
			break;
		default:
			description = "walking";
		}
		
		
		return description;
	}
	
	public String walkingSwamp(){
		String walkingDescription;
		int choice = (int) (Math.random() * swampWalkingDescriptions.length);
		walkingDescription = swampWalkingDescriptions[choice];
		
		return walkingDescription;
		
	}
	public String walkingForest(){
		String walkingDescription;
		int choice = (int) (Math.random() * forestWalkingDescriptions.length);
		walkingDescription = forestWalkingDescriptions[choice];
		
		return walkingDescription;
		
	}
	public String walkingFrozen(){
		String walkingDescription;
		int choice = (int) (Math.random() * frozenWalkingDescriptions.length);
		walkingDescription = frozenWalkingDescriptions[choice];
		
		return walkingDescription;
		
	}
	public String walkingHaunted(){
		String walkingDescription;
		int choice = (int) (Math.random() * hauntedWalkingDescriptions.length);
		walkingDescription = hauntedWalkingDescriptions[choice];
		
		return walkingDescription;
		
	}
	public String walkingDesert(){
		String walkingDescription;
		int choice = (int) (Math.random() * desertWalkingDescriptions.length);
		walkingDescription = desertWalkingDescriptions[choice];
		
		return walkingDescription;
		
	}
		
		public Location() {
			int locationTypeChoice = (int) (Math.random() * locationTypes.length);
			locationType = locationTypes[locationTypeChoice];
			int randomDesc;
			
			switch (locationType){
			case "Swamp":
				randomDesc = (int) (Math.random() * swampLocationDescriptions.length);
				locationDescription = swampLocationDescriptions[randomDesc];
				break;
			case "Forest":
				randomDesc = (int) (Math.random() * forestLocationDescriptions.length);
				locationDescription = forestLocationDescriptions[randomDesc];
				break;
			case "Haunted":
				randomDesc = (int) (Math.random() * hauntedLocationDescriptions.length);
				locationDescription = hauntedLocationDescriptions[randomDesc];
				break;
			case "Frozen":
				randomDesc = (int) (Math.random() * frozenLocationDescriptions.length);
				locationDescription = frozenLocationDescriptions[randomDesc];
				break;
			case "Desert":
				randomDesc = (int) (Math.random() * desertLocationDescriptions.length);
				locationDescription = desertLocationDescriptions[randomDesc];
				break;
			}
			
		}




		public String getLocationType() {
			return locationType;
		}




		public void setLocationType(String locationType) {
			this.locationType = locationType;
		}




		public String getLocationDescription() {
			return locationDescription;
		}




		public void setLocationDescription(String locationDescription) {
			this.locationDescription = locationDescription;
		}
			
		}