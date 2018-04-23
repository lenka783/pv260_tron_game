package cz.muni.pv260.tron.engine.presentation;


import cz.muni.pv260.tron.engine.model.Room;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface Presenter<R extends Room> extends KeyListener, MouseListener, MouseMotionListener {
	
	R getRoom();
	
	R initRoom();
	
	Window initWindow();
	
	void update(long timePassed);
	
	void terminate();
	
	Dimension getDimension();
	
}
