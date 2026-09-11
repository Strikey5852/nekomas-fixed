package net.greenjab.nekomasfixed.mixin;

import net.greenjab.nekomasfixed.registry.item.RedstoneStrikerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {
    /**
     * Expires struck-redstone entries once their game-time is passed, so a struck
     * block stops reading as fully powered and its neighbours are nudged again.
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void depowerRedstoneStruckBlocks(BooleanSupplier haveTime, CallbackInfo ci) {
        ServerLevel level = (ServerLevel) (Object) this;
        HashMap<GlobalPos, Long> copy = new HashMap<>(RedstoneStrikerItem.STRUCK_WIRES);
        for (Map.Entry<GlobalPos, Long> entry : copy.entrySet()) {
            if (level.getGameTime() > entry.getValue()) {
                GlobalPos globalPos = entry.getKey();
                if (level.dimension() == globalPos.dimension()) {
                    BlockPos pos = globalPos.pos();
                    BlockState state = level.getBlockState(pos);
                    RedstoneStrikerItem.STRUCK_WIRES.remove(globalPos);
                    state.handleNeighborChanged(level, pos, Blocks.AIR, pos, false);
                    level.updateNeighborsAt(pos, state.getBlock());
                }
            }
        }
    }
}