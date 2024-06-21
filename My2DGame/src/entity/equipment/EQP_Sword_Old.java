package entity.equipment;

import entity.Entity;
import main.GamePanel;

public class EQP_Sword_Old extends Entity {

	GamePanel gp;
	public static final String eqpName = "Old Sword";
	
	public EQP_Sword_Old(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_sword;	
		name = eqpName;
		description = "[" + name + "]\nA humble starter sword.\n+1 ATK";
				
		attackValue = 3;
		knockbackPower = 1;
		
		swingSpeed1 = 3;
		swingSpeed2 = 15;
		
		attackbox.width = 36;
		attackbox.height = 36;
		
		down1 = setup("/items/ITEM_SWORD");
		image1 = down1;
	}
	
	public void playSE() {
		gp.playSE(3, 0);
	}
}