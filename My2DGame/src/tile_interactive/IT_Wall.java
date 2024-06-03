package tile_interactive;

import java.awt.Color;
import main.GamePanel;

public class IT_Wall extends InteractiveTile {

	GamePanel gp;
	
	public IT_Wall(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 = setup("/tiles_interactive/destructiblewall", gp.tileSize, gp.tileSize);
		destructible = true;
		bombable = true;
		life = 1;
	}
	
	public void playSE() {
		gp.playSE(1, 7);
	}
	
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new IT_Trunk(gp, worldX / gp.tileSize, worldY / gp.tileSize);
		return tile;
	}
	
	public Color getParticleColor() {
		Color color = new Color(65,50,30); // BROWN
		return color;
	}
	
	public int getParticleSize() {		
		int size = 6; // 6px
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 1;
		return speed;		
	}
	
	public int getParticleMaxLife() {
		int maxLife = 20; // 20 frames
		return maxLife;
	}
}