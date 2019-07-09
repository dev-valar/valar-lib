package modfest.valar.common.gen;

import java.util.Set;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;

public interface PublicWorldModifierTester
{
	public void setWorldBlockState(Set<BlockPos> set, ModifiableTestableWorld world, BlockPos pos, BlockState state, MutableIntBoundingBox mibb);
}