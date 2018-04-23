package cz.muni.pv260.tron.game;

import cz.muni.pv260.tron.engine.Item;
import cz.muni.pv260.tron.engine.PointListCollisionMask;
import cz.muni.pv260.tron.engine.Room;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player extends Item {
	
	private int speed = 5;
	private Direction currentDirection;
	private List<Point> path = new ArrayList<>();
	private final Color color;
	
	private final int keyUp;
	private final int keyDown;
	private final int keyLeft;
	private final int keyRight;
	
	public Player(Point center, Direction currentDirection, Color color, int keyUp, int keyDown, int keyLeft, int keyRight) {
		super(center);
		center.x = center.x/speed*speed;
		center.y = center.y/speed*speed;
		this.currentDirection = currentDirection;
		this.color = color;
		this.keyUp = keyUp;
		this.keyDown = keyDown;
		this.keyLeft = keyLeft;
		this.keyRight = keyRight;
	}
	
	public Direction getCurrentDirection() {
		return currentDirection;
	}
	
	public List<Point> getPath() {
		return path;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void makeStep(Dimension roomDimension) {
		switch (currentDirection) {
			case UP:
				if (getCenter().y > 0) {
					getCenter().y -= speed;
				} else {
					getCenter().y = ((int)roomDimension.getHeight()/speed)*speed;
				}
				break;
			case RIGHT:
				if (getCenter().x < roomDimension.getWidth()) {
					getCenter().x += speed;
				} else {
					getCenter().x = 0;
				}
				break;
			case DOWN:
				if (getCenter().y < roomDimension.getHeight()) {
					getCenter().y += speed;
				} else {
					getCenter().y = 0;
				}
				break;
			case LEFT:
				if (getCenter().x > 0) {
					getCenter().x -= speed;
				} else {
					getCenter().x = ((int) roomDimension.getWidth()/speed)*speed;
				}
				break;
		}
	}
	
	@Override
	public void update(long timePassed,  Room room) {
		makeStep(room.getDimension());
		List<Point> collsionMaskPoints = new ArrayList<>();
		for (Point point : path) {
			collsionMaskPoints.add(new Point(point.x - getCenter().x, point.y - getCenter().y));
		}
		// TODO remove magic number 10, 10. Should be related to image size
		this.setCollisionMask(new PointListCollisionMask(collsionMaskPoints, new Dimension(10, 10)));
		path.add(new Point(getCenter()));
	}
	
	@Override
	public void collided(Item item) {
		if (item instanceof Player) {
			// TODO better handling. Should call Game.stop()
			System.exit(0);
		}
	}
	
	@Override
	public void draw(Graphics graphics) {
		drawPath(graphics);
	}
	
	private void drawPath(Graphics graphic) {
		for (Point point : path) {
			graphic.setColor(color);
			graphic.fillRect(point.x, point.y, 10, 10);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == keyUp) {
			if (currentDirection != Direction.DOWN){
				currentDirection = Direction.UP;
			}
		} else if (e.getKeyCode() == keyDown) {
			if (currentDirection != Direction.UP){
				currentDirection = Direction.DOWN;
			}
		} else if (e.getKeyCode() == keyRight) {
			if (currentDirection != Direction.LEFT){
				currentDirection = Direction.RIGHT;
			}
		} else if (e.getKeyCode() == keyLeft) {
			if (currentDirection != Direction.RIGHT){
				currentDirection = Direction.LEFT;
			}
		}
	}
	
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	
}