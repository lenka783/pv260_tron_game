package cz.muni.pv260.tron.engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Game implements KeyListener, MouseListener, MouseMotionListener {

	private boolean running;
	protected ScreenManager sm;
	private Room room;
	
	public void run(){
		try {
			init();
			gameLoop();
		} finally {
			terminate();
		}
	}
	
	protected abstract Room initRoom(Dimension dimension);
	
	public Room getRoom() {
		return room;
	}
	
	public void init(){
		sm = new ScreenManagerImpl();
		Window window = sm.initWindow();
		window.addKeyListener(this);
		window.addMouseListener(this);
		window.addMouseMotionListener(this);
		
		room = initRoom(new Dimension(window.getWidth(), window.getHeight()));
		
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
			
			draw(sm.getGraphics());
			sm.update();
			
			try{
				Thread.sleep(20);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	public void stop(){
		running = false;
	}
	
	public void terminate(){
		sm.restoreWindow();
	}
	
	public void update(long timePassed) {
		room.update(timePassed);
	}
	
	public void draw(Graphics graphics) {
		room.draw(graphics);
	}
	
	public void keyPressed(KeyEvent e) {
		room.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		room.keyReleased(e);
	}
	
	public void keyTyped(KeyEvent e) {
		room.keyTyped(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		room.mouseClicked(e);
	}
	
	public void mouseEntered(MouseEvent e) {
		room.mouseEntered(e);
	}
	
	public void mouseExited(MouseEvent e) {
		room.mouseExited(e);
	}
	
	public void mousePressed(MouseEvent e) {
		room.mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		room.mouseReleased(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		room.mouseDragged(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		room.mouseMoved(e);
	}
}
