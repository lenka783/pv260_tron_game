package cz.muni.pv260.tron.game.presentation;

import cz.muni.pv260.tron.engine.presentation.ScreenPresenter;
import cz.muni.pv260.tron.game.model.ArenaRoom;
import cz.muni.pv260.tron.game.model.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ArenaPresenter extends ScreenPresenter<ArenaRoom> {
	
	@Override
	public ArenaRoom initRoom() {
		return new ArenaRoom(this.getDimension());
	}
	
	@Override
	public Window initWindow() {
		Window window = super.initWindow();
		
		window.setFont(new Font("Arial", Font.PLAIN,20));
		window.setBackground(Color.WHITE);
		window.setForeground(Color.RED);
		window.setCursor(
				window.getToolkit().createCustomCursor(
						new BufferedImage(3, 3,BufferedImage.TYPE_INT_ARGB),
						new Point(0, 0),
						"null"
				)
		);
		
		return window;
	}
	
	@Override
	public void draw(ArenaRoom room, Graphics graphics) {
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, (int) room.getDimension().getWidth(), (int) room.getDimension().getHeight());
		
		for (Player player : room.getPlayers()) {
			
			for (Point point : player.getPath()) {
				graphics.setColor(player.getColor());
				graphics.fillRect(point.x, point.y, 10, 10);
			}
		}
		
	}
	
}
