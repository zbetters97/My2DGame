package entity.projectile;

import application.GamePanel;
import entity.*;

public class PRJ_Sword extends Projectile {
	
	GamePanel gp;
	public final static String prjName = "Sword Beam";
	
	public PRJ_Sword(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_projectile;
		name = prjName;
		attack = 1; 
		speed = 8; 
		animationSpeed = 8;
		maxLife = 60; life = maxLife;		
		alive = false;
		
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/projectile/sword_up_1");
		up2 = setup("/projectile/sword_up_2");
		down1 = setup("/projectile/sword_down_1");
		down2 = setup("/projectile/sword_down_2");
		left1 = setup("/projectile/sword_left_1");
		left2 = setup("/projectile/sword_left_2");
		right1 = setup("/projectile/sword_right_1");
		right2 = setup("/projectile/sword_right_2");		
	}
	
	public boolean hasResource(Entity user) {		
		boolean hasResource = false;
		if (user.life == user.maxLife)
				hasResource = true;
		
		return hasResource;
	}
	
	public void subtractResource(Entity user) {
		user.arrows -= useCost;
	}
	
	public void playSE() {
		gp.playSE(4, 1);
	}
}