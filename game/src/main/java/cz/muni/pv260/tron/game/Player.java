package cz.muni.pv260.tron.game;

import cz.muni.pv260.tron.engine.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player extends Item {
	
	private int moveAmount = 5;
    private int currentDirection; // TODO should be enum
    private List<Integer> pathx = new ArrayList<>(); // TODO only one list of points
    private List<Integer> pathy = new ArrayList<>();
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

    public List<Integer> getPathx() {
        return pathx;
    }

    public List<Integer> getPathy() {
        return pathy;
    }

    public void addPathxy(int pahtx, int pathy) {
        this.pathx.add(pahtx);
        this.pathy.add(pathy);
    }

    public Color getColor() {
        return color;
    }

    public void makeStep(Rectangle roomDimension) {
        switch (getCurrentDirection()) {
            case 0:
                if (getCentrey() > 0) {
                    setCentrey(getCentrey() - moveAmount);
                } else {
                    setCentrey(((int)roomDimension.getHeight()/10)*10);
                }
                break;
            case 1:
                if (getCentrex() < roomDimension.getWidth()) {
                    setCentrex(getCentrex() + moveAmount);
                } else {
                    setCentrex(0);
                }
                break;
            case 2:
                if (getCentrey() < roomDimension.getHeight()) {
                    setCentrey(getCentrey() + moveAmount);
                } else {
                    setCentrey(0);
                }
                break;
            case 3:
                if (getCentrex() > 0) {
                    setCentrex(getCentrex() - moveAmount);
                } else {
                    setCentrex(((int) roomDimension.getWidth()/10)*10);
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
	public void update(long timePassed, Rectangle roomDimension) {
        makeStep(roomDimension);
		addPathxy(getCentrex(), getCentrey());
	}
	
	@Override
	public void draw(Graphics graphics, Rectangle roomDimension) {
		drawPath(graphics);
	}
	
	private void drawPath(Graphics graphic) {
		for (int x = 0; x < pathx.size(); x++){
			graphic.setColor(color);
			graphic.fillRect(pathx.get(x), pathy.get(x), 10, 10);
		}
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