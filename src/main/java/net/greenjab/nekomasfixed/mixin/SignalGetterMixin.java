package net.greenjab.nekomasfixed.mixin;

import net.greenjab.nekomasfixed.registry.item.RedstoneStrikerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.SignalGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SignalGetter.class)
public interface SignalGetterMixin {
    // A struck redstone-conductor outputs a full 15 signal to its neighbours.
    @Inject(method = "getSignal(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)I",
            at = @At("HEAD"), cancellable = true)
    private void powerBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Integer> cir) {
        Level level = (Level) this;
        if (RedstoneStrikerItem.STRUCK_WIRES.containsKey(new GlobalPos(level.dimension(), pos))) {
            BlockState state = level.getBlockState(pos);
            if (state.isRedstoneConductor(level, pos)) {
                cir.setReturnValue(15);
            }
        }
    }

    // A struck position also reads as having a neighbour signal (drives components).
    @Inject(method = "hasNeighborSignal", at = @At("HEAD"), cancellable = true)
    private void powerRedstoneComponents(BlockPos blockPos, CallbackInfoReturnable<Boolean> cir) {
        if (RedstoneStrikerItem.STRUCK_WIRES.containsKey(new GlobalPos(((Level) this).dimension(), blockPos))) {
            cir.setReturnValue(true);
        }
    }
}