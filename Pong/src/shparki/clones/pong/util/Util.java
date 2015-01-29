package shparki.clones.pong.util;

import shparki.clones.pong.Engine;

public class Util {
	
	public static Vector2 linearInterp (Vector2 start, Vector2 end){
		double x = (start.getX() + ((double)Time.getUpdateDelta() / Time.SECOND) * (end.getX() - start.getX()));
		double y = (start.getY() + ((double)Time.getUpdateDelta() / Time.SECOND) * (end.getY() - start.getY()));
		
		return new Vector2(x, y);
	}
	
	
	
	
}
