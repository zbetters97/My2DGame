package entity.equipment;

import application.GamePanel;
import entity.Entity;

public class EQP_Sword_Old extends Entity {

	public static final String eqpName = "Traveler's Sword";
	
	public EQP_Sword_Old(GamePanel gp) {
		super(gp);
		
		type = type_equipment;	
		name = eqpName;
		description = "[" + name + "]\nA humble starter sword.";
				
		attackValue = 2;
		knockbackPower = 1;
		
		swingSpeed1 = 3;
		swingSpeed2 = 12;
		
		attackbox.width = 36;
		attackbox.height = 36;
		
		down1 = setup("/items/EQP_SWORD_OLD");
		image1 = down1;
	}
	
	public void playSE() {
		gp.playSE(4, 0);
	}
}