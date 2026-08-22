package net.greenjab.nekomasfixed.registry.registries;

import net.greenjab.nekomasfixed.NekomasFixed;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import java.util.function.BiFunction;

public class ItemRegistry {

    public static final Item GLOW_TORCH = register(
        BlockRegistry.GLOW_TORCH,
        (block, settings) -> new StandingAndWallBlockItem(block, BlockRegistry.GLOW_WALL_TORCH, settings, Direction.DOWN),
        new Item.Properties());
    public static final Item BAOBAB_LOG = register(BlockRegistry.BAOBAB_LOG);
    public static final Item BAOBAB_WOOD = register(BlockRegistry.BAOBAB_WOOD);
    public static final Item STRIPPED_BAOBAB_LOG = register(BlockRegistry.STRIPPED_BAOBAB_LOG);
    public static final Item STRIPPED_BAOBAB_WOOD = register(BlockRegistry.STRIPPED_BAOBAB_WOOD);
    public static final Item BAOBAB_PLANKS = register(BlockRegistry.BAOBAB_PLANKS);
    public static final Item BAOBAB_STAIRS = register(BlockRegistry.BAOBAB_STAIRS);
    public static final Item BAOBAB_SLAB = register(BlockRegistry.BAOBAB_SLAB);
    public static final Item BAOBAB_FENCE = register(BlockRegistry.BAOBAB_FENCE);
    public static final Item BAOBAB_FENCE_GATE = register(BlockRegistry.BAOBAB_FENCE_GATE);
    public static final Item BAOBAB_DOOR = register(BlockRegistry.BAOBAB_DOOR);
    public static final Item BAOBAB_TRAPDOOR = register(BlockRegistry.BAOBAB_TRAPDOOR);
    public static final Item BAOBAB_PRESSURE_PLATE = register(BlockRegistry.BAOBAB_PRESSURE_PLATE);
    public static final Item BAOBAB_BUTTON = register(BlockRegistry.BAOBAB_BUTTON);
    public static final Item BAOBAB_SIGN = register(
        BlockRegistry.BAOBAB_SIGN,
        (block, settings) -> new SignItem(settings, block, BlockRegistry.BAOBAB_WALL_SIGN),
        new Item.Properties().stacksTo(16));
    public static final Item BAOBAB_HANGING_SIGN = register(
        BlockRegistry.BAOBAB_HANGING_SIGN,
        (block, settings) -> new HangingSignItem(block, BlockRegistry.BAOBAB_WALL_HANGING_SIGN, settings),
        new Item.Properties().stacksTo(16));
    public static final Item BAOBAB_LEAVES = register(BlockRegistry.BAOBAB_LEAVES);

    private static Item register(Block block) {
        return Registry.register(BuiltInRegistries.ITEM,
            ResourceKey.create(Registries.ITEM, blockKeyOf(block)),
            new BlockItem(block, new Item.Properties()));
    }

    private static ResourceLocation blockKeyOf(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private static Item register(Block block, BiFunction<Block, Item.Properties, Item> factory, Item.Properties settings) {
        return Registry.register(BuiltInRegistries.ITEM,
            ResourceKey.create(Registries.ITEM, blockKeyOf(block)),
            factory.apply(block, settings));
    }

    public static void registerItems() {
        NekomasFixed.LOGGER.info("Registering items");
    }
}
