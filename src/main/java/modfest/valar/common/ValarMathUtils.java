package modfest.valar.common;

public final class ValarMathUtils
{
	private ValarMathUtils() {}
	
	public static int floor(double arg0)
	{
		int i = (int) arg0;
		return i < arg0 ? i : i - 1;
	}
	
	public static double sigmoid(double in, double scale, double offset, double xScale, double xOffset)
	{
		return scale * (1D / (
				1D + Math.exp(
						-((in + xOffset) * xScale)
				)
			)
		) + offset;
	}
}
