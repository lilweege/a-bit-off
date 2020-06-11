package main.window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// adapt listeners to be implemented
public interface InputAdapter extends MouseListener, MouseMotionListener, KeyListener {
	
	// default unwanted methods to do nothing
	default void mouseClicked(MouseEvent e) {}
	
	default void mouseEntered(MouseEvent e) {}
	
	default void mouseExited(MouseEvent e) {}
	
	default void keyTyped(KeyEvent e) {}
}