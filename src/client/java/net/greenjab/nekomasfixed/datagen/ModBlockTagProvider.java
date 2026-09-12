package net.greenjab.nekomasfixed.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.greenjab.nekomasfixed.registry.registries.BlockRegistry;
import net.greenjab.nekomasfixed.util.BlockDyeMap;
import net.greenjab.nekomasfixed.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

/**
 * Generates the colour-suite block tags.
 * The coloured brick walls are also appended to the vanilla #minecraft:walls tag
 * (replace: false) so they connect to vanilla walls (1.21.1 WallBlock.connectsTo).
 */
public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        // Hollow-log family: all 12 hollow log/stem/block variants.
        Block hollowLogs[] = {
                BlockRegistry.HOLLOW_OAK_LOG, BlockRegistry.HOLLOW_SPRUCE_LOG,
                BlockRegistry.HOLLOW_BIRCH_LOG, BlockRegistry.HOLLOW_JUNGLE_LOG,
                BlockRegistry.HOLLOW_ACACIA_LOG, BlockRegistry.HOLLOW_DARK_OAK_LOG,
                BlockRegistry.HOLLOW_MANGROVE_LOG, BlockRegistry.HOLLOW_CHERRY_LOG,
                BlockRegistry.HOLLOW_BAMBOO_BLOCK, BlockRegistry.HOLLOW_CRIMSON_STEM,
                BlockRegistry.HOLLOW_WARPED_STEM, BlockRegistry.HOLLOW_BAOBAB_LOG
        };
        for (Block b : hollowLogs) {
            getOrCreateTagBuilder(ModTags.HOLLOW_LOGS).add(BuiltInRegistries.BLOCK.getKey(b));
        }
        getOrCreateTagBuilder(ModTags.BAOBAB_LOGS)
                .add(BuiltInRegistries.BLOCK.getKey(BlockRegistry.BAOBAB_LOG))
                .add(BuiltInRegistries.BLOCK.getKey(BlockRegistry.BAOBAB_WOOD))
                .add(BuiltInRegistries.BLOCK.getKey(BlockRegistry.STRIPPED_BAOBAB_LOG))
                .add(BuiltInRegistries.BLOCK.getKey(BlockRegistry.STRIPPED_BAOBAB_WOOD));
        BlockDyeMap.BRICKS.values().forEach(block -> getOrCreateTagBuilder(ModTags.BRICKS).add(BuiltInRegistries.BLOCK.getKey(block)));
        BlockDyeMap.BRICK_SLAB.values().forEach(block -> getOrCreateTagBuilder(ModTags.BRICK_SLABS).add(BuiltInRegistries.BLOCK.getKey(block)));
        BlockDyeMap.BRICK_STAIRS.values().forEach(block -> getOrCreateTagBuilder(ModTags.BRICK_STAIRS).add(BuiltInRegistries.BLOCK.getKey(block)));
        BlockDyeMap.BRICK_WALL.values().forEach(block -> {
            getOrCreateTagBuilder(ModTags.BRICK_WALLS).add(BuiltInRegistries.BLOCK.getKey(block));
            getOrCreateTagBuilder(BlockTags.WALLS).add(BuiltInRegistries.BLOCK.getKey(block));  // vanilla connectivity override
        });
        BlockDyeMap.SPOTTED_WOOL.values().forEach(block -> getOrCreateTagBuilder(ModTags.SPOTTED_WOOL).add(BuiltInRegistries.BLOCK.getKey(block)));
        BlockDyeMap.SPOTTED_CARPET.values().forEach(block -> getOrCreateTagBuilder(ModTags.SPOTTED_CARPET).add(BuiltInRegistries.BLOCK.getKey(block)));
        BlockDyeMap.FROGLIGHT.values().forEach(block -> getOrCreateTagBuilder(ModTags.FROGLIGHTS).add(BuiltInRegistries.BLOCK.getKey(block)));
        // Candles: vanilla #minecraft:candles tag override so ancient candles work with
        // cake/waxing interactions as vanilla candles do (replace: false semantics).
        BlockDyeMap.CANDLE.values().forEach(block -> getOrCreateTagBuilder(BlockTags.CANDLES).add(BuiltInRegistries.BLOCK.getKey(block)));
        // Beds: vanilla #minecraft:beds block tag override (replace: false semantics).
        BlockDyeMap.BED.values().forEach(block -> getOrCreateTagBuilder(BlockTags.BEDS).add(BuiltInRegistries.BLOCK.getKey(block)));
        // Shulker boxes: vanilla #minecraft:shulker_boxes block tag override (replace: false semantics).
        BlockDyeMap.SHULKER_BOX.values().forEach(block -> getOrCreateTagBuilder(BlockTags.SHULKER_BOXES).add(BuiltInRegistries.BLOCK.getKey(block)));
    }
}