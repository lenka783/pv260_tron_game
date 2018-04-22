package cz.muni.pv260.tron.engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Item implements KeyListener, MouseListener, MouseMotionListener {
    
    private Point center;

    public Item(Point center) {
        this.center = center;
    }
    
    public abstract void update(long timePassed, Room room);
    
    // TODO add class Drawable<T extends Item>?
    public abstract void draw(Graphics graphics);
	
	// TODO add class CollisionMask<T extends Item>?
    public abstract boolean isInCollision(Point point);
    
    public Point getCenter() {
        return center;
    }

    protected void setCenter(Point center) {
        this.center = center;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    
    }
    
    @Override
    public void keyPressed(KeyEvent keyEvent) {
    
    }
    
    @Override
    public void keyReleased(KeyEvent keyEvent) {
    
    }
    
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    
    }
    
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    
    }
    
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    
    }
    
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    
    }
    
    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    
    }
    
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
    
    }
    
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    
    }
}