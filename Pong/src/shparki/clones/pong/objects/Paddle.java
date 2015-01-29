package shparki.clones.pong.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import shparki.clones.pong.Game;
import shparki.clones.pong.graphics.Window;
import shparki.clones.pong.io.Input;
import shparki.clones.pong.util.Point2;
import shparki.clones.pong.util.Rectangle;
import shparki.clones.pong.util.Util;
import shparki.clones.pong.util.Vector2;

public class Paddle {
	
	public static final int WIDTH = 25;
	public static final int HEIGHT = 75;
	public static final int VELOCITY = 600; // in pixels per second
	
	private Point2 position;
	private int up, down;
	private int vel = 0;
	private int points = 0;
	
	
	public int getPoints() { return points; }
	public void setPoints(int points) { this.points = points; }
	public void incPoints(int i) { points += i; }
	
	public Paddle(Point2 position, int up, int down){
		this.position = position;
		this.up = up;
		this.down = down;
	}
	
	public Rectangle getBounds() { return new Rectangle((int)position.getX(), (int)position.getY(), WIDTH, HEIGHT); }
	
	public void update(){
		vel = 0;
		
		if (Input.isKeyDown(up)){ vel = -VELOCITY; }
		if (Input.isKeyDown(down)){ vel = VELOCITY; }
		
		position.linearInterp(new Point2(position.getX(), position.getY() + vel));
		if (position.getY() < Game.UPPER_LIMIT) position.setY(Game.UPPER_LIMIT);
		if (position.getY() > Game.LOWER_LIMIT - HEIGHT) position.setY(Game.LOWER_LIMIT - HEIGHT);
	}
	
	public void render(Graphics2D g2d){
		g2d.setColor(Color.BLACK);
		g2d.fillRect((int)position.getX(), (int)position.getY(), WIDTH, HEIGHT);
	}
	
	public int getVel() { return vel; }
	
}
