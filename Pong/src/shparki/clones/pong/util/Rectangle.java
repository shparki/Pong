package shparki.clones.pong.util;

public class Rectangle {
	
	private int x, y;
	private int width, height;
	
	public Rectangle(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	
	public boolean contains(Vector2 p) {
		if (x >= p.getX() && x <= p.getX() + width){
			if (y >= p.getY() && y <= p.getY() + height){
				return true;
			}
		}
		return false;
	}
	
}
