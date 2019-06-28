package modfest.valar.common.provider;

import java.util.Random;

public class RandomProvider
{
	private final long seed;
	private long x, y = 0;
	
	public RandomProvider(long seed)
	{
		this.seed = seed;
	}
	
	public RandomProvider position(long x, long y)
	{
		this.x = x;
		this.y = y;
		
		return this;
	}
	
	public Random createRandom(long zoom)
	{
		return new Random(seed + 535651152L * (x / zoom) + 813413134L * (y / zoom));
	}

}
