package net.greenjab.nekomasfixed.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.greenjab.nekomasfixed.registry.registries.BlockRegistry;
import net.greenjab.nekomasfixed.registry.registries.ItemRegistry;
import net.greenjab.nekomasfixed.util.BlockDyeMap;
import net.greenjab.nekomasfixed.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

/**
 * Generates the colour-suite item tags. Each block's item (via BuiltInRegistries.ITEM id)
 * is added to the matching item tag.
 */
public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        // Hollow-log family: item tag mirroring the block tag (hollow logs + their planks).
        Block hollowLogs[] = {
                BlockRegistry.HOLLOW_OAK_LOG, BlockRegistry.HOLLOW_SPRUCE_LOG,
                BlockRegistry.HOLLOW_BIRCH_LOG, BlockRegistry.HOLLOW_JUNGLE_LOG,
                BlockRegistry.HOLLOW_ACACIA_LOG, BlockRegistry.HOLLOW_DARK_OAK_LOG,
                BlockRegistry.HOLLOW_MANGROVE_LOG, BlockRegistry.HOLLOW_CHERRY_LOG,
                BlockRegistry.HOLLOW_BAMBOO_BLOCK, BlockRegistry.HOLLOW_CRIMSON_STEM,
                BlockRegistry.HOLLOW_WARPED_STEM, BlockRegistry.HOLLOW_BAOBAB_LOG
        };
        for (Block b : hollowLogs) {
            getOrCreateTagBuilder(ModTags.HOLLOW_LOGS_ITEM).add(BuiltInRegistries.ITEM.getKey(b.asItem()));
        }
        getOrCreateTagBuilder(ModTags.BAOBAB_LOGS_ITEM)
                .add(BuiltInRegistries.ITEM.getKey(ItemRegistry.BAOBAB_LOG))
                .add(BuiltInRegistries.ITEM.getKey(ItemRegistry.BAOBAB_WOOD))
                .add(BuiltInRegistries.ITEM.getKey(ItemRegistry.STRIPPED_BAOBAB_LOG))
                .add(BuiltInRegistries.ITEM.getKey(ItemRegistry.STRIPPED_BAOBAB_WOOD));
        BlockDyeMap.BRICKS.values().forEach(block -> getOrCreateTagBuilder(ModTags.BRICKS_ITEM).add(BuiltInRegistries.ITEM.getKey(block.asItem())));
        BlockDyeMap.BRICK_SLAB.values().forEach(block -> getOrCreateTagBuilder(ModTags.BRICK_SLABS_ITEM).add(BuiltInRegistries.ITEM.getKey(block.asItem())));
        BlockDyeMap.BRICK_STAIRS.values().forEach(block -> getOrCreateTagBuilder(ModTags.BRICK_STAIRS_ITEM).add(BuiltInRegistries.ITEM.getKey(block.asItem())));
        BlockDyeMap.BRICK_WALL.values().forEach(block -> getOrCreateTagBuilder(ModTags.BRICK_WALLS_ITEM).add(BuiltInRegistries.ITEM.getKey(block.asItem())));
        BlockDyeMap.SPOTTED_WOOL.values().forEach(block -> getOrCreateTagBuilder(ModTags.SPOTTED_WOOL_ITEM).add(BuiltInRegistries.ITEM.getKey(block.asItem())));
        BlockDyeMap.SPOTTED_CARPET.values().forEach(block -> getOrCreateTagBuilder(ModTags.SPOTTED_CARPET_ITEM).add(BuiltInRegistries.ITEM.getKey(block.asItem())));
        BlockDyeMap.FROGLIGHT.values().forEach(block -> getOrCreateTagBuilder(ModTags.FROGLIGHTS_ITEM).add(BuiltInRegistries.ITEM.getKey(block.asItem())));
        // The redstone striker can take Unbreaking via the vanilla durability-enchantable tag.
        getOrCreateTagBuilder(net.minecraft.tags.ItemTags.DURABILITY_ENCHANTABLE)
                .add(BuiltInRegistries.ITEM.getKey(ItemRegistry.REDSTONE_STRIKER));
    }
}
