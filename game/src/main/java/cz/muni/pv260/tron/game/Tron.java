package cz.muni.pv260.tron.game;

import cz.muni.pv260.tron.engine.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

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
		
		initPlayers();
	}
	
	@Override
	public void draw(Graphics graphics) {
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, sm.getWidth(), sm.getHeight());
		
		super.draw(graphics);
	}
	
	private void initPlayers() {
		int padding = 100;
		
		Player player1 = new Player(
				new Point(padding, padding), Player.Direction.RIGHT, Color.GREEN,
				KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT
		);
		
		Player player2 = new Player(
				new Point(sm.getWidth()-padding, sm.getHeight()-padding), Player.Direction.LEFT, Color.RED,
				KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D
		);

		Player player3 = new Player(
				new Point(sm.getWidth()-padding, padding), Player.Direction.LEFT, Color.YELLOW,
				KeyEvent.VK_G, KeyEvent.VK_B, KeyEvent.VK_V, KeyEvent.VK_N
		);
		
		Player player4 = new Player(
				new Point(padding, sm.getHeight()-padding), Player.Direction.RIGHT, Color.CYAN,
				KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L
		);
		
		addItem(player1);
		addItem(player2);
		addItem(player3);
		addItem(player4);
	}

}
