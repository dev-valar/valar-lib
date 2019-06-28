package modfest.valar.common.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import modfest.valar.common.math.ImmutableTriple;
import modfest.valar.common.math.Triple;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;

public class BlockGenerator
{
	protected ModifiableTestableWorld world;
	public Set<BlockPos> set;
	protected boolean canGenerate = true;
	protected List<Triple<BlockPos, BlockState, Boolean>> gen = new ArrayList<>();

	public BlockGenerator(ModifiableTestableWorld world, Set<BlockPos> set)
	{
		this.world = world;
		this.set = set;
	}

	public void setBlock(BlockPos pos, BlockState state, boolean ignore)
	{
		boolean replaceable = canTreeReplace(this.world, pos);

		if (!ignore) this.canGenerate = this.canGenerate && replaceable;

		this.gen.add(new ImmutableTriple<>(pos, state, replaceable || ignore));
	}

	public boolean generate(PublicWorldModifierTester generator)
	{
		return this.generate(false, generator);
	}

	public boolean generate(boolean forceGeneration, PublicWorldModifierTester generator)
	{
		if (this.canGenerate || forceGeneration)
		{
			for (Triple<BlockPos, BlockState, Boolean> pair : this.gen)
				if (pair.getC())
				{
					generator.setWorldBlockState(this.set, this.world, pair.getA(), pair.getB());
				}

			return true;
		}
		else return false;
	}

	protected static boolean canTreeReplace(TestableWorld testableWorld_1, BlockPos blockPos_1)
	{
		return testableWorld_1.testBlockState(blockPos_1, (blockState_1) -> {
			Block block_1 = blockState_1.getBlock();
			return blockState_1.isAir() || blockState_1.matches(BlockTags.LEAVES) || block_1 == Blocks.GRASS_BLOCK || Block.isNaturalDirt(block_1) || block_1.matches(BlockTags.LOGS) || block_1.matches(BlockTags.SAPLINGS) || block_1 == Blocks.VINE;
		});
	}
}
