package modfest.valar.common.rand;

public interface IRandom<T extends IRandom<T>>
{
	public int nextInt(int bound);
	
	public double nextDouble();
	
	default public boolean nextBoolean()
	{
		return this.nextInt(2) == 0;
	}
	
	public T create(long seed, double[] settings) throws ArrayIndexOutOfBoundsException;
}
