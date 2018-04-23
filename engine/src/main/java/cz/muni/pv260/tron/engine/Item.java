package cz.muni.pv260.tron.engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Item implements KeyListener, MouseListener, MouseMotionListener {
    
    private Point center;
    private CollisionMask collisionMask;
    
    public Item(Point center) {
        this(center, new EmptyCollisionMask());
    }
    
    public Item(Point center, CollisionMask collisionMask) {
        this.center = center;
        this.collisionMask = collisionMask;
    }
    
    public abstract void update(long timePassed, Room room);
    
    public abstract void collided(Item item);
    
    // TODO add class Drawable<T extends Item>?
    public abstract void draw(Graphics graphics);
    
    public boolean isInCollision(Item item) {
        Point offset = new Point(item.getCenter().x - center.x, item.getCenter().y - center.y);
        return collisionMask.isInCollision(item.getCollisionMask(), offset);
    }
    
    public Point getCenter() {
        return center;
    }

    protected void setCenter(Point center) {
        this.center = center;
    }
    
    public CollisionMask getCollisionMask() {
        return collisionMask;
    }
    
    protected void setCollisionMask(CollisionMask collisionMask) {
        this.collisionMask = collisionMask;
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