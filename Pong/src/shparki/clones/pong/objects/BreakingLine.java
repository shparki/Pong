package shparki.clones.pong.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import shparki.clones.pong.util.Point2;

public class BreakingLine {
	
	public static final int MIN_BREAK_LENGTH = 25;
	public static final int MAX_BREAK_LENGTH = 75;
	public static final int MIN_PRESENT_LENGTH = 50;
	public static final int MAX_PRESENT_LENGTH = 150;
	
	private Point2 start, end;
	private ArrayList<Line> lines = new ArrayList<Line>();
	private ArrayList<Double> velocities = new ArrayList<Double>();
	private double acceleration = 0.0;
	
	public BreakingLine(Point2 start,Point2 end, double acceleration){
		this.start = start;
		this.end = end;
		this.acceleration = acceleration;
		
		Random rand = new Random();
		velocities.add(0.0);
		lines.add(new Line(start.getClone(), start.getClone(), new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256))));
		
	}
	public void update(){
		for (Double d : velocities){
			d += acceleration;
		}
		for(int i = lines.size() - 1; i >= 0; i--){
			//lines.get(i).getStart().getX() += 
		}
	}
	
	public void render(Graphics2D g2d){
		for (Line line : lines){
			line.render(g2d);
		}
	}
}
