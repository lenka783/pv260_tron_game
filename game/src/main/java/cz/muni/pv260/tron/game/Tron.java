package cz.muni.pv260.tron.game;

import cz.muni.pv260.tron.engine.Game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Tron extends Game {

	private int moveAmount = 5;
	private int pathsLength; // TODO should be in Player Object
	
	public static void main(String[] args) {
		new Tron().run();
	}
	
	public void init() {
		super.init();
		
		Window window = sm.getFullScreenWindow();
		window.addKeyListener(this);
		window.addMouseListener(this);
		window.addMouseMotionListener(this);

		initPlayers();
	}
	
	private List<Player> getPlayers() {
		return getItems(Player.class);
	}
	
	public void draw(Graphics2D g) {
		movePlayers();
		checkCollision();
		addPathsxy();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, sm.getWidth(), sm.getHeight());
		
		drawPlayersPaths(g);
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
		
		addItem(player1);
		addItem(player2);
		addItem(player3);
		updatePathsLength();
	}

	private void updatePathsLength() {
		pathsLength = getPlayers().get(0).getPathx().size();
	}

	private void movePlayers() {
		for (Player player : getPlayers()) {
			player.makeStep(sm, moveAmount);
		}
		updatePathsLength();
	}

	private void checkCollision() {
		for (int i = 1; i < getItems().size(); i++) {
			// TODO should be inside Player
			for (int x = 0; x < pathsLength; x++){
				if (getPlayers().get(i-1).isInCollision(getPlayers().get(i), x)){
					System.exit(0);
				}
			}
		}
	}

	private void addPathsxy(){
		for (Player player : getPlayers()) {
			player.addPathxy(player.getCentrex(), player.getCentrey());
		}
	}

	private void drawPlayersPaths(Graphics2D graphic) {
		for (int x = 0; x < pathsLength; x++){
			for (Player player : getPlayers()) {
				// TODO should be inside Player
				graphic.setColor(player.getColor());
				graphic.fillRect(player.getPathx().get(x), player.getPathy().get(x), 10, 10);
			}
		}
	}
	
	
}
