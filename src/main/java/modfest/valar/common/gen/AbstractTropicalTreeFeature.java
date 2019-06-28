package modfest.valar.common.gen;

import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public abstract class AbstractTropicalTreeFeature extends AbstractTreeFeature<DefaultFeatureConfig> implements PublicWorldModifierTester
{
	private final int heightMin;
	private final int heightDelta;
	private boolean isBeachFeature, isWaterFeature, isStoneResistant = false;
	
	protected Predicate<BlockState> genPredicate = (blockState_1) -> {
		Block block = blockState_1.getBlock();
		return block == Blocks.GRASS && (block != Blocks.GRASS_BLOCK && block != Blocks.DIRT && block != Blocks.COARSE_DIRT);
	};
	
	public AbstractTropicalTreeFeature(boolean notify, int minHeight, int maxHeight)
	{
		super(DefaultFeatureConfig::deserialize, notify);
		
		this.heightMin = minHeight;
		this.heightDelta = maxHeight - minHeight;
	}
	
	protected void setBeachFeature()
	{
		this.isBeachFeature = true;
	}
	
	protected void setStoneResistant()
	{
		this.isStoneResistant = true;
	}
	
	protected void setWaterFeature()
	{
		this.isWaterFeature = true;
	}


	@Override
	public void setWorldBlockState(Set<BlockPos> set, ModifiableTestableWorld world, BlockPos pos, BlockState state)
	{
		if ((canTreeReplace(world, pos) || world.testBlockState(pos, genPredicate)))
		{
			super.setBlockState(set, world, pos, state, new MutableIntBoundingBox());
		}
	}

	@Override
	protected boolean generate(Set<BlockPos> set_1, ModifiableTestableWorld world, Random rand, BlockPos blockPos_1, MutableIntBoundingBox mutableIntBoundingBox)
	{
		int height = heightMin + rand.nextInt(heightDelta);
		
		blockPos_1 = world.getTopPosition(Heightmap.Type.OCEAN_FLOOR, blockPos_1);

		BlockGenerator generator = new BlockGenerator(world, set_1);
		
		if (blockPos_1.getY() >= 1 && blockPos_1.getY() + height + 1 <= 256 && 
				(this.isWaterFeature || !isWater(world, blockPos_1)) && 
				((this.isBeachFeature && this.isSandOrClay(world, blockPos_1.down())) || (this.isStoneResistant && this.isStone(world, blockPos_1.down())) || super.isNaturalDirtOrGrass(world, blockPos_1.down())))
		{
			
			this.generateBlocks(world, generator, height, rand, blockPos_1);
			
			return generator.generate(this);
		}
		else
		{
			return false;
		}
	}
	
	private boolean isSandOrClay(TestableWorld world, BlockPos down)
	{
		return world.testBlockState(down, (blockState_1) -> {
			Block block_1 = blockState_1.getBlock();
			return block_1 == Blocks.RED_SAND || block_1 == Blocks.SAND || block_1 == Blocks.CLAY;
		});
	}
	
	private boolean isStone(TestableWorld world, BlockPos down)
	{
		return world.testBlockState(down, (blockState_1) -> {
			return blockState_1.getMaterial() == Material.STONE;
		});
	}
	
	protected abstract void generateBlocks(ModifiableTestableWorld world, BlockGenerator generator, int height, Random rand, BlockPos pos);
}
