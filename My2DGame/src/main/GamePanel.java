package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3; // scale rate to accommodate for large screen
	public final int tileSize = originalTileSize * scale; // scaled tile (16*3 = 48px)	
	public final int maxScreenCol = 16; // columns (width)
	public final int maxScreenRow = 12; // rows (height)
	public final int screenWidth = tileSize * maxScreenCol; // screen width (in tiles) 768px
	public final int screenHeight = tileSize * maxScreenRow; // screen height (in tiles) 576px
	
	// WORLD SIZE
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	// FPS
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Sound music = new Sound();
	Sound se = new Sound();	
	public UI ui = new UI(this);
	
	Thread gameThread;
	
	public TileManager tileM = new TileManager(this);
	public CollisionChecker cChecker = new CollisionChecker(this);		
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10]; // total amount of different items in game
	public AssetSetter aSetter = new AssetSetter(this);
		
	/** CONSTRUCTOR **/
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // screen size
		this.setBackground(Color.black); // black background color
		this.setDoubleBuffered(true); // improves rendering performance
		
		this.addKeyListener(keyH);
		this.setFocusable(true); // GamePanel in focus to receive input
	}
	
	public void setupGame() {
		
		aSetter.setObject();
		playMusic(0);
	}

	public void startGameThread() {
		
		gameThread = new Thread(this); // new Thread with GamePanel class
		gameThread.start(); // calls run() method
	}
	
	@Override
	public void run() {
		
		long currentTime;
		long lastTime = System.nanoTime();
		double drawInterval = 1000000000 / FPS; // 1/60th of a second
		double delta = 0;
		long timer = 0;
		int drawCount = 0;
		
		// updates and repaints gameThread x times per second (x fps)
		while(gameThread != null) {
			
			currentTime = System.nanoTime();			
			delta += (currentTime - lastTime) / drawInterval; // time passed / 1/60th second
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta >= 1) {
									
				// UPDATE INFORMATION (character positions...)
				update();
				
				// DRAW SCREEN WITH INFORMATION
				repaint(); // calls paintCompoment() method
				
				delta--;				
				drawCount++;
			}
			// DISPLAY FPS (PER SEC)
			if (timer >= 1000000000) {
//				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); // required for paintCompoment() method
		
		Graphics2D g2 = (Graphics2D)g; // Graphics2D has more functionality than Graphics
		
		// DRAW TILES
		tileM.draw(g2);	
		
		// DRAW OBJECTS
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) 
				obj[i].draw(g2, this);
		}
		
		// DRAW PLAYER
		player.draw(g2);
		
		// DRAW UI
		ui.draw(g2);
		
		// MEMORY DUMP
		g2.dispose(); 
	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		music.stop();
	}
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
}









