package shparki.clones.pong.util;

public class Vector2 {
	
	private double x, y;
	
	public Vector2(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	
	public double getX() { return x; }
	public void setX(double x) { this.x = x; }
	public void incX(double i) { x += i; }
	public double getY() { return y; }
	public void setY(double y) { this.y = y; }
	public void incY(double i) { y += i; }
	
	public void multiply(double d) { x *= d; y *= d; }
	
	public Vector2 getClone() { return new Vector2(x, y); }
	
	
}
