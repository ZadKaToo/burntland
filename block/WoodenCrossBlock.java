
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

public class WoodenCrossBlock extends Block {
	public WoodenCrossBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
		return Shapes.or(box(7, 0, 7, 9, 32, 9), box(-1, 22, 7, 7, 24, 9), box(9, 22, 7, 17, 24, 9), box(8, 21, 6, 10, 25, 7), box(7, 22, 5, 11, 24, 6), box(7, 22, 10, 11, 24, 11), box(11, 22, 5, 12, 24, 11), box(6, 22, 5, 7, 24, 11),
				box(8, 25, 6, 10, 26, 10), box(8, 20, 6, 10, 21, 10), box(8, 21, 9, 10, 25, 10));
	}
}
