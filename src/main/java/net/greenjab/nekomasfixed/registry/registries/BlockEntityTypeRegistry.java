package net.greenjab.nekomasfixed.registry.registries;

import net.greenjab.nekomasfixed.NekomasFixed;
import net.greenjab.nekomasfixed.registry.block.entity.ClamBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Set;

public class BlockEntityTypeRegistry {

    @SuppressWarnings("DataFlowIssue")
    public static final BlockEntityType<ClamBlockEntity> CLAM_BLOCK_ENTITY =
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, NekomasFixed.id("clam"),
            new BlockEntityType<>(
                ClamBlockEntity::new,
                Set.of(BlockRegistry.CLAM, BlockRegistry.CLAM_BLUE, BlockRegistry.CLAM_PINK, BlockRegistry.CLAM_PURPLE),
                null));

    public static void registerBlockEntityTypes() {
        NekomasFixed.LOGGER.info("Registering block entity types");
    }
}
