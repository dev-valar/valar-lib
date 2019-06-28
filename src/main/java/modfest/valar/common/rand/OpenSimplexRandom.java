package modfest.valar.common.rand;

import modfest.valar.common.ValarMathUtils;
import modfest.valar.common.noise.OpenSimplexNoise;

public class OpenSimplexRandom implements NoiseRandom<OpenSimplexNoise, OpenSimplexRandom>
{
	private final OpenSimplexNoise parent;
	
	private long localSeed;
	private final long initSeed, baseSeed;
	private static final double MAX_PROCESSED_DOUBLE = (Long.MAX_VALUE >> 24);
	
	private OpenSimplexRandom(long seed, double zoom, double startX, double startY)
	{
		parent = new OpenSimplexNoise(seed);
		int x = ValarMathUtils.floor(startX / zoom);
		int y = ValarMathUtils.floor(startY / zoom);
		
		baseSeed = (long) (266587864571D * (0.2D + parent.eval(x, y)));
		
		long tempSeed = baseSeed;
		
		tempSeed *= tempSeed * 4746246356235635467L + 143654758424313431L;
		tempSeed += baseSeed;
		tempSeed *= tempSeed * 4746246356235635467L + 143654758424313431L;
		tempSeed += baseSeed;
		tempSeed *= tempSeed * 4746246356235635467L + 143654758424313431L;
		
		initSeed = tempSeed;
		
		init();
	}
	
	private void init()
	{
		localSeed = initSeed;
		
		localSeed *= localSeed * 4746246356235635467L + 143654758424313431L;
		localSeed += baseSeed;
		localSeed *= localSeed * 4746246356235635467L + 143654758424313431L;
		localSeed += initSeed;
		localSeed *= localSeed * 4746246356235635467L + 143654758424313431L;
		localSeed += baseSeed;
		localSeed *= localSeed * 4746246356235635467L + 143654758424313431L;
	}
	
	private void next()
	{	
		localSeed += initSeed;
		localSeed *= localSeed * 4746246356235635467L + 143654758424313431L;
	}
	
	@Override
	public int nextInt(int bound)
	{
		int returns = (int) ((localSeed >> 24) % ((long) bound));
		if (returns < 0)
			returns += bound;
		
		next();
		
		return returns;
	}

	@Override
	public double nextDouble()
	{
		double returns = ((double) (localSeed >> 24)) / MAX_PROCESSED_DOUBLE;
		if (returns < 0)
			returns = -returns;
		
		next();
		
		return returns;
	}

	@Override
	public long getSeed()
	{
		return localSeed;
	}

	@Override
	public OpenSimplexNoise getNoiseGenerator()
	{
		return parent;
	}
	
	@Override
	public OpenSimplexRandom create(long seed, double[] settings)
	{
		return new OpenSimplexRandom(seed, settings[0], settings[1], settings[2]);
	}
	
	public static OpenSimplexRandom from(long seed, double[] settings) throws ArrayIndexOutOfBoundsException
	{
		return new OpenSimplexRandom(seed, settings[0], settings[1], settings[2]);
	}
}
