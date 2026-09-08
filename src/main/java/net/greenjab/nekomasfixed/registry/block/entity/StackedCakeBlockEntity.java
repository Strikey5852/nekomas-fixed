package net.greenjab.nekomasfixed.registry.block.entity;

import net.greenjab.nekomasfixed.registry.registries.BlockEntityTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class StackedCakeBlockEntity extends BlockEntity {
    public BlockState LAYER_2_STATE = Blocks.AIR.defaultBlockState();
    public BlockState LAYER_3_STATE = Blocks.AIR.defaultBlockState();
    public BlockState CANDLE_STATE = Blocks.AIR.defaultBlockState();

    public StackedCakeBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypeRegistry.STACKED_CAKE_BLOCK_ENTITY, pos, state);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void saveAdditional(@NonNull CompoundTag tag, HolderLookup.@NonNull Provider registries) {
        super.saveAdditional(tag, registries);
        BlockState.CODEC.encodeStart(NbtOps.INSTANCE, this.LAYER_2_STATE)
                .result().ifPresent(value -> tag.put("layer_2", value));
        BlockState.CODEC.encodeStart(NbtOps.INSTANCE, this.LAYER_3_STATE)
                .result().ifPresent(value -> tag.put("layer_3", value));
        BlockState.CODEC.encodeStart(NbtOps.INSTANCE, this.CANDLE_STATE)
                .result().ifPresent(value -> tag.put("candle", value));
    }

    @Override
    protected void loadAdditional(@NonNull CompoundTag tag, HolderLookup.@NonNull Provider registries) {
        super.loadAdditional(tag, registries);
        this.LAYER_2_STATE = tag.contains("layer_2")
                ? BlockState.CODEC.parse(NbtOps.INSTANCE, tag.get("layer_2")).result().orElse(Blocks.AIR.defaultBlockState())
                : Blocks.AIR.defaultBlockState();
        this.LAYER_3_STATE = tag.contains("layer_3")
                ? BlockState.CODEC.parse(NbtOps.INSTANCE, tag.get("layer_3")).result().orElse(Blocks.AIR.defaultBlockState())
                : Blocks.AIR.defaultBlockState();
        this.CANDLE_STATE = tag.contains("candle")
                ? BlockState.CODEC.parse(NbtOps.INSTANCE, tag.get("candle")).result().orElse(Blocks.AIR.defaultBlockState())
                : Blocks.AIR.defaultBlockState();
    }

    @Override
    public @NonNull CompoundTag getUpdateTag(HolderLookup.@NonNull Provider registries) {
        return saveWithoutMetadata(registries);
    }
}