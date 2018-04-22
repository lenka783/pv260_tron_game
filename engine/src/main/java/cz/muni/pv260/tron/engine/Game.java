package cz.muni.pv260.tron.engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Game implements KeyListener, MouseListener, MouseMotionListener {

	private boolean running;
	protected ScreenManager sm;
	private List<Item> items = new ArrayList<>();
	
	public void run(){
		try {
			init();
			gameLoop();
		} finally {
			terminate();
		}
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public <T> List<T> getItems(Class<T> clazz) {
		// TODO Should be some kind of groups? Instead of by Class.
		return (List<T>) items.stream().filter(clazz::isInstance).collect(Collectors.toList());
	}
	
	public void init(){
		sm = new ScreenManager();
		sm.setFullScreen();
		Window w = sm.getFullScreenWindow();
		w.setFont(new Font("Arial",Font.PLAIN,20));
		w.setBackground(Color.WHITE);
		w.setForeground(Color.RED);
		w.setCursor(w.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),"null")); 
		running = true;
	}
	
	public void gameLoop(){
		long startTime = System.currentTimeMillis();
		long cumTime = startTime;
		
		// TODO Add Game scheduler
		while (running && cumTime != startTime + 300000){
			long timePassed = System.currentTimeMillis()-cumTime;
			cumTime += timePassed;
			update(timePassed);
			Graphics2D g = sm.getGraphics();
			draw(g);
			g.dispose();
			sm.update();
			
			try{
				Thread.sleep(20);
			}catch(Exception ex){
				// TODO
			}
		}
	}
	
	public void stop(){
		running = false;
	}
	
	public void terminate(){
		sm.restoreScreen();
	}
	
	public void update(long timePassed) {
		for (Item item : items) {
			item.update(timePassed, new Rectangle(sm.getWidth(), sm.getHeight()));
		}
	}
	
	public void draw(Graphics graphics) {
		for (Item item : items) {
			item.draw(graphics, new Rectangle(sm.getWidth(), sm.getHeight()));
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
