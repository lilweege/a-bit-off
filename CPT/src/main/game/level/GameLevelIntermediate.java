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

public class GameLevelIntermediate extends GameLevel {
	
	public GameByte firstNumber;
	
	public GameByte secondNumber;
	
	public GameOperator operator;

	public GameLevelIntermediate() {
		this(Integer.MIN_VALUE);
	}

	public GameLevelIntermediate(int target) {
		super(target);
	}
	
	@Override
	public void createObjects() {
		int[] types = {
			GameOperator.OPERATOR_OR,
			GameOperator.OPERATOR_XOR,
			GameOperator.OPERATOR_PLUS,
			GameOperator.OPERATOR_MINUS
		};
		int type = types[GamePhysics.randint(0, types.length)];
		operator = new GameOperator(85, 315, type);
		firstNumber = new GameByte(110, 270, GameByte.maxBits);
		secondNumber = new GameByte(110, 350, GameByte.maxBits);
		if (getTarget() == GameLevel.DEFAULT_TARGET)
			setTarget(generateTarget(type));
	}
	
	@Override
	public void addObjects() {
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
				return ~secondNumber.getValue();
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