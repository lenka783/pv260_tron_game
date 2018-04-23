package cz.muni.pv260.tron.game.model;

import cz.muni.pv260.tron.engine.model.Room;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

public class ArenaRoom extends Room {
	
	public ArenaRoom(Dimension dimension) {
		super(dimension);
		constructPlayers(dimension);
	}
	
	private void constructPlayers(Dimension dimension) {
		int padding = 100;
		
		Player player1 = new Player(
				new Point(padding, padding), Player.Direction.RIGHT, Color.GREEN,
				KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, true
		);
		
		Player player2 = new Player(
				new Point((int)dimension.getWidth() - padding, (int)dimension.getHeight() - padding),
				Player.Direction.LEFT, Color.RED,
				KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, false
		);
		
		Player player3 = new Player(
				new Point((int)dimension.getWidth() - padding, padding), Player.Direction.LEFT, Color.YELLOW,
				KeyEvent.VK_G, KeyEvent.VK_B, KeyEvent.VK_V, KeyEvent.VK_N, false
		);
		
		Player player4 = new Player(
				new Point(padding, (int)dimension.getHeight() - padding), Player.Direction.RIGHT, Color.CYAN,
				KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L, false
		);
		
		addItem(player1);
		addItem(player2);
		//addItem(player3);
		//addItem(player4);
	}
	
	public List<Player> getPlayers() {

		return getItems().stream()
				.filter(item -> item instanceof Player)
				.map(item -> (Player) item)
				.collect(Collectors.toList());
	}
}
