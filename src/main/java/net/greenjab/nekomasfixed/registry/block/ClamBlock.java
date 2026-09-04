package net.greenjab.nekomasfixed.registry.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.greenjab.nekomasfixed.registry.block.entity.ClamBlockEntity;
import net.greenjab.nekomasfixed.registry.block.enums.ClamType;
import net.greenjab.nekomasfixed.registry.registries.BlockEntityTypeRegistry;
import net.greenjab.nekomasfixed.registry.registries.BlockRegistry;
import net.greenjab.nekomasfixed.registry.registries.LootTableRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ClamBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {

    public static final MapCodec<ClamBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    ClamType.CODEC.fieldOf("clam_type").forGetter(ClamBlock::getClamType),
                    propertiesCodec()
            ).apply(instance, ClamBlock::new)
    );

    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final Map<Direction, VoxelShape> SHAPES_BY_DIRECTION =
            directionalShapes(Block.box(1.0, 0.0, 0.0, 15.0, 4.0, 15.0));

    private final ClamType clamType;

    public ClamBlock(ClamType clamType, Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(OPEN, false)
                .setValue(POWERED, false));
        this.clamType = clamType;
    }

    private static Map<Direction, VoxelShape> directionalShapes(VoxelShape base) {
        Map<Direction, VoxelShape> map = new EnumMap<>(Direction.class);
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            map.put(dir, rotateShape(dir, base));
        }
        return map;
    }

    private static VoxelShape rotateShape(Direction to, VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{shape, Shapes.empty()};
        int times = (to.get2DDataValue() - Direction.NORTH.get2DDataValue() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = Shapes.or(buffer[1],
                    Shapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = Shapes.empty();
        }
        return buffer[0];
    }

    public static ItemStack getItemStack(@Nullable ClamType clamType) {
        return new ItemStack(get(clamType));
    }

    public static Block get(@Nullable ClamType clamType) {
        if (clamType == null) {
            return BlockRegistry.CLAM;
        }
        return switch (clamType) {
            case REGULAR -> BlockRegistry.CLAM;
            case BLUE -> BlockRegistry.CLAM_BLUE;
            case PINK -> BlockRegistry.CLAM_PINK;
            case PURPLE -> BlockRegistry.CLAM_PURPLE;
        };
    }

    public static int getLuck(@Nullable ClamType clamType) {
        if (clamType == null) {
            return 0;
        }
        return switch (clamType) {
            case REGULAR -> 0;
            case BLUE -> 1;
            case PINK -> 2;
            case PURPLE -> 3;
        };
    }

    private static boolean swapSingleStack(ItemStack stack, Player player,
                                           ClamBlockEntity clamBlockEntity, Inventory playerInventory) {
        ItemStack itemStack = clamBlockEntity.swapStack(0, stack);
        ItemStack itemStack2 = player.getAbilities().instabuild && itemStack.isEmpty() ? stack.copy() : itemStack;
        playerInventory.setItem(playerInventory.selected, itemStack2);
        playerInventory.setChanged();
        clamBlockEntity.setChanged();
        if (clamBlockEntity.getLevel() != null) {
            clamBlockEntity.getLevel().sendBlockUpdated(
                    clamBlockEntity.getBlockPos(), clamBlockEntity.getBlockState(),
                    clamBlockEntity.getBlockState(), 3);
        }
        return !itemStack.isEmpty();
    }

    @Override
    public @NonNull MapCodec<? extends ClamBlock> codec() {
        return CODEC;
    }

    @Override
    public @NonNull RenderShape getRenderShape(@NonNull BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction direction = ctx.getHorizontalDirection().getOpposite();
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
        return this.defaultBlockState()
                .setValue(FACING, direction)
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER)
                .setValue(OPEN, false)
                .setValue(POWERED, false);
    }

    @Override
    public void setPlacedBy(Level level, @NonNull BlockPos pos, @NonNull BlockState state, LivingEntity placer, @NonNull ItemStack itemStack) {
        if (!level.isClientSide()) tryLaunch(state, level, pos);
    }

    private void tryLaunch(BlockState state, Level level, BlockPos pos) {
        boolean wasPowered = state.getValue(POWERED);
        boolean isPowered = level.hasNeighborSignal(pos);
        if (wasPowered != isPowered) {
            if (isPowered && !state.getValue(OPEN)) {
                List<Entity> entities = level.getEntities(null, new AABB(pos));
                for (Entity entity : entities) {
                    if (entity instanceof LivingEntity || entity instanceof ItemEntity) {
                        float power = level.getBestNeighborSignal(pos);
                        power = (float) (Math.sqrt(power) / 4.0f);
                        float dirx = -state.getValue(ClamBlock.FACING).getStepX();
                        float dirz = -state.getValue(ClamBlock.FACING).getStepZ();
                        if (entity instanceof ItemEntity) {
                            dirx *= 0.5f;
                            dirz *= 0.5f;
                        }
                        if (entity instanceof ServerPlayer serverPlayerEntity) {
                            serverPlayerEntity.connection.send(new ClientboundSetEntityMotionPacket(
                                    serverPlayerEntity.getId(), new Vec3(power * dirx, power, power * dirz)));
                        } else {
                            entity.setDeltaMovement(power * dirx, power, power * dirz);
                            // Force an immediate velocity sync so the client applies the launch
                            // right away (otherwise the item interpolates and "teleports" on open).
                            entity.hasImpulse = true;
                        }
                    }
                }
            }
            level.setBlock(pos, state.setValue(POWERED, isPowered).setValue(OPEN, isPowered), Block.UPDATE_CLIENTS);
        }
    }

    @Override
    public @NonNull BlockState playerWillDestroy(Level level, @NonNull BlockPos pos, @NonNull BlockState state, @NonNull Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof ClamBlockEntity clamBlockEntity) {
            int cstate = state.getValue(ClamBlock.OPEN) ? 1 : 0;
            if (cstate == 1 && !clamBlockEntity.getItems().isEmpty()
                    && !clamBlockEntity.getItems().getFirst().isEmpty()) {
                cstate++;
            }
            clamBlockEntity.setState(cstate);
            if (!level.isClientSide() && player.getAbilities().instabuild) {
                // Creative pick: fold the block entity's data components (incl. held item + state)
                // onto the dropped clam item so the contents are preserved.
                ItemStack itemStack = getItemStack(this.getClamType());
                itemStack.applyComponents(blockEntity.collectComponents());
                ItemEntity itemEntity = new ItemEntity(level,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack);
                itemEntity.setDefaultPickUpDelay();
                level.addFreshEntity(itemEntity);
            } else {
                clamBlockEntity.unpackLootTable(player);
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, OPEN, POWERED);
    }

    public ClamType getClamType() {
        return this.clamType;
    }

    @Override
    public BlockEntity newBlockEntity(@NonNull BlockPos pos, @NonNull BlockState state) {
        return new ClamBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NonNull BlockState state,
                                                                  @NonNull BlockEntityType<T> type) {
        return level.isClientSide()
                ? createTickerHelper(type, BlockEntityTypeRegistry.CLAM_BLOCK_ENTITY, ClamBlockEntity::clientTick)
                : null;
    }

    @Override
    protected boolean isPathfindable(@NonNull BlockState state, @NonNull PathComputationType type) {
        return false;
    }

    @Override
    protected @NonNull BlockState updateShape(BlockState state, @NonNull Direction direction, @NonNull BlockState neighborState,
                                              @NonNull LevelAccessor level, @NonNull BlockPos pos, @NonNull BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected void neighborChanged(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull Block neighborBlock,
                                   @NonNull BlockPos neighborPos, boolean movedByPiston) {
        if (!level.isClientSide()) tryLaunch(state, level, pos);
    }

    @Override
    protected void onPlace(BlockState state, @NonNull Level level, @NonNull BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.is(state.getBlock())) {
            if (!level.isClientSide() && level.getBlockEntity(pos) == null) tryLaunch(state, level, pos);
        }
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(@NonNull BlockState state, Level level, @NonNull BlockPos pos,
                                                        @NonNull Player player, @NonNull BlockHitResult hit) {
        if (!level.isClientSide()) {
            BlockState blockState = state.cycle(OPEN);
            level.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected @NonNull ItemInteractionResult useItemOn(@NonNull ItemStack stack, @NonNull BlockState state, Level level, @NonNull BlockPos pos,
                                                       @NonNull Player player, @NonNull InteractionHand hand, @NonNull BlockHitResult hit) {
        if (level.getBlockEntity(pos) instanceof ClamBlockEntity clamBlockEntity && !hand.equals(InteractionHand.OFF_HAND)) {
            if (!level.isClientSide()) {
                if (!(Boolean) state.getValue(OPEN) || player.isShiftKeyDown()) {
                    BlockState blockState = state.cycle(OPEN);
                    level.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
                    level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
                    return ItemInteractionResult.SUCCESS;
                }
                Inventory playerInventory = player.getInventory();
                boolean bl = swapSingleStack(stack, player, clamBlockEntity, playerInventory);
                if (bl) {
                    this.playSound(level, pos, SoundEvents.CHISELED_BOOKSHELF_PICKUP);
                } else {
                    if (stack.isEmpty()) {
                        BlockState blockState = state.cycle(OPEN);
                        level.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
                        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
                        return ItemInteractionResult.SUCCESS;
                    }
                    this.playSound(level, pos, SoundEvents.CHISELED_BOOKSHELF_INSERT);
                }
            }
            return ItemInteractionResult.SUCCESS;
        } else {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
    }

    @Override
    protected @NonNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected boolean hasAnalogOutputSignal(@NonNull BlockState state) {
        return true;
    }

    @Override
    protected @NonNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected @NonNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected int getAnalogOutputSignal(@NonNull BlockState state, Level level, @NonNull BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    @Override
    protected @NonNull VoxelShape getShape(BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPES_BY_DIRECTION.get(state.getValue(FACING));
    }

    @Override
    protected void randomTick(@NonNull BlockState state, ServerLevel level, @NonNull BlockPos pos, net.minecraft.util.@NonNull RandomSource random) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof ClamBlockEntity clamBlockEntity) {
            ItemStack item = clamBlockEntity.getItems().getFirst();
            BlockState below = level.getBlockState(pos.below());
            if (below.is(Blocks.SAND) || below.is(Blocks.GRAVEL) || below.is(Blocks.DIRT)) {
                if (state.getValue(OPEN)) {
                    if (item.isEmpty()) {
                        clamBlockEntity.setHeldStack(below.getBlock().asItem().getDefaultInstance());
                    } else if (item.is(below.getBlock().asItem())) {
                        clamBlockEntity.setHeldStack(item.copyWithCount(Math.min(item.getCount() + 1, item.getMaxStackSize())));
                    }
                    if (!state.getValue(POWERED) && random.nextInt(Math.max(64 - item.getCount(), 1)) < 4) {
                        BlockState blockState = state.cycle(OPEN);
                        level.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
                    }
                } else {
                    if (item.is(net.minecraft.world.item.Items.SAND) || item.is(net.minecraft.world.item.Items.GRAVEL)
                            || item.is(net.minecraft.world.item.Items.DIRT)) {
                        clamBlockEntity.setHeldStack(item.copyWithCount(item.getCount() - 1));
                        if (random.nextInt(16) == 0) {
                            net.minecraft.world.level.storage.loot.LootTable lootTable = level.getServer()
                                    .reloadableRegistries().getLootTable(LootTableRegistry.CLAM_LOOT_TABLE);
                            net.minecraft.world.level.storage.loot.LootParams lootParams =
                                    (new net.minecraft.world.level.storage.loot.LootParams.Builder(level))
                                            .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                                            .withLuck(getLuck(this.getClamType()))
                                            .create(net.minecraft.world.level.storage.loot.parameters.LootContextParamSets.FISHING);
                            net.minecraft.world.item.ItemStack loots = lootTable.getRandomItems(lootParams).getFirst();
                            ItemEntity itemEntity = new ItemEntity(level,
                                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, item);
                            itemEntity.setDefaultPickUpDelay();
                            level.addFreshEntity(itemEntity);
                            clamBlockEntity.setHeldStack(loots);
                        }
                    }
                    if (!state.getValue(POWERED) && random.nextInt(item.getCount() + 1) < 4) {
                        BlockState blockState = state.cycle(OPEN);
                        level.setBlock(pos, blockState, Block.UPDATE_CLIENTS);
                    }
                }
            }
        }
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(WATERLOGGED);
    }

    private void playSound(LevelAccessor level, BlockPos pos, SoundEvent sound) {
        level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
    }
}
