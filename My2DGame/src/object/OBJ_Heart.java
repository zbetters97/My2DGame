package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {
	
	GamePanel gp;
	
	public OBJ_Heart(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_collectable;		
		name = "Heart";
		value = 2;
		down1 = setup("/objects/heart_full", gp.tileSize / 2, gp.tileSize / 2);
		image = setup("/objects/heart_full", gp.tileSize / 2, gp.tileSize / 2);
		image2 = setup("/objects/heart_half", gp.tileSize / 2, gp.tileSize / 2);
		image3 = setup("/objects/heart_empty", gp.tileSize / 2, gp.tileSize / 2);
	}
	
	public void use(Entity entity) {
		gp.playSE(1, 6);
		gp.ui.addMessage("Life +" + value + "!");
		entity.life += value;
	}
}