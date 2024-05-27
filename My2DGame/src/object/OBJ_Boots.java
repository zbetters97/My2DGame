package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity {
	
	GamePanel gp;
	
	public OBJ_Boots(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_item;
		name = "Running Shoes";
		description = "[" + name + "]\nHold SHIFT to run!";
		down1 = setup("/objects/ITEM_BOOTS");
		down2 = down1;
	}	
}