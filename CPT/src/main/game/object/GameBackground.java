package main.game.object;

import java.awt.Graphics2D;
import java.awt.Image;

// janky fix for my bad planning
// ideally all backgrounds would be handled by the game frame
public class GameBackground extends GameObject {

	private final int w, h;
	
	public GameBackground(int w, int h, Image image) {
		super(0, 0, image);
		this.w = w;
		this.h = h;
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, 0, 0, w, h, null);
	}
}
