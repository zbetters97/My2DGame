package entity.object.object_interactive;

import java.awt.Rectangle;
import java.util.ArrayList;

import application.GamePanel;
import entity.Entity;
import entity.object.OBJ_Door_Closed;
import tile.tile_interactive.IT_Plate_Metal;
import tile.tile_interactive.InteractiveTile;

public class OT_Block_Pushable extends Entity {
	
	public static final String obj_iName = "Pushable Block";
	public int pushCounter = 0;
	public int pushMax = 48;
	GamePanel gp;
	
	public OT_Block_Pushable(GamePanel gp) {		
		super(gp);
		this.gp = gp;		
		
		type = type_obstacle;
		name = obj_iName;
		direction = "down";
		speed = 15; defaultSpeed = speed;
		
		collision = true;		
		
		hitbox = new Rectangle(0, 0, 48, 48); 		
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
		
		getImage();
	}
	
	public void getImage() {		
		up1 = setup("/objects_interactive/block_pushable"); 
		up2 = up1;
		down1 = up1;
		down2 = up1;
		left1 = up1;
		left2 = up1;
		right1 = up1;
		right2 = up1;
	}
				
	public void update() { 
						
		if (pushed) {
			if (pushCounter < pushMax) {
				detectMovement(1, direction);
				pushCounter++;
			}
			else {
				pushed = false;
				pushCounter = 0;
			}
		}
	}
	
	public void move(String dir) {		

		if (pushCounter == 0 && !pushed) {			
			playSE();
			pushed = true;	
			direction = dir;			
		}
	}
	
	public boolean detectMovement(int spd, String dir) {
		
		direction = dir;
		speed = spd;
		
		collisionOn = false;
		gp.cChecker.checkTile(this);		
		gp.cChecker.checkEntity(this, gp.npc);
		gp.cChecker.checkEntity(this, gp.enemy);
		gp.cChecker.checkEntity(this, gp.enemy_r);
		gp.cChecker.checkPlayer(this);
		
		// PUSH BOULDER 
		if (!collisionOn) {
			switch (direction) {
				case "up": worldY -= speed; break;				
				case "down": worldY += speed; break;				
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
			}
		}

		detectPlate();		
		speed = defaultSpeed;		
		return collisionOn;
	}
	
	public void detectPlate() {
		
		// CREATE PLATE LIST
		ArrayList<InteractiveTile> plateList = new ArrayList<>();		
		for (int i = 0; i < gp.iTile[1].length; i++) {
			if (gp.iTile[gp.currentMap][i] != null && 
					gp.iTile[gp.currentMap][i].name != null &&
					gp.iTile[gp.currentMap][i].name.equals(IT_Plate_Metal.itName)) {
				plateList.add(gp.iTile[gp.currentMap][i]);
			}
		}	
		
		// CREATE BOULDER LIST
		ArrayList<Entity> boulderList = new ArrayList<>();		
		for (int i = 0; i < gp.obj_i[1].length; i++) {
			if (gp.obj_i[gp.currentMap][i] != null && 
					gp.obj_i[gp.currentMap][i].name != null &&
					gp.obj_i[gp.currentMap][i].name.equals(OT_Block_Pushable.obj_iName)) {
				boulderList.add(gp.obj_i[gp.currentMap][i]);
			}
		}
		
		// SCAN PLATES	
		for (int i = 0; i < plateList.size(); i++) {
			
			int xDistance = Math.abs(worldX - plateList.get(i).worldX);
			int yDistance = Math.abs(worldY - plateList.get(i).worldY);
			int distance = Math.max(xDistance, yDistance);
						
			// ROCK IS ON PLATE (within 8 px)
			if (distance < 15) {									
				if (linkedEntity == null) {
					plateList.get(i).playSE();
					linkedEntity = plateList.get(i);
				}
			}
			// ROCK MOVED OFF PLATE
			else if (linkedEntity == plateList.get(i)) {
				linkedEntity = null;								
			}
		}
		
		// TRACK HOW MANY PLATES HAVE BOULDERS
		int count = 0;
		for (int i = 0; i < boulderList.size(); i++) {			
			if (boulderList.get(i).linkedEntity != null) {
				count++;
			}
		}
		
		// IF ALL PLATES ARE PRESSED
		if (count == boulderList.size()) {
			for (int i = 0; i < gp.obj[1].length; i++) {
				
				// REMOVE IRON DOOR
				if (gp.obj[gp.currentMap][i] != null && 
						gp.obj[gp.currentMap][i].name != null &&
						gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Closed.objName)) {
					
					gp.obj[gp.currentMap][i].playOpenSE();
					gp.obj[gp.currentMap][i] = null;
				}				
			}
		}
	}
		
	public void playSE() {
		gp.playSE(3, 12);
	}
}