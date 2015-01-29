package shparki.clones.pong.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import shparki.clones.pong.Game;
import shparki.clones.pong.util.Point2;

public class Ball extends GameObject{
	
	public static final int VELOCITY = 480;
	public static final int SIZE = 10;
	
	private Point2 initialPosition = null;
	private int velX, velY;
	private ArrayList<Paddle> paddles = new ArrayList<Paddle>();
	public void addPaddle(Paddle paddle) { paddles.add(paddle); }
	private boolean onHit = false;
	public boolean isOnHit() { return onHit; }
	
	
	public Ball(Point2 position){
		this.position = position.getClone();
		width = SIZE; height = SIZE;
		
		initialPosition = position.getClone();
		
		velX = VELOCITY; velY = VELOCITY;
	}
	
	public void reset(){
		position = initialPosition.getClone();
	}
	public void update(){
		onHit = false;
		
		position.linearInterp(new Point2(position.getX() + velX, position.getY() + velY));
		
		for(Paddle p : paddles){
			if (position.getY() + SIZE >= p.getBounds().getY() && position.getY() <= p.getBounds().getY() + p.getBounds().getHeight()){
				if (position.getX() + SIZE >= p.getBounds().getX() && position.getX() <= p.getBounds().getX() + p.getBounds().getWidth()){
					if (velX == VELOCITY){
						position.setX(p.getBounds().getX());
					} else if (velX == -1 * VELOCITY){
						position.setX(p.getBounds().getX() + p.getBounds().getWidth());
					}
					velX *= -1;
					onHit = true;
				}
			}
		}
		
		if (position.getX() < Game.LEFT_LIMIT) { position.setX(Game.LEFT_LIMIT); velX *= -1; onHit = true; }
		if (position.getX() + width > Game.RIGHT_LIMIT) { position.setX(Game.RIGHT_LIMIT - width); velX *= -1; onHit = true; }
		
		if (position.getY() < Game.UPPER_LIMIT) { position.setY(Game.UPPER_LIMIT); velY *= -1; onHit = true; }
		if (position.getY() + height > Game.LOWER_LIMIT) { position.setY(Game.LOWER_LIMIT - height); velY *= -1; onHit = true; }
	}
	public void render(Graphics2D g2d){
		g2d.setColor(Color.BLACK);
		g2d.fillOval((int)position.getX(), (int)position.getY(), width, height);
	}
}
