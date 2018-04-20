package cz.muni.pv260.tron.game;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private int centrex;
    private int centrey;
    private int currentDirection;
    private ArrayList<Integer> pathx = new ArrayList();
    private ArrayList<Integer> pathy = new ArrayList();
    private final Color color;

    public final int keyUp;
    public final int keyDown;
    public final int keyLeft;
    public final int keyRight;

    public Player(int centrex, int centrey, int currentDirection, Color color, int keyUp, int keyDown, int keyLeft, int keyRight) {
        this.centrex = centrex;
        this.centrey = centrey;
        this.currentDirection = currentDirection;
        this.color = color;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
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

}