package main.game;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

import main.game.object.GameObject;
import main.window.RenderPanel;

// draw game world
public class GameRenderer extends RenderPanel {
	
	private static final long serialVersionUID = -1432140795663663642L;
	
	private GamePhysics world;
	
	private JButton manualExit = null;
	
	public void setManualExit(JButton btn, int x, int y) {
		if (manualExit != null)
			remove(manualExit);
		manualExit = btn;
		manualExit.setVisible(false);
		add(manualExit, x, y);
	}
	
	public GameRenderer(GamePhysics world) {
		super();
		this.world = world;
		setSize(world.width, world.height);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		if (manualExit != null && world.isFinished())
			manualExit.setVisible(true);
		
		for (GameObject object : world)
			if (object != null)
				object.draw(g2d);
	}
}