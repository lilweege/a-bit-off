package main.window;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

// rendering hints and absolute position
public class RenderPanel extends JPanel {
	
	private static final long serialVersionUID = -758196229228393121L;
	
	private final static RenderingHints textRenderHints = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	
	private final static RenderingHints imageRenderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
	private final static RenderingHints colorRenderHints = new RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	
	private final static RenderingHints interpolationRenderHints = new RenderingHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	
	private final static RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	
	Insets absoluteOffset = getInsets();
	
	protected RenderPanel() {
		super(true /*double buffer*/);
		setLayout(null);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		// images are drawn with better quality
		g2d.setRenderingHints(textRenderHints);
		g2d.setRenderingHints(imageRenderHints);
		g2d.setRenderingHints(colorRenderHints);
		g2d.setRenderingHints(interpolationRenderHints);
		g2d.setRenderingHints(renderHints);
	}
	
	// jframe.pack() calls this
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}
	
	// absolute position
	public Component add(Component c, int x, int y) {
		Dimension sz = c.getPreferredSize();
		add(c);
		c.setBounds(absoluteOffset.left + x, absoluteOffset.top + y, sz.width, sz.height);
		return c;
	}
	
	// janky workaround for minor bug
	public void fixButtonHoverGlitch() {
		for (Component c : getComponents()) {
			if (c instanceof JButton) {
				MouseListener[] l = c.getMouseListeners();
				if (l.length == 2 /*basic button and custom*/) {
					l[1].mouseExited(null); // invoke custom lisetner's exit to remove hover glitch
				}
			}
			else if (c instanceof RenderPanel) {
				((RenderPanel) c).fixButtonHoverGlitch();
			}
		}		
	}
}