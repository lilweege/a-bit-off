package main.game;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import main.assets.AssetFactory;
import main.game.level.GameLevel;
import main.game.level.GameLevelAdvanced;
import main.game.level.GameLevelIntermediate;
import main.game.level.GameLevelSimple;
import main.game.object.GameBackground;
import main.game.object.GameBit;
import main.game.object.GameHint;
import main.game.object.GameObject;
import main.game.object.GameString;
import main.window.UserInput;

// gameplay functionality and world objects
public class GamePhysics implements Iterable<GameObject> {
	
	public static int clamp(int x, int l, int h) { return x > h ? h : x < l ? l : x; }
	
	public static int randint(int min, int max) { return (int)((Math.random() * (max - min)) + min); };
	
	public GameHint hint;
	
	private GameString infoText, resultText, timerText;
	
	public GameString finalScore;
	
	private GameBackground background;
	
	private int time = 0;
	
	private int levelNo = 0;
	
	public final int width, height, xmin, ymin, xmax, ymax;
	
	public UserInput input;
	
	public GameLevel level;
	
	private boolean finished = false;
	
	public GamePhysics() {
		width = 800;
		height = 600;
		xmin = 20;
		ymin = 20;
		xmax = 780;
		ymax = 580;
		createObjects();
	}
	
	private void createObjects() {
		input = new UserInput();
		level = new GameLevelSimple(255);
		hint = new GameHint();
		background = new GameBackground(width, height, AssetFactory.IMAGE_BACKGROUND_PLAYING.getImage());
		infoText = new GameString(xmin + GameString.charWidth / 2, ymin);
		timerText = new GameString((int) (xmax - 4.5 * GameString.charWidth), ymin);
		resultText = new GameString(150, 350);
		finalScore = new GameString(480, 357);
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	private String timeFormat(int time) {
		int mins = time / 60,
			secs = time % 60;
		return mins + ":" + (secs >= 10 ? "" : "0") + secs;
	}
	
	private boolean firstHintPress = true;
	
	public void handleKeyEvent(KeyEvent ke) {
		boolean pressed = ke.getID() == KeyEvent.KEY_PRESSED;
		int key = ke.getKeyCode();
		
		// handle 
		if (key == KeyEvent.VK_H) {
			if (firstHintPress)
				toggleHint();
			firstHintPress = !pressed;
		}
		
		// otherwise
		else {
			// this is redundant
			// input is not being used anywhere else
			input.pressKey(key, pressed);
		}
	}
	
	// called every frame
	public void update() {
		if (!finished) {
			if (!hint.isShowing())
				flipBits();
			checkWin();
		}
	}
	
	// collide with mouse
	private void flipBits() {
		for (GameBit bit : level) {
			boolean collide = bit.collidesWith(input.getMouseX(), input.getMouseY()) && input.isMousePressed();
			if (collide && bit.isToggled())
				bit.flip();
			bit.setToggle(!collide);
		}
	}
	
	// compare result to target
	// calls next level
	public boolean checkWin() {
		int result = level.getResult(),
			target = level.getTarget();
		if (result == target)
			nextLevel();
		updateText(result, target);
		return level.getResult() == level.getTarget();
	}
	
	// jank
	private void updateText(int result, int target) {
		infoText.setText("level " + levelNo + "\ntarget: " + target);
		timerText.setText(timeFormat(time));
		timerText.setX((int) (xmax - (timerText.getText().length() + 0.5) * GameString.charWidth));
		resultText.setText("_________________________\n= " + Integer.toString(result));
	}
	
	public void nextLevel() {
		AssetFactory.playSound(AssetFactory.SOUND_SFX_BLIP_THREE, 0.5);
		++levelNo;
		if (levelNo < 2)
			level = new GameLevelSimple();
		else if (levelNo < 4)
			level = new GameLevelIntermediate();
		else if (levelNo < 10)
			level = new GameLevelAdvanced();
		else
			endGame();
		hint.setHint(level);
	}
	
	public void endGame() {
		int mins = time / 60,
			secs = time % 60;
		finalScore.setText(mins + ":" + (secs >= 10 ? "" : "0") + secs);
		finished = true;
		background.setImage(AssetFactory.IMAGE_BACKGROUND_FINISHED.getImage());
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public void toggleHint() {
		hint.setShowing(!hint.isShowing());
		if (hint.getHint() == GameHint.HINT_UNDEFINED)
			hint.setHint(level);
	}
	
	@Override
	public Iterator<GameObject> iterator() {
		ArrayList<GameObject> ret = new ArrayList<>();
		ret.add(background);
		if (isFinished()) {
			ret.add(finalScore);
		}
		else {
			ret.addAll(level.objects);
			ret.add(infoText);
			ret.add(timerText);
			ret.add(resultText);
			ret.add(hint);
		}
		return ret.iterator();
	}
}