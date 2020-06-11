package main.window;
import java.util.HashMap;

// generic mouse and key state container and interface
public class UserInput {
	
	private HashMap<Integer, Boolean> keysPressed = new HashMap<>();
	
	private boolean mousePressed = false;
	
	private int mouseX = 0, mouseY = 0;
	
	public boolean isKeyDown(int k) {
		return keysPressed.getOrDefault(k, false);
	}

	public void pressKey(int key, boolean pressed) {
		keysPressed.put(key, pressed);
	}
	
	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
}