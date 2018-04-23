package cz.muni.pv260.tron.engine;

import java.awt.geom.Area;

public class EmptyCollisionMask extends CollisionMask {
	
	@Override
	protected Area getArea() {
		return new Area();
	}
}
