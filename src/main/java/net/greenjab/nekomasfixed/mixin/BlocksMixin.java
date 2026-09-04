package net.greenjab.nekomasfixed.mixin;

import net.greenjab.nekomasfixed.mixin.accessor.BlockBehaviourAccessor;
import net.greenjab.nekomasfixed.registry.block.MelonBlock;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public class BlocksMixin {
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/Blocks;register(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/block/Block;)Lnet/minecraft/world/level/block/Block;",
                    ordinal = 0
            ),
            slice = @Slice(
                    from = @At(
                            value = "FIELD",
                            target = "Lnet/minecraft/references/Blocks;MELON:Lnet/minecraft/resources/ResourceKey;",
                            opcode = Opcodes.GETSTATIC
                    ),
                    to = @At(
                            value = "FIELD",
                            target = "Lnet/minecraft/world/level/block/Blocks;MELON:Lnet/minecraft/world/level/block/Block;",
                            opcode = Opcodes.PUTSTATIC
                    )
            )
    )
    private static Block registerMelon(ResourceKey<Block> key, Block vanilla) {
        Blocks.register(key, vanilla);
        BlockBehaviour.Properties properties = ((BlockBehaviourAccessor) vanilla).getProperties();
        return Blocks.register(key, new MelonBlock(false, properties));
    }
}