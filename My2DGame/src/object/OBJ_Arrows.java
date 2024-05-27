package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Arrows extends Entity {

	GamePanel gp;
	
	public OBJ_Arrows(GamePanel gp) {
		super(gp);
		this.gp = gp;

		type = type_collectable;
		name = "Arrow";
		value = 1;
		down1 = setup("/objects/arrows_full", gp.tileSize - 15, gp.tileSize - 15);
		image = setup("/objects/arrows_full", gp.tileSize - 15, gp.tileSize - 15);
		image2 = setup("/objects/arrows_empty", gp.tileSize - 15, gp.tileSize - 15);
	}
	
	public void use(Entity entity) {
		gp.playSE(1, 6);
		gp.ui.addMessage("Arrows +" + value + "!");
		entity.arrows += value;
	}
}