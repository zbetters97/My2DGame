package entity.projectile;

import java.awt.Rectangle;

import application.GamePanel;
import entity.*;

public class PRJ_Arrow extends Projectile {

	GamePanel gp;
	public static final String prjName = "Arrow";
	
	public PRJ_Arrow(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_projectile;
		name = prjName;
		speed = 6; 		
		attack = 2; 	
		knockbackPower = 0;
		useCost = 1;		
		maxLife = 120; life = maxLife;
		alive = false;
		
		hitbox = new Rectangle(12, 16, 24, 24);
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
		hitboxDefaultWidth = hitbox.width;
		hitboxDefaultHeight = hitbox.height;
		
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/projectile/arrow_up_1", 35, 35);
		up2 = up1;
		down1 = setup("/projectile/arrow_down_1", 35, 35);
		down2 = down1;
		left1 = setup("/projectile/arrow_left_1", 35, 35);
		left2 = left1;
		right1 = setup("/projectile/arrow_right_1", 35, 35);
		right2 = right1;	
	}
	
	public boolean hasResource(Entity user) {
		
		boolean hasResource = false;
		
		if (user.arrows >= useCost || user.arrows == -1) 
			hasResource = true;		
		
		return hasResource;
	}	
	public void subtractResource(Entity user) {
		if (user.arrows != -1)
			user.arrows -= useCost;
	}
	
	// PICKUP ARROW IF NOT MOVING
	public void interact() {
		if (canPickup) {
			gp.player.arrows++;
			alive = false;
			canPickup = false;
		}
	}
	
	public void resetValues() {
		attack = 2;
		speed = 6; 
		alive = false;
		canPickup = false;	
	}
}