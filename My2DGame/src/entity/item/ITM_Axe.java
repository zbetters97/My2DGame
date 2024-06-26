package entity.item;

import application.GamePanel;
import entity.Entity;

public class ITM_Axe extends Entity {
	
	GamePanel gp;
	public static final String itmName = "Wooden Axe";
	
	public ITM_Axe(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_item;		
		name = itmName;
		description = "[" + name + "]\nEquip to chop down trees!";
		
		attack = 1;
		knockbackPower = 0;
		
		down1 = setup("/items/ITEM_AXE");
	}
	
	public void use() {				
		gp.player.attacking = true;
		gp.player.action = Action.CHOPPING;
	}
	public void playSE() {
		gp.playSE(4, 0);
	}
}