package net.greenjab.nekomasfixed.registry.block.entity;

import net.greenjab.nekomasfixed.registry.block.ClamBlock;
import net.greenjab.nekomasfixed.registry.registries.BlockEntityTypeRegistry;
import net.greenjab.nekomasfixed.registry.registries.ComponentRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class ClamBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {
    private final ChestLidController lidAnimator = new ChestLidController();
    private NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);
    private int state = 0;

    public ClamBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityTypeRegistry.CLAM_BLOCK_ENTITY, pos, state);
    }

    protected ClamBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, ClamBlockEntity blockEntity) {
        blockEntity.lidAnimator.shouldBeOpen(state.getValue(ClamBlock.OPEN));
        blockEntity.lidAnimator.tickLid();
        if (state.getValue(ClamBlock.OPEN) && state.getValue(ClamBlock.WATERLOGGED)
                && blockEntity.lidAnimator.getOpenness(0) < 1) {
            level.addParticle(ParticleTypes.BUBBLE,
                    pos.getX() + 0.5 + level.getRandom().nextGaussian() * 0.15,
                    pos.getY() + 0.2,
                    pos.getZ() + 0.5 + level.getRandom().nextGaussian() * 0.15,
                    0.0, 0.75, 0.0);
        }
    }

    @Override
    protected void loadAdditional(@NonNull CompoundTag tag, HolderLookup.@NonNull Provider registries) {
        super.loadAdditional(tag, registries);
        this.readInventoryNbt(tag, registries);
    }

    public void readInventoryNbt(CompoundTag tag, HolderLookup.Provider registries) {
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.inventory, registries);
        }
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NonNull CompoundTag getUpdateTag(HolderLookup.@NonNull Provider registries) {
        CompoundTag compoundTag = new CompoundTag();
        this.saveAdditional(compoundTag, registries);
        return compoundTag;
    }

    @Override
    protected void saveAdditional(@NonNull CompoundTag tag, HolderLookup.@NonNull Provider registries) {
        super.saveAdditional(tag, registries);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.inventory, registries);
        }
    }

    @Override
    protected @NonNull Component getDefaultName() {
        return Component.literal("clam");
    }

    @Override
    public @NonNull NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void setItems(@NonNull NonNullList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    @Override
    protected @NonNull AbstractContainerMenu createMenu(int syncId, @NonNull Inventory playerInventory) {
        return ChestMenu.threeRows(syncId, playerInventory, this);
    }

    public ItemStack swapStack(int slot, ItemStack stack) {
        ItemStack itemStack = this.removeItemNoUpdate(slot);
        this.setItem(slot, stack);
        return itemStack;
    }

    @Override
    public float getOpenNess(float tickProgress) {
        return this.lidAnimator.getOpenness(tickProgress);
    }

    public void setHeldStack(ItemStack itemStack) {
        this.inventory.set(0, itemStack);
    }

    public void setState(int cstate) {
        this.state = cstate;
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.@NonNull DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.state = componentInput.getOrDefault(ComponentRegistry.CLAM_STATE, 0);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.@NonNull Builder builder) {
        super.collectImplicitComponents(builder);
        if (this.state != 0) {
            builder.set(ComponentRegistry.CLAM_STATE, this.state);
        }
    }
}
