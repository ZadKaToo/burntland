
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

public class InkPotQuillBlock extends Block {
	public InkPotQuillBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.LODESTONE).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
		return Shapes.or(box(4, 0, 7, 5, 6, 8), box(4, 0, 6, 5, 5, 7), box(4, 0, 8, 5, 5, 9), box(3, 0, 7, 4, 5, 8), box(5, 0, 7, 6, 5, 8), box(5, 0, 8, 6, 4, 9), box(5, 0, 6, 6, 4, 7), box(3, 0, 6, 4, 4, 7), box(3, 0, 8, 4, 4, 9),
				box(4, 6, 7, 5, 7, 8));
	}
}
