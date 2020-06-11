package main.game.object;
import java.awt.Graphics2D;

import main.assets.AssetFactory;

// custom font draw text 
public class GameString extends GameObject {

	public static final int charWidth = 24, charHeight = 48;
	
	private String text = "";

	public GameString(int x, int y) {
		super(x, y, null);
	}

	public GameString(int x, int y, String text) {
		this(x, y);
		this.text = text;
	}
	
	// use ascii as offset in image source character set
	@Override
	public void draw(Graphics2D g2d) {
		if (text == null)
			return;
		for (int i = 0, lb = 0, cleared = 0; i < text.length(); ++i) {
			if (text.charAt(i) == '\n') {
				++lb;
				cleared = i + 1;
			}
			else {
				g2d.drawImage(AssetFactory.GAME_ASSET_CHARACTER_SET.getImage(),
					getX() + (i - cleared) * charWidth, // dx1
					getY() + lb * charHeight, // dy1
					
					getX() + (i + 1 - cleared) * charWidth, // dx2
					getY() + (lb + 1) * charHeight, // dy2
					
					text.charAt(i) * charWidth, // sx1
					0 * charHeight, // sy1
					
					(text.charAt(i) + 1) * charWidth, // sx2
					1 * charHeight, // sy2
					
					null);
			}
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
