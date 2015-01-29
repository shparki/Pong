package shparki.clones.pong.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import shparki.clones.pong.Game;
import shparki.clones.pong.objects.BreakingLine;
import shparki.clones.pong.objects.DashedLine;
import shparki.clones.pong.objects.Hexagon;
import shparki.clones.pong.util.Point2;
import shparki.clones.pong.util.Vector2;

public class Background {
	
	public static final int HEX_ADD_TIMER = 15; // How long until a new hexagon
	public static final int HEX_SPEED = 0; // How fast the hexagon "moves"
	public static final double HEX_ACCEL = .005;
	public static final double HEX_ACCEL_ACCEL = .0006;
	public static final Vector2 HEX_CENTER_SPEED = new Vector2(-185, -60);
	public static Point2 HEX_CENTER;
	
	public static final Vector2 HEX_MOD_SPEED = new Vector2(-3.6, -.3);
	
	private static DashedLine centerLine;
	private static BreakingLine testLine;
	private static ArrayList<Hexagon> hexagons = new ArrayList<Hexagon>();
	private static ArrayList<Double> hexagonVelocities = new ArrayList<Double>();
	private static ArrayList<Double> hexagonAccelerations = new ArrayList<Double>();
	
	public static void init(){
		centerLine = new DashedLine(Window.getWidth() / 2 - Game.LINE_THICKNESS / 2, Game.UPPER_LIMIT, Window.getWidth() / 2 + Game.LINE_THICKNESS / 2, Game.LOWER_LIMIT, Game.LINE_THICKNESS, 6);
		testLine = new BreakingLine(new Point2(Window.getWidth() / 2, Window.getHeight() / 2), new Point2(0, Window.getHeight()), 1.0);
		HEX_CENTER = new Point2(Window.getWidth(), Window.getHeight() / 10 * 8);
	}
	
	private static int counter = 0;
	public static void update(){
		
		
		
		for (int i = 0; i < hexagons.size(); i++){
			hexagonAccelerations.set(i, hexagonAccelerations.get(i) + HEX_ACCEL_ACCEL);
			hexagonVelocities.set(i, (hexagonVelocities.get(i) + hexagonAccelerations.get(i)));
			hexagons.get(i).incHeight(hexagonVelocities.get(i).intValue());
			hexagons.get(i).getCenter().linearInterp(HEX_CENTER_SPEED);
		}
		
		HEX_CENTER.incX(HEX_MOD_SPEED.getX());

		if (counter >= HEX_ADD_TIMER){
			counter -= HEX_ADD_TIMER;
			//hexagons.add(new Hexagon(new Point2(Window.getWidth() / 2, Window.getHeight() / 2), 1));
			hexagons.add(new Hexagon(HEX_CENTER.getClone(), 1));
			hexagonVelocities.add((double) HEX_SPEED);
			hexagonAccelerations.add((double)HEX_ACCEL);

		}
		for (Hexagon h : hexagons){
			h.update();
		}
		
		counter++;
	}
	
	public static void render(Graphics2D g2d){
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, Window.getWidth(), Window.getHeight());
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(Game.HORIZ_PADDING, Game.VERT_PADDING, Game.RENDER_WIDTH + Game.LINE_THICKNESS * 2, Game.RENDER_HEIGHT + Game.LINE_THICKNESS * 2);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(Game.HORIZ_PADDING + Game.LINE_THICKNESS, Game.VERT_PADDING + Game.LINE_THICKNESS, Game.RENDER_WIDTH, Game.RENDER_HEIGHT);
		
		centerLine.render(g2d);
		testLine.render(g2d);
		
		for (Hexagon h : hexagons){
			h.render(g2d);
		}
	}
	
	
	
	
}
