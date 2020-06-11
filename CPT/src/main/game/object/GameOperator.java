package main.game.object;
import java.awt.Image;

import main.assets.AssetFactory;

// store and retrieve operator information for levels
public class GameOperator extends GameObject {
	
	public static final int OPERATOR_NOT = 0;

	public static final int OPERATOR_AND = 1;

	public static final int OPERATOR_OR = 2;

	public static final int OPERATOR_XOR = 3;

	public static final int OPERATOR_LEFT = 4;

	public static final int OPERATOR_RIGHT = 5;

	public static final int OPERATOR_PLUS = 6;

	public static final int OPERATOR_MINUS = 7;
	
	private final int type;
	
	public GameOperator(int x, int y, int type) {
		super(x, y, getImage(type));
		this.type = type;
	}

	public static Image getImage(int type) {
		switch (type) {
			case GameOperator.OPERATOR_NOT:
				return AssetFactory.GAME_ASSET_OPERATOR_NOT.getImage();
			case GameOperator.OPERATOR_AND:
				return AssetFactory.GAME_ASSET_OPERATOR_AND.getImage();
			case GameOperator.OPERATOR_OR:
				return AssetFactory.GAME_ASSET_OPERATOR_OR.getImage();
			case GameOperator.OPERATOR_XOR:
				return AssetFactory.GAME_ASSET_OPERATOR_XOR.getImage();
			case GameOperator.OPERATOR_LEFT:
				return AssetFactory.GAME_ASSET_OPERATOR_LEFT.getImage();
			case GameOperator.OPERATOR_RIGHT:
				return AssetFactory.GAME_ASSET_OPERATOR_RIGHT.getImage();
			case GameOperator.OPERATOR_PLUS:
				return AssetFactory.GAME_ASSET_OPERATOR_PLUS.getImage();
			case GameOperator.OPERATOR_MINUS:
				return AssetFactory.GAME_ASSET_OPERATOR_MINUS.getImage();
			default:
				return null;
		}
	}
	
	public int getType() {
		return type;
	}
}
