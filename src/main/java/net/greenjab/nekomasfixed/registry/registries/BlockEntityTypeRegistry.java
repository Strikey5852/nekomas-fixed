package net.greenjab.nekomasfixed.registry.registries;

import net.greenjab.nekomasfixed.NekomasFixed;
import net.greenjab.nekomasfixed.registry.block.entity.ClamBlockEntity;
import net.greenjab.nekomasfixed.registry.block.entity.EndermanHeadBlockEntity;
import net.greenjab.nekomasfixed.registry.block.entity.HollowLogBlockEntity;
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

    @SuppressWarnings("DataFlowIssue")
    public static final BlockEntityType<HollowLogBlockEntity> HOLLOW_LOG_BLOCK_ENTITY =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, NekomasFixed.id("hollow_log"),
                    new BlockEntityType<>(
                            HollowLogBlockEntity::new,
                            Set.of(BlockRegistry.HOLLOW_OAK_LOG, BlockRegistry.HOLLOW_SPRUCE_LOG,
                                    BlockRegistry.HOLLOW_BIRCH_LOG, BlockRegistry.HOLLOW_JUNGLE_LOG,
                                    BlockRegistry.HOLLOW_ACACIA_LOG, BlockRegistry.HOLLOW_DARK_OAK_LOG,
                                    BlockRegistry.HOLLOW_MANGROVE_LOG, BlockRegistry.HOLLOW_CHERRY_LOG,
                                    BlockRegistry.HOLLOW_BAMBOO_BLOCK,
                                    BlockRegistry.HOLLOW_CRIMSON_STEM, BlockRegistry.HOLLOW_WARPED_STEM,
                                    BlockRegistry.HOLLOW_BAOBAB_LOG),
                            null));

    @SuppressWarnings("DataFlowIssue")
    public static final BlockEntityType<EndermanHeadBlockEntity> ENDERMAN_HEAD_BLOCK_ENTITY =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, NekomasFixed.id("enderman_head"),
                    new BlockEntityType<>(
                            EndermanHeadBlockEntity::new,
                            Set.of(BlockRegistry.ENDERMAN_HEAD, BlockRegistry.WALL_ENDERMAN_HEAD),
                            null));

    public static void registerBlockEntityTypes() {
        NekomasFixed.LOGGER.info("Registering block entity types");
    }
}
