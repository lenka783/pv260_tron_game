package cz.muni.pv260.tron.game.model;

import cz.muni.pv260.tron.engine.model.CollisionMask;
import cz.muni.pv260.tron.engine.model.Item;
import cz.muni.pv260.tron.engine.model.ComposedCollisionMask;
import cz.muni.pv260.tron.engine.model.Room;
import cz.muni.pv260.tron.engine.model.SquareCollisionMask;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player extends Item {
	
	private int speed = 5;
	private Direction currentDirection;
	private List<Point> path = new ArrayList<>();
	private final Color color;
	private final boolean mouseEnabled;
	
	// TODO Replace by some Control Object
	private final int keyUp;
	private final int keyDown;
	private final int keyLeft;
	private final int keyRight;
	
	public Player(Point center, Direction currentDirection, Color color, int keyUp, int keyDown, int keyLeft, int keyRight, boolean mouseEnabled) {
		super(center);
		center.x = center.x/speed*speed;
		center.y = center.y/speed*speed;
		this.currentDirection = currentDirection;
		this.color = color;
		this.keyUp = keyUp;
		this.keyDown = keyDown;
		this.keyLeft = keyLeft;
		this.keyRight = keyRight;
		this.mouseEnabled = mouseEnabled;
	}
	
	@Override
	public boolean isInCollision(Item item) {
		Point offset = new Point(item.getCenter().x - getCenter().x, item.getCenter().y - getCenter().y);
		return (new SquareCollisionMask(speed-1)).isInCollision(item.getCollisionMask(), offset);
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
	
	private void makeStep(Dimension roomDimension) {
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
	public void update(long timePassed, Room room) {
		makeStep(room.getDimension());
		List<CollisionMask> collsionMasks = path.stream()
				.map((point) -> new SquareCollisionMask(
						speed,
						new Point(point.x - getCenter().x, point.y - getCenter().y)
				))
				.collect(Collectors.toList());
		this.setCollisionMask(new ComposedCollisionMask(collsionMasks));
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

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		if (mouseEnabled) {
			if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
				if (currentDirection != Direction.RIGHT){
					currentDirection = Direction.LEFT;
				}
			}
			if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
				if (currentDirection != Direction.LEFT){
					currentDirection = Direction.RIGHT;
				}
			}
		}
	}

	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	
}