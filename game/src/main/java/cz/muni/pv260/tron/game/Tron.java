package cz.muni.pv260.tron.game;

import cz.muni.pv260.tron.engine.Game;
import cz.muni.pv260.tron.engine.Room;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tron extends Game {

	public static void main(String[] args) {
		try {
			new Tron().run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected Room initRoom(Dimension dimension) {
		return new ArenaRoom(dimension);
	}
	
	@Override
	public void init() {
		super.init();
		Window window = sm.getWindow();
		window.setFont(new Font("Arial", Font.PLAIN,20));
		window.setBackground(Color.WHITE);
		window.setForeground(Color.RED);
		window.setCursor(
				window.getToolkit().createCustomCursor(
						new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
						new Point(0, 0),
						"null"
				)
		);
	}
}
