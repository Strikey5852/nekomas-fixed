package net.greenjab.nekomasfixed.registry.block.entity;

import net.greenjab.nekomasfixed.registry.block.AbstractEndermanHeadBlock;
import net.greenjab.nekomasfixed.registry.registries.BlockEntityTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public class EndermanHeadBlockEntity extends BlockEntity {

    public EndermanHeadBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityTypeRegistry.ENDERMAN_HEAD_BLOCK_ENTITY, pos, state);
    }

    protected EndermanHeadBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, EndermanHeadBlockEntity blockEntity) {
        int power = state.getValue(AbstractEndermanHeadBlock.POWER);
        int newPower = 0;
        if (level instanceof ServerLevel serverLevel && level.getGameTime() % 10L == 0L) {
            newPower = getPlayerLooking(level, serverLevel.getServer().getPlayerList(), pos, level.dimension());
            if (power != newPower) {
                ((AbstractEndermanHeadBlock) state.getBlock()).setPower(level, pos, state, newPower);
                if (power == 0 && newPower > 0) {
                    level.playSound(null, pos, SoundEvents.ENDERMAN_SCREAM, SoundSource.BLOCKS, 0.3F, 0.8F);
                }
            }
        }

        if (state.getBlock() instanceof AbstractEndermanHeadBlock && newPower > 0
                && level.getGameTime() % 10L == 0L && level.getRandom().nextInt(10) == 0) {
            level.playSound(null, pos, SoundEvents.ENDERMAN_SCREAM, SoundSource.BLOCKS, 0.3F, 0.8F);
        }
    }

    public static int getPlayerLooking(Level level, PlayerList playerManager, BlockPos pos, ResourceKey<Level> levelKey) {
        int max = 0;
        for (int i = 0; i < playerManager.getPlayers().size(); i++) {
            ServerPlayer player = playerManager.getPlayers().get(i);
            if (player.isSpectator()) continue;
            if (player.level().dimension() != levelKey) continue;
            double x1 = pos.getX() - player.getX();
            double y1 = pos.getY() - player.getY();
            double z1 = pos.getZ() - player.getZ();
            double dist = Math.sqrt(x1 * x1 + y1 * y1 + z1 * z1);
            if (dist < 50) {
                BlockHitResult hitResult = raycast(level, player);
                if (pos.equals(hitResult.getBlockPos())) {
                    int v = (int) Mth.clamp((48 - dist) / 3, 1, 15);
                    if (v > max) max = v;
                    if (max == 15) return 15;
                }
            }
        }
        return max;
    }

    protected static BlockHitResult raycast(Level level, Player player) {
        Vec3 eyePos = player.getEyePosition();
        Vec3 lookTarget = eyePos.add(player.calculateViewVector(player.getXRot(), player.getYRot()).scale(45));
        return level.clip(new ClipContext(eyePos, lookTarget, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
    }

    @Override
    protected void loadAdditional(@NonNull CompoundTag tag, HolderLookup.@NonNull Provider registries) {
        super.loadAdditional(tag, registries);
    }

    @Override
    protected void saveAdditional(@NonNull CompoundTag tag, HolderLookup.@NonNull Provider registries) {
        super.saveAdditional(tag, registries);
    }
}