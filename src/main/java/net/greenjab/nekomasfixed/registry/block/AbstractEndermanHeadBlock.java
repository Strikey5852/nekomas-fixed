package net.greenjab.nekomasfixed.registry.block;

import com.mojang.serialization.MapCodec;
import net.greenjab.nekomasfixed.registry.block.entity.EndermanHeadBlockEntity;
import net.greenjab.nekomasfixed.registry.registries.BlockEntityTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public abstract class AbstractEndermanHeadBlock extends BaseEntityBlock {
    public static final IntegerProperty POWER = BlockStateProperties.POWER;

    public AbstractEndermanHeadBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWER, 0));
    }

    @Override
    public abstract @NonNull MapCodec<? extends AbstractEndermanHeadBlock> codec();

    @Override
    public BlockState getStateForPlacement(@NonNull BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(POWER, 0);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWER);
    }

    @Override
    public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new EndermanHeadBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NonNull BlockState state, @NonNull BlockEntityType<T> type) {
        return level.isClientSide() ? null : createTickerHelper(type, BlockEntityTypeRegistry.ENDERMAN_HEAD_BLOCK_ENTITY, EndermanHeadBlockEntity::tick);
    }

    @Override
    protected int getSignal(BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull Direction direction) {
        return state.getValue(POWER);
    }

    @Override
    protected int getDirectSignal(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull Direction direction) {
        return direction == Direction.UP ? state.getSignal(level, pos, direction) : 0;
    }

    @Override
    protected boolean isSignalSource(@NonNull BlockState state) {
        return true;
    }

    // The block is drawn entirely by the block-entity renderer, so the empty
    // block model must not render (vanilla chests do the same).
    @Override
    protected @NonNull RenderShape getRenderShape(@NonNull BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    public void setPower(Level level, BlockPos pos, BlockState state, int power) {
        state = state.setValue(AbstractEndermanHeadBlock.POWER, power);
        level.setBlock(pos, state, Block.UPDATE_ALL);
        updateNeighbors(state, level, pos);
    }

    public void updateNeighbors(BlockState state, Level level, BlockPos pos) {
        Direction direction = Direction.DOWN;
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.relative(direction), this);
    }

    @Override
    protected void onRemove(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull BlockState newState, boolean movedByPiston) {
        if (!movedByPiston && state.getBlock() != newState.getBlock() && state.getValue(POWER) > 0) {
            this.updateNeighbors(state.setValue(POWER, 0), level, pos);
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected boolean isPathfindable(@NonNull BlockState state, @NonNull PathComputationType type) {
        return false;
    }
}