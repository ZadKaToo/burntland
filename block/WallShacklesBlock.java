
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

public class WallShacklesBlock extends Block {
	public WallShacklesBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.CHAIN).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
		return Shapes.or(box(3, 29, 12, 7, 32, 16), box(11, 29, 12, 15, 32, 16), box(3, 20, 13, 7, 21, 15), box(11, 15, 13, 15, 16, 15), box(11, 11, 13, 15, 12, 15), box(3, 16, 13, 7, 17, 15), box(7, 16, 13, 8, 21, 15), box(15, 11, 13, 16, 16, 15),
				box(10, 11, 13, 11, 16, 15), box(2, 16, 13, 3, 21, 15));
	}
}
