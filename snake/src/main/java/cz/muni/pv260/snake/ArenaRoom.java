package cz.muni.pv260.snake;

import cz.muni.pv260.tron.engine.Room;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class ArenaRoom extends Room {

    private Point food;

    public ArenaRoom(Dimension dimension) {
        super(dimension);
        constructPlayers(dimension);
        generateFood();
    }

    public Point getFood() {
        return food;
    }

    public void setFood(Point food) {
        this.food = food;
    }

    @Override
    public void draw(Graphics graphics) {

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, (int) getDimension().getWidth(), (int) getDimension().getHeight());

        if (food == null) {
            generateFood();
        }
        graphics.setColor(Color.RED);
        graphics.fillRect(food.x, food.y, 10, 10);

        super.draw(graphics);
    }

    private void constructPlayers(Dimension dimension) {
        int padding = 100;

        Player player1 = new Player(
                new Point(padding, padding), Player.Direction.RIGHT, Color.GREEN,
                KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT
        );

        addItem(player1);
    }

    private void generateFood() {
        Random random = new Random();
        int x = random.nextInt((int) getDimension().getWidth());
        int y = random.nextInt((int) getDimension().getHeight());
        food = new Point(x, y);
    }
}
