package net.greenjab.nekomasfixed.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.greenjab.nekomasfixed.registry.registries.BlockRegistry;
import net.greenjab.nekomasfixed.util.BlockDyeMap;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;

import java.util.concurrent.CompletableFuture;

/**
 * Generates the colour-suite block loot tables.
 * Slabs use createSlabItemTable so the double (stacked) state drops 2 items
 * (vanilla-correct; main used dropSelf which dropped only 1). All others self-drop.
 */
public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    private static boolean isModBlock(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals("nekomasfixed");
    }

    @Override
    public void generate() {
        BlockDyeMap.BRICKS.values().forEach(this::dropSelf);
        // Slabs use createSlabItemTable so the double (stacked) state drops 2
        // (vanilla-correct; main used dropSelf which dropped only 1). The builder
        // must be registered explicitly via add(Block, builder).
        BlockDyeMap.BRICK_SLAB.values().forEach(block -> this.add(block, createSlabItemTable(block)));
        BlockDyeMap.BRICK_STAIRS.values().forEach(this::dropSelf);
        BlockDyeMap.BRICK_WALL.values().forEach(this::dropSelf);
        BlockDyeMap.SPOTTED_WOOL.values().forEach(this::dropSelf);
        BlockDyeMap.SPOTTED_CARPET.values().forEach(this::dropSelf);
        // WOOL/CARPET maps mix mod ancient blocks; filter so vanilla blocks are never overridden.
        BlockDyeMap.WOOL.values().stream().filter(ModLootTableProvider::isModBlock).forEach(this::dropSelf);
        BlockDyeMap.CARPET.values().stream().filter(ModLootTableProvider::isModBlock).forEach(this::dropSelf);
        // Stained glass + panes are silk-touch only (Vanilla-correct: glass drops nothing
        // without silk touch). Filter to mod blocks so vanilla glass loot is untouched.
        BlockDyeMap.STAINED_GLASS.values().stream()
                .filter(ModLootTableProvider::isModBlock)
                .forEach(block -> this.add(block, createSilkTouchOnlyTable(block)));
        BlockDyeMap.STAINED_GLASS_PANE.values().stream()
                .filter(ModLootTableProvider::isModBlock)
                .forEach(block -> this.add(block, createSilkTouchOnlyTable(block)));
        // Candles drop 1-4 based on the candles state (vanilla createCandleDrops shape).
        BlockDyeMap.CANDLE.values().stream()
                .filter(ModLootTableProvider::isModBlock)
                .forEach(block -> this.add(block, createCandleDrops(block)));
        // Beds drop only from the head half (vanilla single-prop condition table).
        BlockDyeMap.BED.values().stream()
                .filter(ModLootTableProvider::isModBlock)
                .forEach(block -> this.add(block,
                        createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD)));
        // Shulker boxes drop themselves preserving container/custom-name/lock (vanilla shape).
        BlockDyeMap.SHULKER_BOX.values().stream()
                .filter(ModLootTableProvider::isModBlock)
                .forEach(block -> this.add(block, createShulkerBoxDrop(block)));
        // Some dye maps mix in vanilla blocks (e.g. 3 vanilla froglights in the 20-colour
        // FROGLIGHT set). Only emit loot for mod blocks; never override vanilla ones.
        BlockDyeMap.FROGLIGHT.values().stream()
                .filter(ModLootTableProvider::isModBlock)
                .forEach(this::dropSelf);

        // Baobab wood-family self-drop blocks (baobab_fruit has a custom fortune/
        // alternatives loot, and the boats are items, so both stay hand-written).
        // The slab drops 2 in the double (stacked) state.
        this.dropSelf(BlockRegistry.BAOBAB_BUTTON);
        this.dropSelf(BlockRegistry.BAOBAB_DOOR);
        this.dropSelf(BlockRegistry.BAOBAB_FENCE);
        this.dropSelf(BlockRegistry.BAOBAB_FENCE_GATE);
        this.dropSelf(BlockRegistry.BAOBAB_HANGING_SIGN);
        // baobab_leaves has bespoke shears/silk-touch/fortune loot (drops sapling/
        // stick), and baobab_fruit/boats are bespoke too, so ALL three stay hand-written.
        this.dropSelf(BlockRegistry.BAOBAB_LOG);
        this.dropSelf(BlockRegistry.BAOBAB_WOOD);
        this.dropSelf(BlockRegistry.STRIPPED_BAOBAB_LOG);
        this.dropSelf(BlockRegistry.STRIPPED_BAOBAB_WOOD);
        this.dropSelf(BlockRegistry.BAOBAB_PLANKS);
        this.dropSelf(BlockRegistry.BAOBAB_PRESSURE_PLATE);
        this.dropSelf(BlockRegistry.BAOBAB_SAPLING);
        this.dropSelf(BlockRegistry.BAOBAB_SIGN);
        this.dropSelf(BlockRegistry.BAOBAB_STAIRS);
        this.dropSelf(BlockRegistry.BAOBAB_TRAPDOOR);
        this.add(BlockRegistry.BAOBAB_SLAB, createSlabItemTable(BlockRegistry.BAOBAB_SLAB));

        // Hollow-log family: all 12 are plain self-drop (their planks craft is via
        // recipes, and the pot-drop is handled in the block entity, not the loot table).
        this.dropSelf(BlockRegistry.HOLLOW_OAK_LOG);
        this.dropSelf(BlockRegistry.HOLLOW_SPRUCE_LOG);
        this.dropSelf(BlockRegistry.HOLLOW_BIRCH_LOG);
        this.dropSelf(BlockRegistry.HOLLOW_JUNGLE_LOG);
        this.dropSelf(BlockRegistry.HOLLOW_ACACIA_LOG);
        this.dropSelf(BlockRegistry.HOLLOW_DARK_OAK_LOG);
        this.dropSelf(BlockRegistry.HOLLOW_MANGROVE_LOG);
        this.dropSelf(BlockRegistry.HOLLOW_CHERRY_LOG);
        this.dropSelf(BlockRegistry.HOLLOW_BAMBOO_BLOCK);
        this.dropSelf(BlockRegistry.HOLLOW_CRIMSON_STEM);
        this.dropSelf(BlockRegistry.HOLLOW_WARPED_STEM);
        this.dropSelf(BlockRegistry.HOLLOW_BAOBAB_LOG);
    }
}