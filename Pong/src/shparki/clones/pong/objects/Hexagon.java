package shparki.clones.pong.objects;

import java.awt.Graphics2D;

import shparki.clones.pong.util.Point2;

public class Hexagon {
	
	private Point2 center;
	private double height, sideLengths;
	private Point2[] vertices;
	private Polygon hexagon;
	
	public Hexagon(Point2 center, double height){
		this.center = center;
		this.height = height;
		sideLengths = (2 * height) / (Math.sqrt(3));
		
		vertices = new Point2[]{
			new Point2(center.getX() + sideLengths / 2, center.getY() - height),
			new Point2(center.getX() + sideLengths / 2 + (height / Math.sqrt(3)), center.getY()),
			new Point2(center.getX() + sideLengths / 2, center.getY() + height),
			new Point2(center.getX() - sideLengths / 2, center.getY() + height),
			new Point2(center.getX() - sideLengths / 2 - (height / Math.sqrt(3)), center.getY()),
			new Point2(center.getX() - sideLengths / 2, center.getY() - height)
		};
		
		hexagon = new Polygon(vertices);
	}
	
	public void incHeight(double i) { height += i; }
	
	
	public void update(){
		sideLengths = (2 * height) / (Math.sqrt(3));
		
		Point2[] verticesL = new Point2[]{
			new Point2(center.getX() + sideLengths / 2, center.getY() - height),
			new Point2(center.getX() + sideLengths / 2 + (height / Math.sqrt(3)), center.getY()),
			new Point2(center.getX() + sideLengths / 2, center.getY() + height),
			new Point2(center.getX() - sideLengths / 2, center.getY() + height),
			new Point2(center.getX() - sideLengths / 2 - (height / Math.sqrt(3)), center.getY()),
			new Point2(center.getX() - sideLengths / 2, center.getY() - height)
		};
		
		for (int i = 0; i < vertices.length; i++){
			vertices[i].linearInterp(verticesL[i]);
		}
		
		
		hexagon = new Polygon(vertices);
	}
	
	public void render(Graphics2D g2d){
		hexagon.render(g2d);
	}
	
	public Point2 getCenter() { return center; }
	
	
}
