package modfest.valar.common.noise;

public interface NoiseGenerator
{
	double eval(double x, double y);

	double eval(double x, double y, double z);

	long getSeed();
}
