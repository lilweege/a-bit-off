package main.game.object;

// wrapper for GameBits
public class GameByte {
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public static final int maxBits = 8;
	
	private final int numBits;

	public GameBit[] bits;
	
	private final int x, y;
	
	public GameByte(int x, int y, int numBits) {
		this.x = x;
		this.y = y;
		this.numBits = Math.min(numBits, maxBits);
		bits = new GameBit[numBits];
		for (int i = 0; i < numBits; ++i)
			bits[i] = new GameBit(x + (GameBit.r * 2 + 5) * (maxBits - i), y);
	}
	
	public int getValue() {
		int res = 0;
		for (int i = 0; i < numBits; ++i)
			if (bits[i].isSet())
				res += 1 << i;
		return res;
	}
}
