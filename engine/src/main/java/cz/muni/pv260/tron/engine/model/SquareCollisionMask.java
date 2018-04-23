package cz.muni.pv260.tron.engine.model;

import cz.muni.pv260.tron.engine.model.CollisionMask;

import java.awt.*;
import java.awt.geom.Area;

public class SquareCollisionMask extends CollisionMask {
	
	private int size;
	private Point offset;
	
	public SquareCollisionMask(int size, Point offset) {
		this.size = size;
		this.offset = offset;
	}
	
	public SquareCollisionMask(int size) {
		this(size, new Point(0, 0));
	}
	
	@Override
	protected Area getArea() {
		return new Area(new Rectangle(offset.x, offset.y, size, size));
	}
}
