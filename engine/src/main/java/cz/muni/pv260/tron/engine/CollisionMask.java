package cz.muni.pv260.tron.engine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public abstract class CollisionMask {
	
	public boolean isInCollision(CollisionMask collisionMask, Point offset) {
		Area areaA = new Area(getArea());
		Area areaB = collisionMask.getArea().createTransformedArea(new AffineTransform(1, 0, 0, 1, offset.x, offset.y));
		areaA.intersect(areaB);
		return !areaA.isEmpty();
	}
	
	protected abstract Area getArea();
	
}
