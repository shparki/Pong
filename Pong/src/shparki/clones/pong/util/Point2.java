package shparki.clones.pong.util;

public class Point2{
	
	public double x, y;
	
	public Point2(double x, double y){
		this.x = x; this.y = y;
	}
	
	public double getX() { return x; }
	public void setX(double x) { this.x = x; }
	public void incX(double i) { x += i; }
	
	public double getY() { return  y; }
	public void setY(double y) { this.y = y; }
	public void incY(double i) { y += i; }

	public Point2 getClone() { return new Point2(x, y); }
	
	public void add(Vector2 v){
		x += v.getX();
		y += v.getY();
	}
	public void sub(Vector2 v){
		x -= v.getX();
		y += v.getY();
	}
	
	public void linearInterp(Point2 p){
		x = (x + ((double)Time.getUpdateDelta() / Time.SECOND) * (p.getX() - x));
	    y = (y + ((double)Time.getUpdateDelta() / Time.SECOND) * (p.getY() - y));
	}
	public void linearInterp(Vector2 v){
		Point2 dest = new Point2(x + v.getX(), y + v.getY());
		linearInterp(dest);
	}
	
}
