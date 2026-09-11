package net.greenjab.nekomasfixed.mixin.accessor;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.ObserverBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ObserverBlock.class)
public interface ObserverBlockAccessor {
    @Invoker("startSignal")
    void invokeStartSignal(LevelAccessor level, BlockPos pos);
}