package main.game.object;
import java.awt.Polygon;

import main.assets.AssetFactory;

public class GameBit extends GameObject {

	// hardcoded radius cause lazy
	public static final int r = 35;
	
	private boolean set = false;
	
	private boolean enterToggle = false;
	
	public void flip() {
		setBit(!set);
	}
	
	public void setBit(boolean set) {
		this.set = set;
		if (set) {
			setImage(AssetFactory.GAME_ASSET_BIT_SET.getImage());
			AssetFactory.playSound(AssetFactory.SOUND_SFX_BLIP_TWO, 0.5);
		}
		else {
			setImage(AssetFactory.GAME_ASSET_BIT_UNSET.getImage());
			AssetFactory.playSound(AssetFactory.SOUND_SFX_BLIP_ONE, 0.5);
		}
	}
	
	public boolean isSet() {
		return set;
	}
	
	public GameBit(int x, int y) {
		super(x, y, AssetFactory.GAME_ASSET_BIT_UNSET.getImage());
		// construct circle hitbox
		Polygon circle = new Polygon();
		for (int i = 0, n = 32; i < n; ++i) {
			double a = Math.PI * (i / (n / 2.));
			int px = getX() + (int)(r * Math.cos(a));
			int py = getY() + (int)(r * Math.sin(a));
			circle.addPoint(px, py);
		}
		setHitbox(circle);
	}
	
	public boolean isToggled() {
		return enterToggle;
	}
	
	public void setToggle(boolean enterToggle) {
		this.enterToggle = enterToggle;
	}
}
