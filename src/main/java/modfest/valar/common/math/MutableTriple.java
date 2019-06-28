package modfest.valar.common.math;

public class MutableTriple<A, B, C> implements Triple<A, B, C>, Cloneable
{
	protected A a;
	protected B b;
	protected C c;
	
	@Override
	public A getA()
	{
		return this.a;
	}
	
	@Override
	public B getB()
	{
		return this.b;
	}
	
	@Override
	public C getC()
	{
		return this.c;
	}
	
	@Override
	public MutableTriple<A, B, C> clone()
	{
		return new MutableTriple<>(a, b, c);
	}
	
	public MutableTriple<A, B, C> setA(A a)
	{
		this.a = a;
		return this;
	}
	
	public MutableTriple<A, B, C> setB(B b)
	{
		this.b = b;
		return this;
	}
	
	public MutableTriple<A, B, C> setC(C c)
	{
		this.c = c;
		return this;
	}
	
	public MutableTriple<A, B, C> mutate(A a, B b, C c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
		
		return this;
	}
	
	public MutableTriple(A a, B b, C c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
	}
}
