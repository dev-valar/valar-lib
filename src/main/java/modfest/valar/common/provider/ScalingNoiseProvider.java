package modfest.valar.common.provider;

import modfest.valar.common.rand.OpenSimplexRandom;

public class ScalingNoiseProvider implements RandomProvider<OpenSimplexRandom>
{
	private final long seed;
	private double x, y = 0D;
	
	public ScalingNoiseProvider(long seed)
	{
		this.seed = seed;
	}
	
	public ScalingNoiseProvider position(double x, double y)
	{
		this.x = x;
		this.y = y;
		
		return this;
	}
	
	@Override
	public OpenSimplexRandom createRandom(double zoom)
	{
		return OpenSimplexRandom.from(seed, new double[] {zoom, x, y});
	}

}
