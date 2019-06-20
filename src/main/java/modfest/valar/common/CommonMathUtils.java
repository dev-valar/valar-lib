package modfest.valar.common;

public final class CommonMathUtils
{
	private CommonMathUtils() {}
	
	public static int floor(double arg0)
	{
		int i = (int) arg0;
		return i < arg0 ? i : i - 1;
	}
}
