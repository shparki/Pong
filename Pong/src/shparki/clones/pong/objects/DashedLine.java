package shparki.clones.pong.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import shparki.clones.pong.graphics.Window;

public class DashedLine {
	
	private int startX, startY, endX, endY;
	private int width, height;
	
	
	public DashedLine(int sX, int sY, int eX, int eY, int width, int height){
		startX = sX; startY = sY;
		endX = eX; endY = eY;
		this.width = width; this.height = height;
	}
	
	public void render(Graphics2D g2d){
		g2d.setColor(Color.BLACK);
		int count = Math.abs((endY - startY) / height);
		if (count %2 == 0) count --;
		
		int yOffset = (Window.getHeight() - (count * height)) / 2;
		for (int i = 0; i < count; i++){
			if (i % 2 == 0){
				g2d.fillRect(startX, i * height + yOffset, width, height);
			}
		}
	}
	
	
	
}
