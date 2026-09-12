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

        SPOTTED_WOOL.put(AllDyes.WHITE, BlockRegistry.WHITE_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.ORANGE, BlockRegistry.ORANGE_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.MAGENTA, BlockRegistry.MAGENTA_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.LIGHT_BLUE, BlockRegistry.LIGHT_BLUE_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.YELLOW, BlockRegistry.YELLOW_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.LIME, BlockRegistry.LIME_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.PINK, BlockRegistry.PINK_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.GRAY, BlockRegistry.GRAY_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.LIGHT_GRAY, BlockRegistry.LIGHT_GRAY_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.CYAN, BlockRegistry.CYAN_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.PURPLE, BlockRegistry.PURPLE_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.BLUE, BlockRegistry.BLUE_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.BROWN, BlockRegistry.BROWN_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.GREEN, BlockRegistry.GREEN_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.RED, BlockRegistry.RED_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.BLACK, BlockRegistry.BLACK_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.AMBER, BlockRegistry.AMBER_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.AQUA, BlockRegistry.AQUA_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.INDIGO, BlockRegistry.INDIGO_SPOTTED_WOOL);
        SPOTTED_WOOL.put(AllDyes.MAROON, BlockRegistry.MAROON_SPOTTED_WOOL);

        SPOTTED_CARPET.put(AllDyes.WHITE, BlockRegistry.WHITE_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.ORANGE, BlockRegistry.ORANGE_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.MAGENTA, BlockRegistry.MAGENTA_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.LIGHT_BLUE, BlockRegistry.LIGHT_BLUE_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.YELLOW, BlockRegistry.YELLOW_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.LIME, BlockRegistry.LIME_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.PINK, BlockRegistry.PINK_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.GRAY, BlockRegistry.GRAY_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.LIGHT_GRAY, BlockRegistry.LIGHT_GRAY_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.CYAN, BlockRegistry.CYAN_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.PURPLE, BlockRegistry.PURPLE_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.BLUE, BlockRegistry.BLUE_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.BROWN, BlockRegistry.BROWN_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.GREEN, BlockRegistry.GREEN_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.RED, BlockRegistry.RED_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.BLACK, BlockRegistry.BLACK_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.AMBER, BlockRegistry.AMBER_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.AQUA, BlockRegistry.AQUA_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.INDIGO, BlockRegistry.INDIGO_SPOTTED_CARPET);
        SPOTTED_CARPET.put(AllDyes.MAROON, BlockRegistry.MAROON_SPOTTED_CARPET);
        WOOL.put(AllDyes.AMBER, BlockRegistry.AMBER_WOOL);
        WOOL.put(AllDyes.AQUA, BlockRegistry.AQUA_WOOL);
        WOOL.put(AllDyes.INDIGO, BlockRegistry.INDIGO_WOOL);
        WOOL.put(AllDyes.MAROON, BlockRegistry.MAROON_WOOL);

        CARPET.put(AllDyes.AMBER, BlockRegistry.AMBER_CARPET);
        CARPET.put(AllDyes.AQUA, BlockRegistry.AQUA_CARPET);
        CARPET.put(AllDyes.INDIGO, BlockRegistry.INDIGO_CARPET);
        CARPET.put(AllDyes.MAROON, BlockRegistry.MAROON_CARPET);

        STAINED_GLASS.put(AllDyes.AMBER, BlockRegistry.AMBER_STAINED_GLASS);
        STAINED_GLASS.put(AllDyes.AQUA, BlockRegistry.AQUA_STAINED_GLASS);
        STAINED_GLASS.put(AllDyes.INDIGO, BlockRegistry.INDIGO_STAINED_GLASS);
        STAINED_GLASS.put(AllDyes.MAROON, BlockRegistry.MAROON_STAINED_GLASS);

        STAINED_GLASS_PANE.put(AllDyes.AMBER, BlockRegistry.AMBER_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE.put(AllDyes.AQUA, BlockRegistry.AQUA_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE.put(AllDyes.INDIGO, BlockRegistry.INDIGO_STAINED_GLASS_PANE);
        STAINED_GLASS_PANE.put(AllDyes.MAROON, BlockRegistry.MAROON_STAINED_GLASS_PANE);

        CANDLE.put(AllDyes.AMBER, BlockRegistry.AMBER_CANDLE);
        CANDLE.put(AllDyes.AQUA, BlockRegistry.AQUA_CANDLE);
        CANDLE.put(AllDyes.INDIGO, BlockRegistry.INDIGO_CANDLE);
        CANDLE.put(AllDyes.MAROON, BlockRegistry.MAROON_CANDLE);

        BED.put(AllDyes.AMBER, BlockRegistry.AMBER_BED);
        BED.put(AllDyes.AQUA, BlockRegistry.AQUA_BED);
        BED.put(AllDyes.INDIGO, BlockRegistry.INDIGO_BED);
        BED.put(AllDyes.MAROON, BlockRegistry.MAROON_BED);

        SHULKER_BOX.put(AllDyes.AMBER, BlockRegistry.AMBER_SHULKER_BOX);
        SHULKER_BOX.put(AllDyes.AQUA, BlockRegistry.AQUA_SHULKER_BOX);
        SHULKER_BOX.put(AllDyes.INDIGO, BlockRegistry.INDIGO_SHULKER_BOX);
        SHULKER_BOX.put(AllDyes.MAROON, BlockRegistry.MAROON_SHULKER_BOX);

        BRICKS.put(AllDyes.WHITE, BlockRegistry.WHITE_BRICKS);
        BRICKS.put(AllDyes.ORANGE, BlockRegistry.ORANGE_BRICKS);
        BRICKS.put(AllDyes.MAGENTA, BlockRegistry.MAGENTA_BRICKS);
        BRICKS.put(AllDyes.LIGHT_BLUE, BlockRegistry.LIGHT_BLUE_BRICKS);
        BRICKS.put(AllDyes.YELLOW, BlockRegistry.YELLOW_BRICKS);
        BRICKS.put(AllDyes.LIME, BlockRegistry.LIME_BRICKS);
        BRICKS.put(AllDyes.PINK, BlockRegistry.PINK_BRICKS);
        BRICKS.put(AllDyes.GRAY, BlockRegistry.GRAY_BRICKS);
        BRICKS.put(AllDyes.LIGHT_GRAY, BlockRegistry.LIGHT_GRAY_BRICKS);
        BRICKS.put(AllDyes.CYAN, BlockRegistry.CYAN_BRICKS);
        BRICKS.put(AllDyes.PURPLE, BlockRegistry.PURPLE_BRICKS);
        BRICKS.put(AllDyes.BLUE, BlockRegistry.BLUE_BRICKS);
        BRICKS.put(AllDyes.BROWN, BlockRegistry.BROWN_BRICKS);
        BRICKS.put(AllDyes.GREEN, BlockRegistry.GREEN_BRICKS);
        BRICKS.put(AllDyes.RED, BlockRegistry.RED_BRICKS);
        BRICKS.put(AllDyes.BLACK, BlockRegistry.BLACK_BRICKS);
        BRICKS.put(AllDyes.AMBER, BlockRegistry.AMBER_BRICKS);
        BRICKS.put(AllDyes.AQUA, BlockRegistry.AQUA_BRICKS);
        BRICKS.put(AllDyes.INDIGO, BlockRegistry.INDIGO_BRICKS);
        BRICKS.put(AllDyes.MAROON, BlockRegistry.MAROON_BRICKS);

        BRICK_SLAB.put(AllDyes.WHITE, BlockRegistry.WHITE_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.ORANGE, BlockRegistry.ORANGE_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.MAGENTA, BlockRegistry.MAGENTA_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.LIGHT_BLUE, BlockRegistry.LIGHT_BLUE_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.YELLOW, BlockRegistry.YELLOW_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.LIME, BlockRegistry.LIME_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.PINK, BlockRegistry.PINK_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.GRAY, BlockRegistry.GRAY_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.LIGHT_GRAY, BlockRegistry.LIGHT_GRAY_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.CYAN, BlockRegistry.CYAN_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.PURPLE, BlockRegistry.PURPLE_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.BLUE, BlockRegistry.BLUE_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.BROWN, BlockRegistry.BROWN_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.GREEN, BlockRegistry.GREEN_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.RED, BlockRegistry.RED_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.BLACK, BlockRegistry.BLACK_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.AMBER, BlockRegistry.AMBER_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.AQUA, BlockRegistry.AQUA_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.INDIGO, BlockRegistry.INDIGO_BRICK_SLAB);
        BRICK_SLAB.put(AllDyes.MAROON, BlockRegistry.MAROON_BRICK_SLAB);

        BRICK_STAIRS.put(AllDyes.WHITE, BlockRegistry.WHITE_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.ORANGE, BlockRegistry.ORANGE_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.MAGENTA, BlockRegistry.MAGENTA_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.LIGHT_BLUE, BlockRegistry.LIGHT_BLUE_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.YELLOW, BlockRegistry.YELLOW_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.LIME, BlockRegistry.LIME_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.PINK, BlockRegistry.PINK_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.GRAY, BlockRegistry.GRAY_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.LIGHT_GRAY, BlockRegistry.LIGHT_GRAY_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.CYAN, BlockRegistry.CYAN_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.PURPLE, BlockRegistry.PURPLE_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.BLUE, BlockRegistry.BLUE_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.BROWN, BlockRegistry.BROWN_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.GREEN, BlockRegistry.GREEN_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.RED, BlockRegistry.RED_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.BLACK, BlockRegistry.BLACK_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.AMBER, BlockRegistry.AMBER_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.AQUA, BlockRegistry.AQUA_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.INDIGO, BlockRegistry.INDIGO_BRICK_STAIRS);
        BRICK_STAIRS.put(AllDyes.MAROON, BlockRegistry.MAROON_BRICK_STAIRS);

        BRICK_WALL.put(AllDyes.WHITE, BlockRegistry.WHITE_BRICK_WALL);
        BRICK_WALL.put(AllDyes.ORANGE, BlockRegistry.ORANGE_BRICK_WALL);
        BRICK_WALL.put(AllDyes.MAGENTA, BlockRegistry.MAGENTA_BRICK_WALL);
        BRICK_WALL.put(AllDyes.LIGHT_BLUE, BlockRegistry.LIGHT_BLUE_BRICK_WALL);
        BRICK_WALL.put(AllDyes.YELLOW, BlockRegistry.YELLOW_BRICK_WALL);
        BRICK_WALL.put(AllDyes.LIME, BlockRegistry.LIME_BRICK_WALL);
        BRICK_WALL.put(AllDyes.PINK, BlockRegistry.PINK_BRICK_WALL);
        BRICK_WALL.put(AllDyes.GRAY, BlockRegistry.GRAY_BRICK_WALL);
        BRICK_WALL.put(AllDyes.LIGHT_GRAY, BlockRegistry.LIGHT_GRAY_BRICK_WALL);
        BRICK_WALL.put(AllDyes.CYAN, BlockRegistry.CYAN_BRICK_WALL);
        BRICK_WALL.put(AllDyes.PURPLE, BlockRegistry.PURPLE_BRICK_WALL);
        BRICK_WALL.put(AllDyes.BLUE, BlockRegistry.BLUE_BRICK_WALL);
        BRICK_WALL.put(AllDyes.BROWN, BlockRegistry.BROWN_BRICK_WALL);
        BRICK_WALL.put(AllDyes.GREEN, BlockRegistry.GREEN_BRICK_WALL);
        BRICK_WALL.put(AllDyes.RED, BlockRegistry.RED_BRICK_WALL);
        BRICK_WALL.put(AllDyes.BLACK, BlockRegistry.BLACK_BRICK_WALL);
        BRICK_WALL.put(AllDyes.AMBER, BlockRegistry.AMBER_BRICK_WALL);
        BRICK_WALL.put(AllDyes.AQUA, BlockRegistry.AQUA_BRICK_WALL);
        BRICK_WALL.put(AllDyes.INDIGO, BlockRegistry.INDIGO_BRICK_WALL);
        BRICK_WALL.put(AllDyes.MAROON, BlockRegistry.MAROON_BRICK_WALL);
    }
}