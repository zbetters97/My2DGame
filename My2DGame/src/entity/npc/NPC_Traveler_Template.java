package entity.npc;

import java.awt.Rectangle;

import application.GamePanel;
import entity.Entity;

public class NPC_Traveler_Template extends Entity {

	protected GamePanel gp;	
	protected int goalCol, goalRow;
	
	public NPC_Traveler_Template(GamePanel gp) {		
		super(gp);
		this.gp = gp;		
		
		type = type_npc;
		direction = "down";
		speed = 0; defaultSpeed = speed;
		animationSpeed = 0; 
		
		hitbox = new Rectangle(8, 16, 32, 32); 		
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {		
		up1 = setup("/npc/oldman_up_1"); up2 = setup("/npc/oldman_up_2");
		down1 = setup("/npc/oldman_down_1"); down2 = setup("/npc/oldman_down_2");
		left1 = setup("/npc/oldman_left_1"); left2 = setup("/npc/oldman_left_2");
		right1 = setup("/npc/oldman_right_1"); right2 = setup("/npc/oldman_right_2");
	}
	
	public void setDialogue() { }
	
	public void speak() {
		
		if (!onPath) {
			facePlayer();
			startDialogue(this, dialogueSet);	
		}	
		else
			gp.player.attackCanceled = false;
	}
	
	public void setPath(int goalCol, int goalRow) {
		this.goalCol = goalCol;
		this.goalRow = goalRow;
	}
	
	public void setAction() {

		// PATH NOT OPEN
		if (onPath && !findPath(goalCol, goalRow)) {
			speed = defaultSpeed;
			animationSpeed = 0;	
			onPath = false;
		}
		// PATH OPEN
		if (onPath && findPath(goalCol, goalRow)) {		
			speed = 2;
			animationSpeed = 15;		
			searchPath(goalCol, goalRow);
		}		
		// GOAL REACHED
		if (pathCompleted) {
			speed = defaultSpeed;		
			animationSpeed = 0;
			onPath = false;
		}
	}
}