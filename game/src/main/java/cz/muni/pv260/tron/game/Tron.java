package cz.muni.pv260.tron.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Tron extends Core implements KeyListener, MouseListener,
		MouseMotionListener {

	private List<Player> players = new ArrayList<>();

	private int moveAmount = 5;
	private int pathsLength;

	public void init() {
		super.init();
		
		Window window = sm.getFullScreenWindow();
		window.addKeyListener(this);
		window.addMouseListener(this);
		window.addMouseMotionListener(this);

		initPlayers();
	}
	
	public static void main(String[] args) {
		new Tron().run();
	}
	
	public void draw(Graphics2D g) {
		movePlayers();
		checkCollision();
		addPathsxy();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
		drawPlayersPaths(g);
	}

	public void keyPressed(KeyEvent e) {
		for (int i = 0; i < players.size(); i++) {
			if (e.getKeyCode() == players.get(i).keyUp) {
				if (players.get(i).getCurrentDirection() != 2){
					players.get(i).setCurrentDirection(i);
				}
			} else if (e.getKeyCode() == players.get(i).keyDown) {
				if (players.get(i).getCurrentDirection() != 0){
					players.get(i).setCurrentDirection(2);
				}
			} else if (e.getKeyCode() == players.get(i).keyRight) {
				if (players.get(i).getCurrentDirection() != 3){
					players.get(i).setCurrentDirection(1);
				}
			} else if (e.getKeyCode() == players.get(i).keyLeft) {
				if (players.get(i).getCurrentDirection() != 1){
					players.get(i).setCurrentDirection(3);
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
	
	}
	
	public void keyTyped(KeyEvent arg0) {
	
	}
	
	public void mouseClicked(MouseEvent e) {
	
	}
	
	public void mouseEntered(MouseEvent arg0) {
	}
	
	public void mouseExited(MouseEvent arg0) {
	}
	
	public void mousePressed(MouseEvent e) {
	}
	
	public void mouseReleased(MouseEvent e) {
	}
	
	public void mouseDragged(MouseEvent e) {
	
	}
	
	public void mouseMoved(MouseEvent e) {
	
	}

	private void initPlayers(){
		Player player1 = new Player(
				40, 40, 1, Color.GREEN,
				KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT
		);
		Player player2 = new Player(
				600, 440, 3, Color.RED,
				KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D
		);

		Player player3 = new Player(
				600, 240, 3, Color.YELLOW,
				KeyEvent.VK_U, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_H
		);

		players.add(player1);
		players.add(player2);
		players.add(player3);
		updatePathsLength();
	}

	private void updatePathsLength() {
		pathsLength = players.get(0).getPathx().size();
	}

	private void movePlayers() {
		for (Player player : players) {
			player.makeStep(sm, moveAmount);
		}
		updatePathsLength();
	}

	private void checkCollision() {
		for (int i = 1; i < players.size(); i++) {
			for (int x = 0; x < pathsLength; x++){
				if (players.get(i-1).isInCollision(players.get(i), x)){
					System.exit(0);
				}
			}
		}
	}

	private void addPathsxy(){
		for (Player player : players) {
			player.addPathxy(player.getCentrex(), player.getCentrey());
		}
	}

	private void drawPlayersPaths(Graphics2D graphic) {
		for (int x = 0; x < pathsLength; x++){
			for (Player player : players) {
				graphic.setColor(player.getColor());
				graphic.fillRect(player.getPathx().get(x), player.getPathy().get(x), 10, 10);
			}
		}
	}
}
