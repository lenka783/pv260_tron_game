package cz.muni.pv260.tron.game;

import cz.muni.pv260.tron.engine.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.util.List;

public class Tron extends Game {

	public static void main(String[] args) {
		try {
			new Tron().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	@Override
	public void update(long timePassed) {
		
		super.update(timePassed);
		
		checkCollision();
		
	}
	
	@Override
	public void draw(Graphics graphics) {
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, sm.getWidth(), sm.getHeight());
		
		super.draw(graphics);
	}
	
	private void initPlayers(){
		Player player1 = new Player(
				40, 40, 2, Color.GREEN,
				KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT
		);
		Player player2 = new Player(
				600, 140, 0, Color.RED,
				KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D
		);

		Player player3 = new Player(
				500, 240, 2, Color.YELLOW,
				KeyEvent.VK_U, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_H
		);
		
		addItem(player1);
		addItem(player2);
		addItem(player3);
	}

	private void checkCollision() {
		// TODO should iterate over ALL touples
		for (int i = 1; i < getItems().size(); i++) {
			// TODO should be inside Player
			for (int x = 0; x < getPlayers().get(i-1).getPathx().size()-1; x++){
				if (getPlayers().get(i-1).isInCollision(getPlayers().get(i), x)){
					System.exit(0);
				}
			}
		}
	}

}
