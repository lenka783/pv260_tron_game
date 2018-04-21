package cz.muni.pv260.tron.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Item implements KeyListener, MouseListener, MouseMotionListener {
    
    private int centrex; // TODO point
    private int centrey;

    public Item(int centrex, int centrey) {
        this.centrex = centrex;
        this.centrey = centrey;
    }

    public int getCentrex() {
        return centrex;
    }

    public void setCentrex(int centrex) {
        this.centrex = centrex;
    }

    public int getCentrey() {
        return centrey;
    }

    public void setCentrey(int centrey) {
        this.centrey = centrey;
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