package shparki.clones.pong.graphics;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import shparki.clones.pong.io.Input;

public class Window {
	
	private static JFrame frame;
	private static Canvas content;
	private static Input input;
	
	public static void create(int width, int height, String title, Canvas content){
		Window.content = content;
		content.setPreferredSize(new Dimension(width, height));
		content.setMinimumSize(new Dimension(width, height));
		content.setMaximumSize(new Dimension(width, height));
		
		frame = new JFrame(title);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLayout(new BorderLayout());
		frame.add(content, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		input = new Input();
		content.setFocusable(true);
		content.requestFocus();
		content.addKeyListener(input);
	}
	
	public static void update(){
		Input.update();
	}
	
	
	public static int getWidth() { return content.getWidth(); }
	public static int getHeight() { return content.getHeight(); }
	
	public static String getTitle() { return frame.getTitle(); }
	public static void setTitle(String title) { frame.setTitle(title); }
	
	
	
}
