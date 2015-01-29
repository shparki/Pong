package shparki.clones.pong.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import shparki.clones.pong.util.Point2;

public class Line {
	
	private Point2 start, end;
	private Color color;
	
	public Line(Point2 start, Point2 end, Color color){
		this.start = start;
		this.end = end;
		this.color = color;
	}
	
	public Point2 getStart() { return start; }
	public void setStart(Point2 start) { this.start = start.getClone(); }
	public Point2 getEnd() { return end; }
	public void setEnd(Point2 end) { this.end = end.getClone(); }
	
	
	public void render(Graphics2D g2d){
		g2d.setColor(color);
		g2d.drawLine((int)start.getX(), (int)start.getY(), (int)end.getX(), (int)end.getY());
	}
}
