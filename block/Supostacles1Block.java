
package net.com.burntland.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.com.burntland.procedures.PostbutdarkProcedure;
import net.com.burntland.init.BurntLandModBlocks;

public class Supostacles1Block extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public Supostacles1Block() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
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
		return switch (state.getValue(FACING)) {
			default -> Shapes.or(box(4, -1, 13, 6, 9, 14), box(4, 6, 10, 5, 9, 11), box(10, -1, 10, 12, 9, 11), box(6, 6, 7, 7, 9, 8), box(12, -1, 7, 14, 9, 8), box(2, 6, 7, 3, 9, 8), box(8, -1, 7, 10, 9, 8), box(-2, 6, 2, -1, 9, 3),
					box(3, 6, 13, 4, 9, 14), box(9, -1, 13, 11, 9, 14), box(4, -1, 2, 6, 9, 3), box(4, 6, 2, 5, 9, 3), box(10, -1, 2, 12, 9, 3), box(-1, 6, 5, 0, 9, 6), box(5, -1, 5, 7, 9, 6), box(-2, 6, 13, -1, 9, 14));
			case NORTH -> Shapes.or(box(10, -1, 2, 12, 9, 3), box(11, 6, 5, 12, 9, 6), box(4, -1, 5, 6, 9, 6), box(9, 6, 8, 10, 9, 9), box(2, -1, 8, 4, 9, 9), box(13, 6, 8, 14, 9, 9), box(6, -1, 8, 8, 9, 9), box(17, 6, 13, 18, 9, 14),
					box(12, 6, 2, 13, 9, 3), box(5, -1, 2, 7, 9, 3), box(10, -1, 13, 12, 9, 14), box(11, 6, 13, 12, 9, 14), box(4, -1, 13, 6, 9, 14), box(16, 6, 10, 17, 9, 11), box(9, -1, 10, 11, 9, 11), box(17, 6, 2, 18, 9, 3));
			case EAST -> Shapes.or(box(13, -1, 10, 14, 9, 12), box(10, 6, 11, 11, 9, 12), box(10, -1, 4, 11, 9, 6), box(7, 6, 9, 8, 9, 10), box(7, -1, 2, 8, 9, 4), box(7, 6, 13, 8, 9, 14), box(7, -1, 6, 8, 9, 8), box(2, 6, 17, 3, 9, 18),
					box(13, 6, 12, 14, 9, 13), box(13, -1, 5, 14, 9, 7), box(2, -1, 10, 3, 9, 12), box(2, 6, 11, 3, 9, 12), box(2, -1, 4, 3, 9, 6), box(5, 6, 16, 6, 9, 17), box(5, -1, 9, 6, 9, 11), box(13, 6, 17, 14, 9, 18));
			case WEST -> Shapes.or(box(2, -1, 4, 3, 9, 6), box(5, 6, 4, 6, 9, 5), box(5, -1, 10, 6, 9, 12), box(8, 6, 6, 9, 9, 7), box(8, -1, 12, 9, 9, 14), box(8, 6, 2, 9, 9, 3), box(8, -1, 8, 9, 9, 10), box(13, 6, -2, 14, 9, -1),
					box(2, 6, 3, 3, 9, 4), box(2, -1, 9, 3, 9, 11), box(13, -1, 4, 14, 9, 6), box(13, 6, 4, 14, 9, 5), box(13, -1, 10, 14, 9, 12), box(10, 6, -1, 11, 9, 0), box(10, -1, 5, 11, 9, 7), box(2, 6, -2, 3, 9, -1));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player) {
		return new ItemStack(BurntLandModBlocks.ABATIS.get());
	}

	@Override
	public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
		super.entityInside(blockstate, world, pos, entity);
		PostbutdarkProcedure.execute(world, entity);
	}
}
