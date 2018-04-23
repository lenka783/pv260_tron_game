package cz.muni.pv260.tron.engine.model;

import java.awt.geom.Area;
import java.util.List;

public class ComposedCollisionMask extends CollisionMask {
	
	private List<CollisionMask> collisionMasks;
	
	public ComposedCollisionMask(List<CollisionMask> collisionMasks) {
		this.collisionMasks = collisionMasks;
	}
	
	@Override
	protected Area getArea() {
		Area area = new Area();
		for (CollisionMask collisionMask : collisionMasks) {
			area.add(collisionMask.getArea());
		}
		return area;
	}
}
