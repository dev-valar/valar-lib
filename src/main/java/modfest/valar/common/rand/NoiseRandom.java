package modfest.valar.common.rand;

import modfest.valar.common.noise.NoiseGenerator;

public interface NoiseRandom<T extends NoiseGenerator, R extends NoiseRandom<T, R>> extends IRandom<R>
{
	public long getSeed();
	
	public T getNoiseGenerator();
}
