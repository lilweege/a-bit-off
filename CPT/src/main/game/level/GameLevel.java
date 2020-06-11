package main.game.level;
import java.util.ArrayList;
import java.util.HashSet;

import main.game.GamePhysics;
import main.game.object.GameBit;
import main.game.object.GameObject;
import main.game.object.GameOperator;

// generic level
public abstract class GameLevel implements Iterable<GameBit> {
	
	public static final int DEFAULT_TARGET = Integer.MIN_VALUE;
	
	private int target = GameLevel.DEFAULT_TARGET;
	
	public final ArrayList<GameObject> objects = new ArrayList<>();
	
	protected abstract void createObjects();
	
	protected abstract void addObjects();
	
	GameLevel(int target) {
		this.target = target;
		objects.clear();
		createObjects();
		addObjects();
	}
	
	public abstract int getResult();
	
	public int getTarget() {
		return target;
	}
	
	public void setTarget(int target) {
		this.target = target;
	}
	
	public static int generateTarget(int type) {
		int[] range;
		switch (type) {
			case GameOperator.OPERATOR_LEFT:
				HashSet<Integer> poss = new HashSet<>();
				for (int x = 0; x < 256; x++)
					for (int y = 0; y < 8; y++)
						poss.add(x << y);
				return new ArrayList<Integer>(poss).get(GamePhysics.randint(0, poss.size()));
			case GameOperator.OPERATOR_RIGHT:
			case GameOperator.OPERATOR_AND:
			case GameOperator.OPERATOR_OR:
			case GameOperator.OPERATOR_XOR:
				range = new int[] { 1, 256 };
				break;
			case GameOperator.OPERATOR_PLUS:
				range = new int[] { 256, 511 };
				break;
			case GameOperator.OPERATOR_NOT:
			case GameOperator.OPERATOR_MINUS:
				range = new int[] { -256, -1 };
				break;
			default:
				range = new int[] { 255, 256 };
				break;
		}
		return GamePhysics.randint(range[0], range[1]);
	}
}