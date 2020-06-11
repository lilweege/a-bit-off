package main.game.object;

import java.awt.Graphics2D;

import main.assets.AssetFactory;
import main.game.level.GameLevel;
import main.game.level.GameLevelAdvanced;
import main.game.level.GameLevelIntermediate;
import main.game.level.GameLevelSimple;

//store and retrieve hint information for levels
public class GameHint extends GameObject {
	
	public static final int HINT_UNDEFINED = -1;
	
	public static final int HINT_SIMPLE = 0;

	public static final int HINT_NOT = 1;

	public static final int HINT_AND = 2;

	public static final int HINT_OR = 3;

	public static final int HINT_XOR = 4;

	public static final int HINT_LEFT = 5;

	public static final int HINT_RIGHT = 6;

	public static final int HINT_PLUS = 7;

	public static final int HINT_MINUS = 8;
	
	private int hint = HINT_UNDEFINED;
	
	private GameString info;
	
	private boolean showing = false;
	
	private static final String TEXT_ERROR = "SOMETHING WENT WRONG";
	
	private static final String TEXT_PROMPT = "           HINT\n\n";
	
	private static final String TEXT_INTRO = 
			TEXT_PROMPT +
			" Press H to toggle hints!";
	
	private static final String TEXT_SIMPLE =
			TEXT_PROMPT +
			"From right to left, each\n" + 
			"bit represents 2 to the\n" + 
			"power of its position.";

	private static final String TEXT_NOT = 
			TEXT_PROMPT + 
			"       Bitwise NOT\n" + 
			"One's complement is a\n" + 
			"binary operator that will\n" + 
			"essentially flip the sign\n" + 
			"and subtract one.";

	private static final String TEXT_AND = 
			TEXT_PROMPT + 
			"       Bitwise AND\n" + 
			"For each position, both\n" +
			"of the bits in the column\n" + 
			"must be set for the answer\n" + 
			"to have that bit set.";

	private static final String TEXT_OR = 
			TEXT_PROMPT + 
			"        Bitwise OR\n" + 
			"For each position, either\n" +
			"of the bits in the column\n" + 
			"must be set for the answer\n" + 
			"to have that bit set.";

	private static final String TEXT_XOR = 
			TEXT_PROMPT + 
			"       Bitwise XOR\n" + 
			"\"Exclusive OR\" is similar\n" + 
			"to the 'or' operator. But\n" + 
			"the bits cannot be the\n" +
			"same, (hence \"exclusive\").";

	private static final String TEXT_LEFT = 
			TEXT_PROMPT + 
			"      Bitshift Left\n" + 
			"\"Shift\" every bit in the\n" + 
			"first number left by the\n" + 
			"value of the second number\n" + 
			"(x times 2 to power of y).";

	private static final String TEXT_RIGHT = 
			TEXT_PROMPT + 
			"      Bitshift Right\n" + 
			"\"Shift\" every bit in the\n" + 
			"first number right by the\n" + 
			"value of the second number\n" + 
			"(x over 2 to power of y).";

	private static final String TEXT_PLUS = 
			TEXT_PROMPT + 
			"         Addition\n" + 
			"The sum of the two bytes\n" + 
			"should be equal to the\n" + 
			"target. This is the same\n" + 
			"as regular addition.";

	private static final String TEXT_MINUS = 
			TEXT_PROMPT + 
			"       Subtraction\n" + 
			"The difference of the two\n" + 
			"bytes should equal the\n" + 
			"target. This is the same\n" + 
			"as regular subtraction.";
	
	
	
	public GameHint() {
		super(
			AssetFactory.GAME_ASSET_HINT_FRAME.getImage().getWidth(null) / 2,
			AssetFactory.GAME_ASSET_HINT_FRAME.getImage().getHeight(null) / 2,
			AssetFactory.GAME_ASSET_HINT_FRAME.getImage());
		info = new GameString(100, 175);
		info.setText(TEXT_INTRO);
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		if (showing) {
			super.draw(g2d);
			info.draw(g2d);
		}
	}
	
	public void setHint(GameLevel level) {
		if (level instanceof GameLevelSimple) {
			hint = HINT_SIMPLE;
			info.setText(TEXT_SIMPLE);
		}
		else if (level instanceof GameLevelIntermediate || 
				 level instanceof GameLevelAdvanced) {
			
			int type; 
			if (level instanceof GameLevelIntermediate)
				type = ((GameLevelIntermediate) level).operator.getType();
			else
				type = ((GameLevelAdvanced) level).operator.getType();
			
			switch (type) {
				case GameOperator.OPERATOR_NOT:
					hint = HINT_NOT;
					info.setText(TEXT_NOT);
					break;
				case GameOperator.OPERATOR_AND:
					hint = HINT_AND;
					info.setText(TEXT_AND);
					break;
				case GameOperator.OPERATOR_OR:
					hint = HINT_OR;
					info.setText(TEXT_OR);
					break;
				case GameOperator.OPERATOR_XOR:
					hint = HINT_XOR;
					info.setText(TEXT_XOR);
					break;
				case GameOperator.OPERATOR_LEFT:
					hint = HINT_LEFT;
					info.setText(TEXT_LEFT);
					break;
				case GameOperator.OPERATOR_RIGHT:
					hint = HINT_RIGHT;
					info.setText(TEXT_RIGHT);
					break;
				case GameOperator.OPERATOR_PLUS:
					hint = HINT_PLUS;
					info.setText(TEXT_PLUS);
					break;
				case GameOperator.OPERATOR_MINUS:
					hint = HINT_MINUS;
					info.setText(TEXT_MINUS);
					break;
				default:
					hint = HINT_UNDEFINED;
					info.setText(TEXT_ERROR);
					break;
			}
		}
		else {
			info.setText(TEXT_ERROR);
			hint = HINT_UNDEFINED;
		}
	}
	
	public int getHint() {
		return hint;
	}

	public boolean isShowing() {
		return showing;
	}

	public void setShowing(boolean showing) {
		this.showing = showing;
	}
}