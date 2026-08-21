package net.greenjab.nekomasfixed.registry.registries;

import net.greenjab.nekomasfixed.NekomasFixed;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;

import java.util.function.Function;

public class ItemRegistry {

    public static final Item GLOW_TORCH = register(
        "glow_torch",
        new Item.Properties(),
        settings -> new StandingAndWallBlockItem(
            BlockRegistry.GLOW_TORCH, BlockRegistry.GLOW_WALL_TORCH, settings, Direction.DOWN));

    private static Item register(String id, Item.Properties settings, Function<Item.Properties, Item> factory) {
        return Registry.register(BuiltInRegistries.ITEM,
            ResourceKey.create(Registries.ITEM, NekomasFixed.id(id)),
            factory.apply(settings));
    }

    public static void registerItems() {
        NekomasFixed.LOGGER.info("Registered item: {}", GLOW_TORCH);
    }
}
