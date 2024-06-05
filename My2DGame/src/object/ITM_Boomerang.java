package object;

import entity.Entity;
import main.GamePanel;

public class ITM_Boomerang extends Entity {

	GamePanel gp;
	
	public ITM_Boomerang(GamePanel gp) {
		super(gp);
		this.gp = gp;

		type = type_item;
		name = "Boomerang";
		description = "[" + name + "]\nEquip to pull in far away\nitems!";
		price = 40;
		down1 = setup("/objects/ITEM_BOOMERANG");
		
		projectile = new PRJ_Boomerang(gp);
	}
	
	public void use(Entity user) {
		if (!projectile.alive && user.shotAvailableCounter == 30) { 			
						
			projectile.set(user.worldX, user.worldY, user.direction, true, user);			
			addProjectile(projectile);
						
			user.shotAvailableCounter = 0;	
		}			
	}
}