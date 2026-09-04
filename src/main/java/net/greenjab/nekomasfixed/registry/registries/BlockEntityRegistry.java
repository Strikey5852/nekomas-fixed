package net.greenjab.nekomasfixed.registry.registries;

import net.greenjab.nekomasfixed.mixin.BlockEntityTypeAccessor;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Set;

public class BlockEntityRegistry {

    public static void attachBlockEntities() {
        attachSignBlockEntities();
    }

    private static void attachSignBlockEntities() {
        ((BlockEntityTypeAccessor) BlockEntityType.SIGN)
                .nekomasfixed$getValidBlocks()
                .addAll(Set.of(
                        BlockRegistry.BAOBAB_SIGN,
                        BlockRegistry.BAOBAB_WALL_SIGN
                ));
        ((BlockEntityTypeAccessor) BlockEntityType.HANGING_SIGN)
                .nekomasfixed$getValidBlocks()
                .addAll(Set.of(
                        BlockRegistry.BAOBAB_HANGING_SIGN,
                        BlockRegistry.BAOBAB_WALL_HANGING_SIGN
                ));
    }
}
