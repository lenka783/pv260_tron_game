package cz.muni.pv260.tron.engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public abstract class Room implements KeyListener, MouseListener, MouseMotionListener {
	
	private Dimension dimension;
	private List<Item> items;
	
	public Room(Dimension dimension) {
		this(dimension, new ArrayList<>());
	}
	
	public Room(Dimension dimension, List<Item> items) {
		this.dimension = dimension;
		this.items = items;
	}
	
	public Dimension getDimension() {
		return dimension;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void evaluateCollisions() {
		for (Item item1 : items) {
			for (Item item2 : items) {
				if (item1 != item2) {
					if (item1.isInCollision(item2)) {
						item1.collided(item2);
					}
				}
			}
		}
	}
	
	public void update(long timePassed) {
		for (Item item : items) {
			item.update(timePassed, this);
		}
	}
	
	public void draw(Graphics graphics) {
		for (Item item : items) {
			item.draw(graphics);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		for (Item item : items) {
			item.keyPressed(e);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		for (Item item : items) {
			item.keyReleased(e);
		}
		
	}
	
	public void keyTyped(KeyEvent e) {
		for (Item item : items) {
			item.keyTyped(e);
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {
		for (Item item : items) {
			item.mouseClicked(e);
		}
		
	}
	
	public void mouseEntered(MouseEvent e) {
		for (Item item : items) {
			item.mouseEntered(e);
		}
	}
	
	public void mouseExited(MouseEvent e) {
		for (Item item : items) {
			item.mouseExited(e);
		}
	}
	
	public void mousePressed(MouseEvent e) {
		for (Item item : items) {
			item.mousePressed(e);
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		for (Item item : items) {
			item.mouseReleased(e);
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		for (Item item : items) {
			item.mouseDragged(e);
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		for (Item item : items) {
			item.mouseMoved(e);
		}
	}
	
}
