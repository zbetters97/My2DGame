package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Rupee_Blue extends Entity {

	GamePanel gp;
	
	public OBJ_Rupee_Blue(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_collectable;
		name = "Blue Rupee";
		value = 5;
		down1 = setup("/objects/COL_RUPEE_BLUE", gp.tileSize, gp.tileSize);
	}
	
	public void use(Entity entity) {		
		gp.playSE(1, 5);
		gp.ui.addMessage("Rupees +" + value + "!");
		entity.rupee += value;
	}
}