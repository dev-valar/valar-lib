package modfest.valar.common.biome;

import modfest.valar.common.biome.BiomeFactory.BiomePopulator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public abstract class Biome extends net.minecraft.world.biome.Biome
{
	public final BiomeFactory factory;
	public final BiomePopulator populator;
	
	protected Biome(BiomeFactory biomeFactory)
	{
		super(biomeFactory.build());
		
		biomeFactory.setParent(this);
		factory = biomeFactory;
		populator = biomeFactory.createPopulator();
	}

	@Override
	@Environment(EnvType.CLIENT)
	public int getGrassColorAt(BlockPos pos)
	{
		return this.hasCustomColours() ? this.factory.getGrassColour() : super.getGrassColorAt(pos);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public int getFoliageColorAt(BlockPos pos)
	{
		return this.hasCustomColours() ? this.factory.getFoliageColour() : super.getFoliageColorAt(pos);
	}

	@Override
	public float getMaxSpawnLimit()
	{
		return this.factory.getSpawnChance();
	}

	public boolean hasCustomColours()
	{
		return this.factory.hasCustomColours();
	}
	
	public void setTopBlock(BlockState top)
	{
		this.factory.surfaceConfig.setTopMaterial(top);
	}
	public void setFillerBlock(BlockState under)
	{
		this.factory.surfaceConfig.setUnderMaterial(under);
	}
	public void setUnderwaterBlock(BlockState underwater)
	{
		this.factory.surfaceConfig.setUnderwaterMaterial(underwater);
	}
	
	//============================================================================//

	public static class TBOSurfaceConfig extends TernarySurfaceConfig implements Cloneable
	{
		private BlockState top;
		private BlockState under;
		private BlockState waterfloor;

		public TBOSurfaceConfig(BlockState top, BlockState under, BlockState underwater)
		{
			super(top, under, underwater);

			this.top = top;
			this.under = under;
			this.waterfloor = underwater;
		}

		@Override
		public BlockState getTopMaterial()
		{
			return this.top;
		}
		@Override
		public BlockState getUnderMaterial()
		{
			return this.under;
		}
		@Override
		public BlockState getUnderwaterMaterial()
		{
			return this.waterfloor;
		}

		public TBOSurfaceConfig setTopMaterial(BlockState top)
		{
			this.top = top;
			return this;
		}
		public TBOSurfaceConfig setUnderMaterial(BlockState under)
		{
			this.under = under;
			return this;
		}
		public TBOSurfaceConfig setUnderwaterMaterial(BlockState waterfloor)
		{
			this.waterfloor = waterfloor;
			return this;
		}

		@Override
		public TBOSurfaceConfig clone()
		{
			return new TBOSurfaceConfig(this.top, this.under, this.waterfloor);
		}

	}
}
