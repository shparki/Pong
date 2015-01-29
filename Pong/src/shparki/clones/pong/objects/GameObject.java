package shparki.clones.pong.objects;

import java.util.ArrayList;

import shparki.clones.pong.util.Point2;

public abstract class GameObject {
	
	private static ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	
	
	public GameObject() { objects.add(this); }
	
	protected Point2 position;
	
	public void setPosition(Point2 position) { this.position = position.getClone(); }
	public Point2 getPosition() { return position; }
	
	protected int width, height;
	
	public int getWidth() { return width; }
	public void setWidth(int width) { this.width = width; }
	public void incWidth(int i) { width += i; }
	public int getHeight() { return height; }
	public void setHeight(int height) { this.height = height; }
	public void incHeight(int i) { height += i; }
	
	
}
