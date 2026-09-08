package net.greenjab.nekomasfixed.util;

import net.greenjab.nekomasfixed.registry.registries.BlockRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.EnumMap;

/**
 * Central dye → block mapping for every dyed block category. Maps are filled
 * incrementally as each category's blocks are ported to 1.21.1 (the 26.x fills
 * used `Blocks.WOOL.white()`-style group accessors that do not exist in 1.21.1,
 * so fills reference the flat 1.21.1 block keys instead).
 */
public class BlockDyeMap {
    public static final EnumMap<AllDyes, Block> BRICKS = new EnumMap<>(AllDyes.class);
    public static final EnumMap<AllDyes, Block> BRICK_SLAB = new EnumMap<>(AllDyes.class);
    public static final EnumMap<AllDyes, Block> BRICK_STAIRS = new EnumMap<>(AllDyes.class);
    public static final EnumMap<AllDyes, Block> BRICK_WALL = new EnumMap<>(AllDyes.class);
    public static final EnumMap<AllDyes, Block> STAINED_GLASS = new EnumMap<>(AllDyes.class);
    public static final EnumMap<AllDyes, Block> STAINED_GLASS_PANE = new EnumMap<>(AllDyes.class);
    public static final EnumMap<AllDyes, Block> WOOL = new EnumMap<>(AllDyes.class);
    public static final EnumMap<AllDyes, Block> SPOTTED_WOOL = new EnumMap<>(AllDyes.class);
    public static final EnumMap<AllDyes, Block> CARPET = new EnumMap<>(AllDyes.class);
    public static final EnumMap<AllDyes, Block> SPOTTED_CARPET = new EnumMap<>(AllDyes.class);
    public static final EnumMap<AllDyes, Block> GLAZED_TERRACOTTA = new EnumMap<>(AllDyes.class);
    public static final EnumMap<AllDyes, Block> CANDLE = new EnumMap<>(AllDyes.class);
    public static final EnumMap<AllDyes, Block> FROGLIGHT = new EnumMap<>(AllDyes.class);
    public static final EnumMap<AllDyes, Block> SHULKER_BOX = new EnumMap<>(AllDyes.class);
    public static final EnumMap<AllDyes, Block> BED = new EnumMap<>(AllDyes.class);

    static {
        FROGLIGHT.put(AllDyes.WHITE, BlockRegistry.CLEAR_FROGLIGHT);
        FROGLIGHT.put(AllDyes.ORANGE, BlockRegistry.MANDARIN_FROGLIGHT);
        FROGLIGHT.put(AllDyes.MAGENTA, BlockRegistry.THULIAN_FROGLIGHT);
        FROGLIGHT.put(AllDyes.LIGHT_BLUE, BlockRegistry.CERULEAN_FROGLIGHT);
        FROGLIGHT.put(AllDyes.YELLOW, BlockRegistry.LEMON_FROGLIGHT);
        FROGLIGHT.put(AllDyes.LIME, BlockRegistry.KIWI_FROGLIGHT);
        FROGLIGHT.put(AllDyes.PINK, BlockRegistry.SAKURA_FROGLIGHT);
        FROGLIGHT.put(AllDyes.GRAY, BlockRegistry.CASCADING_FROGLIGHT);
        FROGLIGHT.put(AllDyes.LIGHT_GRAY, BlockRegistry.CLOUDY_FROGLIGHT);
        FROGLIGHT.put(AllDyes.CYAN, BlockRegistry.TEAL_FROGLIGHT);
        FROGLIGHT.put(AllDyes.PURPLE, Blocks.PEARLESCENT_FROGLIGHT);
        FROGLIGHT.put(AllDyes.BLUE, BlockRegistry.NAVY_FROGLIGHT);
        FROGLIGHT.put(AllDyes.BROWN, BlockRegistry.CHAMOISEE_FROGLIGHT);
        FROGLIGHT.put(AllDyes.GREEN, Blocks.VERDANT_FROGLIGHT);
        FROGLIGHT.put(AllDyes.RED, BlockRegistry.VERMILION_FROGLIGHT);
        FROGLIGHT.put(AllDyes.BLACK, BlockRegistry.CLOUDBURST_FROGLIGHT);
        FROGLIGHT.put(AllDyes.AMBER, Blocks.OCHRE_FROGLIGHT);
        FROGLIGHT.put(AllDyes.AQUA, BlockRegistry.SEAFOAM_FROGLIGHT);
        FROGLIGHT.put(AllDyes.INDIGO, BlockRegistry.LAVENDER_FROGLIGHT);
        FROGLIGHT.put(AllDyes.MAROON, BlockRegistry.SANGUINE_FROGLIGHT);
    }
}