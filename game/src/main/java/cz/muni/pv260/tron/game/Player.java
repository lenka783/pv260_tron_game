package cz.muni.pv260.tron.game;

import cz.muni.pv260.tron.engine.Item;
import cz.muni.pv260.tron.engine.ScreenManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Item {
	
    private int currentDirection; // TODO should be enum
    private ArrayList<Integer> pathx = new ArrayList(); // TODO only one list of points
    private ArrayList<Integer> pathy = new ArrayList();
    private final Color color;

    private final int keyUp;
	private final int keyDown;
	private final int keyLeft;
	private final int keyRight;

    public Player(int centrex, int centrey, int currentDirection, Color color, int keyUp, int keyDown, int keyLeft, int keyRight) {
	    super(centrex, centrey);
        this.currentDirection = currentDirection;
        this.color = color;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
    }

    public int getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(int currentDirection) {
        this.currentDirection = currentDirection;
    }

    public ArrayList<Integer> getPathx() {
        return pathx;
    }

    public ArrayList<Integer> getPathy() {
        return pathy;
    }

    public void addPathxy(int pahtx, int pathy) {
        this.pathx.add(pahtx);
        this.pathy.add(pathy);
    }

    public Color getColor() {
        return color;
    }

    public void makeStep(ScreenManager sm, int moveAmount) {
        switch (getCurrentDirection()) {
            case 0:
                if (getCentrey() > 0) {
                    setCentrey(getCentrey() - moveAmount);
                } else {
                    setCentrey(sm.getHeight());
                }
                break;
            case 1:
                if (getCentrex() < sm.getWidth()) {
                    setCentrex(getCentrex() + moveAmount);
                } else {
                    setCentrex(0);
                }
                break;
            case 2:
                if (getCentrey() < sm.getHeight()) {
                    setCentrey(getCentrey() + moveAmount);
                } else {
                    setCentrey(0);
                }
                break;
            case 3:
                if (getCentrex() > 0) {
                    setCentrex(getCentrex() - moveAmount);
                } else {
                    setCentrex(sm.getWidth());
                }
                break;
        }
    }

    public boolean isInCollision(Player that, int x) {
        return ((this.getCentrex() == this.getPathx().get(x)) && (this.getCentrey() == this.getPathy().get(x)))
                || ((that.getCentrex() == that.getPathx().get(x)) && (that.getCentrey() == that.getPathy().get(x)))
                || ((this.getCentrex() == that.getPathx().get(x)) && (this.getCentrey() == that.getPathy().get(x)))
                || ((that.getCentrex() == this.getPathx().get(x)) && (that.getCentrey() == this.getPathy().get(x)));

    }
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == keyUp) {
			if (currentDirection != 2){
				currentDirection = 0;
			}
		} else if (e.getKeyCode() == keyDown) {
			if (currentDirection != 0){
				currentDirection = 2;
			}
		} else if (e.getKeyCode() == keyRight) {
			if (currentDirection != 3){
				currentDirection = 1;
			}
		} else if (e.getKeyCode() == keyLeft) {
			if (currentDirection != 1){
				currentDirection = 3;
			}
		}
	}
	
}