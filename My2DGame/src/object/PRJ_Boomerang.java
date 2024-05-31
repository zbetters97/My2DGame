package object;

import entity.Projectile;
import main.GamePanel;

public class PRJ_Boomerang extends Projectile {

	GamePanel gp;
	
	public PRJ_Boomerang(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Boomerang";
		speed = 8; // speed of travel
		maxLife = 30; // length of life (half length of screen)
		animationSpeed = 8;
		life = maxLife;	
		attack = 1; // damage dealt	
		alive = false;
		
		//hitBox = new Rectangle(16, 16, 32, 32);
		
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/projectile/boomerang_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/projectile/boomerang_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/projectile/boomerang_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/projectile/boomerang_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/projectile/boomerang_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/projectile/boomerang_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/projectile/boomerang_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/projectile/boomerang_down_2", gp.tileSize, gp.tileSize);
	}
}