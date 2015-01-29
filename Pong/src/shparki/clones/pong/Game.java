package shparki.clones.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import shparki.clones.pong.graphics.Background;
import shparki.clones.pong.graphics.Window;
import shparki.clones.pong.io.Input;
import shparki.clones.pong.objects.Ball;
import shparki.clones.pong.objects.Hexagon;
import shparki.clones.pong.objects.Paddle;
import shparki.clones.pong.util.Point2;

public class Game {
	
	public static int VERT_PADDING, HORIZ_PADDING;
	public static int LINE_THICKNESS;
	public static int UPPER_LIMIT, LOWER_LIMIT;
	public static int RIGHT_LIMIT, LEFT_LIMIT;
	public static int RENDER_WIDTH, RENDER_HEIGHT;
	
	public static final int SCOREFONT_SIZE = 80;
	public static final int TITLEFONT_SIZE = 120;
	
	
	private boolean gameStarted = false;
	
	private Paddle leftPlayer, rightPlayer;
	private Ball ball;
	private Font titleFont, scoreFont;
	
	public Game(){
		VERT_PADDING = 0; HORIZ_PADDING = 0;
		LINE_THICKNESS = 6;
		UPPER_LIMIT = VERT_PADDING + LINE_THICKNESS;
		LOWER_LIMIT = Window.getHeight() - (VERT_PADDING + LINE_THICKNESS);
		LEFT_LIMIT = HORIZ_PADDING + LINE_THICKNESS;
		RIGHT_LIMIT = Window.getWidth() - (HORIZ_PADDING + LINE_THICKNESS);
		RENDER_WIDTH = Window.getWidth() - (2 * HORIZ_PADDING + 2 * LINE_THICKNESS);
		RENDER_HEIGHT = Window.getHeight() - (2 * VERT_PADDING + 2 * LINE_THICKNESS);
		
		Background.init();
		
		
		leftPlayer = new Paddle(new Point2(Game.RENDER_WIDTH / 10 * 2, Game.RENDER_HEIGHT / 2 + Paddle.HEIGHT / 2), KeyEvent.VK_W, KeyEvent.VK_S);
		rightPlayer = new Paddle(new Point2(Game.RENDER_WIDTH / 10 * 8 + Paddle.WIDTH, Game.RENDER_HEIGHT / 2 + Paddle.HEIGHT / 2), KeyEvent.VK_UP, KeyEvent.VK_DOWN);
	
		ball = new Ball(new Point2(Window.getWidth() / 2 - Ball.SIZE / 2, Window.getHeight() / 2 - Ball.SIZE / 2));
		ball.addPaddle(leftPlayer);
		ball.addPaddle(rightPlayer);
		
		try{
			titleFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/res/titleFont.ttf"));
			titleFont = titleFont.deriveFont(Font.PLAIN, 20);
		} catch (Exception ex) { ex.printStackTrace(); }
		try{
			scoreFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/res/scoreFont.ttf"));
			scoreFont = scoreFont.deriveFont(Font.PLAIN, 80);
		} catch (Exception ex) { ex.printStackTrace(); }
	}
	
	public void update(){
		Background.update();
		
		if (Input.isKeyPressed(KeyEvent.VK_ENTER)) { gameStarted = true; }
		
		leftPlayer.update(); rightPlayer.update();
		if (gameStarted) { ball.update(); }
		
		if (ball.isOnHit()) {
			if (ball.getPosition().getX() <= LEFT_LIMIT){ rightPlayer.incPoints(1); ball.reset(); }
			if (ball.getPosition().getX() + Ball.SIZE >= RIGHT_LIMIT) { leftPlayer.incPoints(1); ball.reset(); }
		}
	}
	
	public void render(Graphics2D g2d){
		Background.render(g2d);
		
		leftPlayer.render(g2d); rightPlayer.render(g2d);
		ball.render(g2d);
		
		
		g2d.setColor(Color.BLACK);
		g2d.setFont(scoreFont);
		g2d.drawString("" + leftPlayer.getPoints(), Window.getWidth() / 10 * 4, 40 + SCOREFONT_SIZE);
		g2d.drawString("" + rightPlayer.getPoints(), Window.getWidth() / 10 * 6 - SCOREFONT_SIZE, 40 + SCOREFONT_SIZE);
	}
	
	
}
