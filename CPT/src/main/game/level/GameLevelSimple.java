package main.game.level;

import java.util.Arrays;
import java.util.Iterator;

import main.game.GamePhysics;
import main.game.object.GameBit;
import main.game.object.GameByte;

public class GameLevelSimple extends GameLevel {
	
	public GameByte number;
	
	public GameLevelSimple() {
		this(GameLevel.DEFAULT_TARGET);
	}
	
	public GameLevelSimple(int target) {
		super(target);
	}
	
	@Override
	public void createObjects() {
		number = new GameByte(110, 350, 8);
		if (getTarget() == GameLevel.DEFAULT_TARGET)
			setTarget(generateTarget());
	}
	
	private int generateTarget() {
		return GamePhysics.randint(0, 256);
	}
	
	@Override
	public void addObjects() {
		for (GameBit bit : number.bits)
			objects.add(bit);
	}
	
	@Override
	public int getResult() {
		return number.getValue();
	}

	@Override
	public Iterator<GameBit> iterator() {
		return Arrays.asList(number.bits).iterator();
	}
}