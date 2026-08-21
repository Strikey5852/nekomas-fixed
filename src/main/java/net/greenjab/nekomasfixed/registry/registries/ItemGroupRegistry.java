package net.greenjab.nekomasfixed.registry.registries;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.greenjab.nekomasfixed.NekomasFixed;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemGroupRegistry {

    public static final ResourceKey<CreativeModeTab> NEKOMASFIXED_KEY =
        ResourceKey.create(net.minecraft.core.registries.Registries.CREATIVE_MODE_TAB,
            NekomasFixed.id("nekomasfixed"));

    public static final CreativeModeTab NEKOMASFIXED = Registry.register(
        BuiltInRegistries.CREATIVE_MODE_TAB,
        NEKOMASFIXED_KEY,
        FabricItemGroup.builder()
            .title(Component.translatable("itemgroup.nekomasfixed"))
            .icon(() -> new ItemStack(ItemRegistry.GLOW_TORCH))
            .displayItems((parameters, entries) -> {
                entries.accept(ItemRegistry.GLOW_TORCH);
                entries.accept(ItemRegistry.BAOBAB_LOG);
                entries.accept(ItemRegistry.BAOBAB_WOOD);
                entries.accept(ItemRegistry.STRIPPED_BAOBAB_LOG);
                entries.accept(ItemRegistry.STRIPPED_BAOBAB_WOOD);
                entries.accept(ItemRegistry.BAOBAB_PLANKS);
                entries.accept(ItemRegistry.BAOBAB_STAIRS);
                entries.accept(ItemRegistry.BAOBAB_SLAB);
                entries.accept(ItemRegistry.BAOBAB_FENCE);
                entries.accept(ItemRegistry.BAOBAB_FENCE_GATE);
                entries.accept(ItemRegistry.BAOBAB_DOOR);
                entries.accept(ItemRegistry.BAOBAB_TRAPDOOR);
                entries.accept(ItemRegistry.BAOBAB_PRESSURE_PLATE);
                entries.accept(ItemRegistry.BAOBAB_BUTTON);
                entries.accept(ItemRegistry.BAOBAB_SIGN);
                entries.accept(ItemRegistry.BAOBAB_HANGING_SIGN);
            })
            .build()
    );

    public static void registerItemGroup() {
        NekomasFixed.LOGGER.info("Registered item group: {}", NEKOMASFIXED);
    }
}
