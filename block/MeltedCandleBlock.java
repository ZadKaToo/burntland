
package net.com.burntland.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class MeltedCandleBlock extends Block {
	public MeltedCandleBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.CANDLE).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.or(box(5, 0, 8, 10, 1, 11), box(7, 1, 8, 11, 2, 10), box(8, 2, 8, 10, 3, 10), box(9, 1, 10, 11, 2, 12), box(8, 1, 6, 10, 2, 8), box(8, 0, 11, 10, 1, 14), box(10, 0, 8, 12, 1, 13), box(6, 0, 5, 10, 1, 8),
				box(10, 0, 4, 11, 1, 8));
	}
}
