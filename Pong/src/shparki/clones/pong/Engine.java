package shparki.clones.pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import shparki.clones.pong.graphics.Background;
import shparki.clones.pong.graphics.Window;
import shparki.clones.pong.io.Input;
import shparki.clones.pong.util.Time;

public class Engine extends Canvas implements Runnable{
	
	public static final int WIDTH = 1920;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final String TITLE = "Pong - Cloned & Improved by Shparki";
	
	public static final int TARGET_UPS = 29;
	public static final long PERIOD = Time.SECOND / TARGET_UPS;
	private int currentUPS, currentFPS;
	private int UPS, FPS;
	
	private Thread animator;
	private boolean running;
	
	private BufferStrategy bs;
	private Graphics2D g2d;
	private Game game;
	private Background background;
	
	
	public Engine(){
		Window.create(WIDTH, HEIGHT, TITLE, this);
		start();
	}
	
	public synchronized void start(){
		if (!running || animator == null){
			animator = new Thread(this, "Animator");
			animator.start();
		}
	}
	public synchronized void stop(){
		running = false;
	}
	
	public void run(){
		
		long beforeTime = 0;
		long currentTime = Time.getTime();
		long upsCounter = 0, secCounter = 0;
		
		long currentUpdate = 0, currentRender = 0;
		long lastUpdate = 0, lastRender = 0;
		
		init();
		
		running = true;
		while(running){
			beforeTime = currentTime;
			currentTime = Time.getTime();
			Time.setDelta(currentTime - beforeTime);
			
			upsCounter += Time.getDelta();
			if (upsCounter >= PERIOD){
				upsCounter -= PERIOD;
				currentUPS ++;
				update();
				
				lastUpdate = currentUpdate;
				currentUpdate = Time.getTime();
				Time.setUpdateDelta(currentUpdate - lastUpdate); 
			}
			
			if (true){
				currentFPS ++;
				render();
			}
			
			lastRender = currentRender;
			currentRender = Time.getTime();
			Time.setRenderDelta(currentRender - lastRender);
		
			secCounter += Time.getDelta();
			if (secCounter >= Time.SECOND){
				secCounter -= Time.SECOND;
				UPS = currentUPS; FPS = currentFPS;
				currentUPS = 0; currentFPS = 0;
				updateSecond();
				System.out.println("UPS: " + UPS + " | FPS: " + FPS);
			}
		}	
		System.exit(0);
	}
	
	public void init(){
		game = new Game();
		background = new Background();
	}
	public void updateSecond(){
	}
	public void update(){
		if (Input.isKeyDown(KeyEvent.VK_ESCAPE)) stop();
		
		background.update();
		game.update();
		
		Window.update();
	}
	public void render(){
		if (bs == null){
			bs = getBufferStrategy();
			if (bs == null){
				createBufferStrategy(3);
				return;
			}
		}
		
		g2d = (Graphics2D) bs.getDrawGraphics();
		
		background.render(g2d);
		game.render(g2d);
		
		g2d.dispose();
		bs.show();
	}
	
	
}
