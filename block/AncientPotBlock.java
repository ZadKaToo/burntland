
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

public class AncientPotBlock extends Block {
	public AncientPotBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.DECORATED_POT_CRACKED).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
		return Shapes.or(box(1, 3, 1, 15, 14, 15), box(3, 15, 3, 13, 25, 13), box(2, 14, 2, 14, 15, 14), box(2, 25, 2, 14, 26, 14), box(1, 26, 1, 15, 27, 15), box(0, 0, 0, 16, 1, 16), box(2, 1, 2, 14, 3, 14));
	}
}
