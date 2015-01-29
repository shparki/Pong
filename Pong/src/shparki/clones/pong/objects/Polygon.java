package shparki.clones.pong.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import shparki.clones.pong.util.Point2;

public class Polygon {
	
	private Line[] sides;
	
	public Polygon(Point2[] v){
		if (v.length < 3) { return; }
		
		sides = new Line[v.length];
		sides[0] = new Line(v[v.length - 1], v[0], Color.BLACK);
		for (int i = 1; i < v.length; i++){
			sides[i] = new Line(v[i - 1], v[i], Color.BLACK);
		}
	}
	
	public void render(Graphics2D g2d){
		for (Line line : sides){
			line.render(g2d);
		}
	}
}
