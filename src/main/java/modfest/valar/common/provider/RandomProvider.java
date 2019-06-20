package modfest.valar.common.provider;

import java.util.function.DoubleFunction;

import modfest.valar.common.rand.IRandom;

public interface RandomProvider<T extends IRandom<T>> extends DoubleFunction<T>
{
	default public T apply(double d)
	{
		return this.createRandom(d);
	}
	
	public T createRandom(double zoom);
}
