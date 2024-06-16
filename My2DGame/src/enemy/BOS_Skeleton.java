package enemy;

import java.awt.Rectangle;

import entity.Entity;
import items.ITM_Hookshot;
import main.GamePanel;

public class BOS_Skeleton extends Entity {

	public static final String bosName = "Skeleton King";
	GamePanel gp;
	
	public BOS_Skeleton(GamePanel gp) {
		super(gp);				
		this.gp = gp;
		
		type = type_enemy;
		boss = true;
		name = bosName;
		speed = 1; defaultSpeed = speed; 
		animationSpeed = 10;
		attack = 10; defense = 2;
		knockbackPower = 5;
		exp = 50;
		maxLife = 50; life = maxLife;
		currentBossPhase = bossPhase_1;
		
		swingSpeed1 = 45;
		swingSpeed2 = 80;
						
		int hbScale = gp.tileSize * 5;
		hitbox = new Rectangle(gp.tileSize, gp.tileSize, hbScale - (gp.tileSize * 2), hbScale - gp.tileSize); 
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
		
		attackArea.width = 170;
		attackArea.height = 170;
		
		getImage();
		getAttackImage();
	}
	
	public void getImage() {
		
		int scale = gp.tileSize * 5;
		
		if (currentBossPhase == bossPhase_1) {
			up1 = setup("/enemy/skeletonlord_up_1", scale, scale);
			up2 = setup("/enemy/skeletonlord_up_2", scale, scale);
			down1 = setup("/enemy/skeletonlord_down_1", scale, scale);
			down2 = setup("/enemy/skeletonlord_down_2", scale, scale);
			left1 = setup("/enemy/skeletonlord_left_1", scale, scale);
			left2 = setup("/enemy/skeletonlord_left_2", scale, scale);
			right1 = setup("/enemy/skeletonlord_right_1", scale, scale);
			right2 = setup("/enemy/skeletonlord_right_2", scale, scale);
		}
		else {
			up1 = setup("/enemy/skeletonlord_phase2_up_1", scale, scale);
			up2 = setup("/enemy/skeletonlord_phase2_up_2", scale, scale);
			down1 = setup("/enemy/skeletonlord_phase2_down_1", scale, scale);
			down2 = setup("/enemy/skeletonlord_phase2_down_2", scale, scale);
			left1 = setup("/enemy/skeletonlord_phase2_left_1", scale, scale);
			left2 = setup("/enemy/skeletonlord_phase2_left_2", scale, scale);
			right1 = setup("/enemy/skeletonlord_phase2_right_1", scale, scale);
			right2 = setup("/enemy/skeletonlord_phase2_right_2", scale, scale);
		}
	}	
	public void getAttackImage() {	
		
		int scale = gp.tileSize * 5;
		
		if (currentBossPhase == bossPhase_1) {
			attackUp1 = setup("/enemy/skeletonlord_attack_up_1", scale, scale * 2); 
			attackUp2 = setup("/enemy/skeletonlord_attack_up_2", scale, scale * 2);		
			attackDown1 = setup("/enemy/skeletonlord_attack_down_1", scale, scale * 2); 
			attackDown2 = setup("/enemy/skeletonlord_attack_down_2", scale, scale * 2);		
			attackLeft1 = setup("/enemy/skeletonlord_attack_left_1", scale * 2, scale); 
			attackLeft2 = setup("/enemy/skeletonlord_attack_left_2", scale * 2, scale);		
			attackRight1 = setup("/enemy/skeletonlord_attack_right_1", scale * 2, scale); 
			attackRight2 = setup("/enemy/skeletonlord_attack_right_2", scale * 2, scale);			
		}
		else {
			attackUp1 = setup("/enemy/skeletonlord_phase2_attack_up_1", scale, scale * 2); 
			attackUp2 = setup("/enemy/skeletonlord_phase2_attack_up_2", scale, scale * 2);		
			attackDown1 = setup("/enemy/skeletonlord_phase2_attack_down_1", scale, scale * 2); 
			attackDown2 = setup("/enemy/skeletonlord_phase2_attack_down_2", scale, scale * 2);		
			attackLeft1 = setup("/enemy/skeletonlord_phase2_attack_left_1", scale * 2, scale); 
			attackLeft2 = setup("/enemy/skeletonlord_phase2_attack_left_2", scale * 2, scale);		
			attackRight1 = setup("/enemy/skeletonlord_phase2_attack_right_1", scale * 2, scale); 
			attackRight2 = setup("/enemy/skeletonlord_phase2_attack_right_2", scale * 2, scale);	
		}
	}
	
	public void setAction() {
		
		if (currentBossPhase == 1) {
			
			if (getTileDistance(gp.player) < 10) {			
				approachPlayer(120);
			}
			else {			
				getDirection(120);
			}			
			if (!attacking) {
				isAttacking(60, gp.tileSize * 7, gp.tileSize * 5);
			}
			
			if (life < maxLife / 2) {
				currentBossPhase = 2;
				attack++; defense--;
				defaultSpeed++; speed = defaultSpeed;
				getImage();
				getAttackImage();
			}
		}
		else if (currentBossPhase == 2) {
			
			if (getTileDistance(gp.player) < 10) {			
				approachPlayer(90);
			}
			else {			
				getDirection(90);
			}
			
			if (!attacking) {
				isAttacking(60, gp.tileSize * 7, gp.tileSize * 5);
			}
		}	
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
	}
	
	public void playHurtSE() {
		gp.playSE(4, 0);
	}
	public void playDeathSE() {
		gp.playSE(4, 2);
	}
	
	// DROPPED ITEM
	public void checkDrop() {		
		dropItem(new ITM_Hookshot(gp));
	}
}