package cz.muni.pv260.tron.engine.presentation;


import cz.muni.pv260.tron.engine.model.Room;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class ScreenPresenter<R extends Room> implements Presenter<R> {
	
	private R room;
	private ScreenManager sm;
	
	public ScreenPresenter() {
		this.sm = new ScreenManagerImpl();
		initWindow();
		this.room = initRoom();
	}
	
	@Override
	public Window initWindow() {
		Window window = this.sm.initWindow();
		window.addKeyListener(this);
		window.addMouseListener(this);
		window.addMouseMotionListener(this);
		return window;
	}
	
	@Override
	public void update(long timePassed) {
		room.update(timePassed);
		draw(getRoom(), sm.getGraphics());
		sm.update();
	}
	
	public R getRoom() {
		return room;
	}
	
	public abstract void draw(R room, Graphics graphics);
	
	@Override
	public void terminate() {
		room.terminate();
		sm.restoreWindow();
	}
	
	@Override
	public Dimension getDimension() {
		
		Window window = sm.getWindow();
		return window.getSize();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		room.keyTyped(e);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		room.keyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		room.keyReleased(e);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		room.mouseClicked(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		room.mousePressed(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		room.mouseReleased(e);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		room.mouseEntered(e);
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		room.mouseExited(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		room.mouseDragged(e);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		room.mouseMoved(e);
	}
}
