package cz.muni.pv260.tron.engine;

import java.awt.*;
import java.awt.geom.Area;
import java.util.List;

public class PointListCollisionMask extends CollisionMask {
	
	private List<Point> points;
	private Dimension size;
	
	public PointListCollisionMask(List<Point> points, Dimension size) {
		this.points = points;
		this.size = size;
	}
	
	@Override
	protected Area getArea() {
		Area area = new Area();
		for (Point point : points) {
			area.add(new Area(new Rectangle(point.x, point.y, size.width, size.height)));
		}
		return area;
	}
}
