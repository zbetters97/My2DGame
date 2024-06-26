package entity.enemy;

import java.awt.Rectangle;
import java.util.Random;

import application.GamePanel;
import entity.Entity;
import entity.collectable.COL_Heart;
import entity.collectable.COL_Rupee_Blue;

public class EMY_Buzzblob extends Entity {

	public static final String emyName = "Buzz Blob";
	GamePanel gp;
	private int cycle = 0;
	private int buzzCounter = 0;
	
	public EMY_Buzzblob(GamePanel gp) {
		super(gp);			
		this.gp = gp;
		
		type = type_enemy;
		name = emyName;
		
		speed = 1; defaultSpeed = speed;
		animationSpeed = 10;
		attack = 2; 
		knockbackPower = 1;
		maxLife = 8; life = maxLife;
		
		hitbox = new Rectangle(2, 18, 44, 30);
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
		
		getImage();
		getBuzzImage();
	}
	
	public void getImage() {
		up1 = setup("/enemy/buzzblob_down_1");
		up2 = setup("/enemy/buzzblob_down_2");
		up3 = setup("/enemy/buzzblob_down_3");
		down1 = up1;
		down2 = up2;
		down3 = up3;
		left1 = up1;
		left2 = up2;
		left3 = up3;
		right1 = up1;
		right2 = up2;
		right3 = up3;
		
	}
	public void getBuzzImage() {		
		buzzUp1 = setup("/enemy/buzzblob_attack_down_1"); 
		buzzUp2 = setup("/enemy/buzzblob_attack_down_2");	
	}
	
	public void cycleSprites() {
		spriteCounter++;
		if (spriteCounter > animationSpeed && animationSpeed != 0) {
			
			// 1 -> 2
			if (buzzing) {
				if (spriteNum == 1) spriteNum = 2;
				else if (spriteNum == 2) spriteNum = 1;
			}
			// 1 -> 2 -> 3 -> 2 -> 1
			else {
				if (spriteNum == 1) spriteNum = 2;
				else if (spriteNum == 2 && cycle == 0) {
					spriteNum = 3;
					cycle++;
				}
				else if (spriteNum == 2 && cycle == 1) {
					spriteNum = 1;
					cycle = 0;
				}
				else if (spriteNum == 3) {
					spriteNum = 2;				
				}
			}
			
			spriteCounter = 0;
		}		
	}
	
	public void setAction() {
		
		// BUZZ FOR 2 SECONDS
		if (buzzing) {
			buzzCounter++;
			if (buzzCounter > 120) {
				buzzing = false;				
				buzzCounter = 0;
				attack = 2;
			}
		}
		
		if (onPath) {			
			isOffPath(gp.player, 6);									
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));	
			if (!buzzing) {
				if (isAttacking(180, gp.tileSize * 3, gp.tileSize * 3)) {
					playShockSE();
					buzzing = true;					
					attack *= 2;
				}
			}
		}
		else {			
			isOnPath(gp.player, 3);
			getDirection(60);
			buzzing = false;
			attack = 2;			
		}
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
		onPath = true;
	}
	
	public void playHurtSE() {
		gp.playSE(3, 0);
	}
	public void playDeathSE() {
		gp.playSE(3, 2);
	}
	
	// DROPPED ITEM
	public void checkDrop() {
		super.checkDrop();
		
		int i = new Random().nextInt(100) + 1;
		
		if (i < 50) dropItem(new COL_Heart(gp));
		else dropItem(new COL_Rupee_Blue(gp));
	}
}