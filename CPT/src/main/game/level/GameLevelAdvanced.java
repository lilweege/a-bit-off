package main.game.level;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.game.GamePhysics;
import main.game.object.GameBit;
import main.game.object.GameByte;
import main.game.object.GameOperator;

public class GameLevelAdvanced extends GameLevel {

	public GameByte firstNumber;
	
	public GameByte secondNumber;
	
	public GameOperator operator;
	
	public GameLevelAdvanced() {
		this(Integer.MIN_VALUE);
	}

	public GameLevelAdvanced(int target) {
		super(target);
	}
	
	@Override
	public void createObjects() {
		int[] types = {
			GameOperator.OPERATOR_NOT,
			GameOperator.OPERATOR_AND,
			GameOperator.OPERATOR_OR,
			GameOperator.OPERATOR_XOR,
			GameOperator.OPERATOR_LEFT,
			GameOperator.OPERATOR_RIGHT,
			GameOperator.OPERATOR_PLUS,
			GameOperator.OPERATOR_MINUS
		};
		int type = types[GamePhysics.randint(0, types.length)];
		operator = new GameOperator(85, 315, type);
		firstNumber = new GameByte(110, type == GameOperator.OPERATOR_NOT ? 350 : 270, 8);
		secondNumber = new GameByte(110, 350, type == GameOperator.OPERATOR_NOT ? 0 : type == GameOperator.OPERATOR_RIGHT || type == GameOperator.OPERATOR_LEFT ? 3 : GameByte.maxBits);
		if (getTarget() == GameLevel.DEFAULT_TARGET)
			setTarget(generateTarget(type));
	}
	
	@Override
	protected void addObjects() {
		objects.add(operator);

		for (GameBit bit : firstNumber.bits)
			objects.add(bit);
		
		for (GameBit bit : secondNumber.bits)
			objects.add(bit);
	}

	@Override
	public int getResult() {
		switch (operator.getType()) {
			case GameOperator.OPERATOR_NOT:
				return ~firstNumber.getValue();
			case GameOperator.OPERATOR_AND:
				return firstNumber.getValue() & secondNumber.getValue();
			case GameOperator.OPERATOR_OR:
				return firstNumber.getValue() | secondNumber.getValue();
			case GameOperator.OPERATOR_XOR:
				return firstNumber.getValue() ^ secondNumber.getValue();
			case GameOperator.OPERATOR_LEFT:
				return firstNumber.getValue() << secondNumber.getValue();
			case GameOperator.OPERATOR_RIGHT:
				return firstNumber.getValue() >> secondNumber.getValue();
			case GameOperator.OPERATOR_PLUS:
				return firstNumber.getValue() + secondNumber.getValue();
			case GameOperator.OPERATOR_MINUS:
				return firstNumber.getValue() - secondNumber.getValue();
			default: return -1;
		}
	}

	@Override
	public Iterator<GameBit> iterator() {
		return Stream.of(Arrays.asList(firstNumber.bits), Arrays.asList(secondNumber.bits))
				.flatMap(Collection::stream)
				.collect(Collectors.toList())
				.iterator();
	}
}