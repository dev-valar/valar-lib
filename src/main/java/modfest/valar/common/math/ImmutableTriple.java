package modfest.valar.common.math;

public class ImmutableTriple<A, B, C> implements Triple<A, B, C>
{
	protected final A a;
	protected final B b;
	protected final C c;
	
	@Override
	public final A getA()
	{
		return this.a;
	}
	
	@Override
	public final B getB()
	{
		return this.b;
	}
	
	@Override
	public final C getC()
	{
		return this.c;
	}
	
	public ImmutableTriple(A a, B b, C c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
	}
}
