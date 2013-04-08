package gameEngine;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import simpleShape.Rectangle;

import interaction.MouseMethods;

import building.Base;
import building.BuildingAbstract;
import gameEngine.GameData;

public class MainGameLoop extends Thread{
    private boolean isRunning = true;
    private Canvas canvas;
    private BufferStrategy strategy;
    private BufferedImage background;
    private Graphics2D backgroundGame;
    
    private static JFrame frame;
    private int width = 1024;
    private int height = 768;
    private GraphicsConfiguration config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    private int frameCount; 
    private boolean frameLag = false;
    // Buffer Image
    public final BufferedImage create(final int width, final int height, final boolean alpha) {
    	return config.createCompatibleImage(width, height, alpha ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
    }

    // Setup
    public MainGameLoop() {
    	// JFrame
    	frame = new JFrame();
    	frame.addWindowListener(new FrameClose());
    	frame.setResizable(false);
    	frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    	frame.setSize(width, height);
    	frame.setVisible(true);
    	
    	// Canvas
    	/*
    	 * On attache un canvas à la fenêtre frame
    	 */
    	canvas = new Canvas(config);
    	canvas.setSize(width, height);
    	canvas.addMouseListener(new MouseMethods());
    	canvas.addMouseMotionListener(new MouseMethods());
    	frame.add(canvas, 0);

    	// Background & Buffer
    	background = create(width, height, false);
    	canvas.createBufferStrategy(2);
    	
    	frameCount = 0;
    	do {
    		strategy = canvas.getBufferStrategy();
    	} while (strategy == null);
    	start(); // lance le thread run()
    }

    // Gère la fermeture de la fenêtre avec un thread, non paramétré par défaut dans swing
    private class FrameClose extends WindowAdapter {
    	@Override
    	public void windowClosing(final WindowEvent e) {
    		isRunning = false;
    	}
    }

    // Screen and buffer stuff
    private Graphics2D getBuffer() {
    	if (backgroundGame == null) {
    		try {
    			backgroundGame = (Graphics2D) strategy.getDrawGraphics();
    		} catch (IllegalStateException e) {
    			return null;
    		}
    	}
    	return backgroundGame;
    }

    // Pour chaque rafraîchissement d'écran
    private boolean updateScreen() {
    	backgroundGame.dispose();
    	backgroundGame = null;  
    	
    	try {
    		strategy.show();
    		Toolkit.getDefaultToolkit().sync(); // Resynchronise les fenêtres
    		return (!strategy.contentsLost());

    	} catch (NullPointerException e) {
    		return true;

    	} catch (IllegalStateException e) {
    		return true;
    	}
    }

    /*
     * Boucle Thread
     * @see java.lang.Thread#run()
     */
    public void run() {
    	long fpsWait = (long) (1.0 / 30 * 1000);
    	main: while (isRunning) {
    		long renderStart = System.nanoTime();
    		updateGame(); // Mise à jour des données logiques
    		updateMove();
    		if(frameCount == 0){
    			if(frameLag == true){
    				updateLifeNano();
    				frameLag = false;
    			}else{
	    			updateLifeBase();
	    			frameLag = true;
    			}
    		}
    		// Update Graphics
    		do {
    			Graphics2D bg = getBuffer();
    			if (!isRunning) {
    				break main;
    			}
    			renderGame(bg); // On dessine les éléments dans le canvas
    		} while (!updateScreen());

    		// Limitation FPS - Environ 33 FPS pour un macbookpro
    		long renderTime = (System.nanoTime() - renderStart) / 1000000;
    		try {
    			Thread.sleep(Math.max(0, fpsWait - renderTime));
    		} catch (InterruptedException e) {
    			Thread.interrupted();
    			break;
    		}
    		renderTime = (System.nanoTime() - renderStart) / 1000000;
    		frameCount++;
        	if((frameCount == renderTime) || (frameCount == renderTime-1)){frameCount = 0;}
    	}
    	frame.dispose();
    }

    public void updateGame() {}
    public void updateMove(){
    	for(BuildingAbstract b : GameData.baseArray){
    		b.iterateNano();
    	}
    }
    public void updateLifeNano(){
    	for(BuildingAbstract b : GameData.baseArray){
    		b.minusLifeNano(1);
    	}
    }
    public void updateLifeBase(){
    	for(BuildingAbstract b : GameData.baseArray){
    		if(b.getType() != 0){
    			b.addLifeBase(1);
    		}
    	}
    }
    /*
     * Appel pour dessiner
     */
    public void renderGame(Graphics2D bg) {
		backgroundGame.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Antialiasing
		bg.drawImage(background, 0, 0, null); // Ouvre le buffer pour l'image
    	drawBackground();
    	drawBuilding();
    	drawSelectLine();

		bg.dispose(); // utilise le buffer
    }
    
    /*
     * Construit le Model pour le jeu
     */
    public static void buildBeginningParty(Random random){
    	for(int i = 0; i<5; i++){
    		GameData.lineSelect.add(0);
    	}
    	//int inc = 0; // Pour l'ID de la base
    	/*for(int i = 0; i<3; i++){	
    		for (int j = 0; j<1; j++){
	    		Base baseJ = new Base(random, 90, inc, i);
	    		GameData.baseArray.add(baseJ);
	    		++inc;
    		}
    	}*/
    	GameData.baseArray.add(new Base(50, 50, 90, 0, 1));
    	GameData.baseArray.add(new Base(900, 600, 90, 0, 2));
    	for(int i = 0; i<3; i++){	
	    		GameData.baseArray.add(new Base(random, 30, 0, 0));
    	}
    }
    
    /*
     * Dessin Painting
     */
    public void drawBackground(){
    	Rectangle rectangle = new Rectangle(width, height, 0, 0, Color.BLACK);
    	rectangle.drawRectangle(backgroundGame);
    }
    public void drawSelectLine(){ 
    	if(GameData.lineSelect.get(4) == 1){
	    	backgroundGame.setColor(Color.WHITE);
	    	backgroundGame.drawLine(GameData.lineSelect.get(2), GameData.lineSelect.get(3), GameData.lineSelect.get(0), GameData.lineSelect.get(1));
    	}
    }
    public void drawBuilding(){   	    	
    	for(BuildingAbstract b : GameData.baseArray){
    		b.paintBase(backgroundGame);
    		b.paintNano(backgroundGame);
    	}
    }

    /*
     * Initialisation
     */
    public static void main(final String args[]) {    	
    	// Build game
		Random random = new Random();
    	buildBeginningParty(random);
    	new MainGameLoop();
    }    
}
