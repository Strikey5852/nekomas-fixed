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
                        entries.accept(ItemRegistry.BAOBAB_LEAVES);
                        entries.accept(ItemRegistry.ROPE);
                        entries.accept(ItemRegistry.BAOBAB_FRUIT);
                        entries.accept(ItemRegistry.BAOBAB_SAPLING);
                        entries.accept(ItemRegistry.BAOBAB_SEEDS);
                        entries.accept(ItemRegistry.BAOBAB_BOAT);
                        entries.accept(ItemRegistry.BAOBAB_CHEST_BOAT);
                        entries.accept(ItemRegistry.CLAM);
                        entries.accept(ItemRegistry.CLAM_BLUE);
                        entries.accept(ItemRegistry.CLAM_PINK);
                        entries.accept(ItemRegistry.CLAM_PURPLE);
                        entries.accept(ItemRegistry.PEARL);
                        entries.accept(ItemRegistry.PEARL_BLOCK);
                        entries.accept(ItemRegistry.GEYSER);
                        entries.accept(ItemRegistry.GLISTERING_MELON);
                        entries.accept(ItemRegistry.SWEETBERRY_CAKE);
                        entries.accept(ItemRegistry.PAN_CAKE);
                        entries.accept(ItemRegistry.GLOWBERRY_CAKE);
                        entries.accept(ItemRegistry.APPLE_CAKE);
                        entries.accept(ItemRegistry.VANILLA_CAKE);
                        entries.accept(ItemRegistry.COOKIE_CAKE);
                        entries.accept(ItemRegistry.CHOCOLATE_CAKE);
                        entries.accept(ItemRegistry.BEETROOT_CAKE);
                        entries.accept(ItemRegistry.ENDERMAN_HEAD);
                        entries.accept(ItemRegistry.HOLLOW_OAK_LOG);
                        entries.accept(ItemRegistry.HOLLOW_SPRUCE_LOG);
                        entries.accept(ItemRegistry.HOLLOW_BIRCH_LOG);
                        entries.accept(ItemRegistry.HOLLOW_JUNGLE_LOG);
                        entries.accept(ItemRegistry.HOLLOW_ACACIA_LOG);
                        entries.accept(ItemRegistry.HOLLOW_DARK_OAK_LOG);
                        entries.accept(ItemRegistry.HOLLOW_MANGROVE_LOG);
                        entries.accept(ItemRegistry.HOLLOW_CHERRY_LOG);
                        entries.accept(ItemRegistry.HOLLOW_BAMBOO_BLOCK);
                        entries.accept(ItemRegistry.HOLLOW_CRIMSON_STEM);
                        entries.accept(ItemRegistry.HOLLOW_WARPED_STEM);
                        entries.accept(ItemRegistry.HOLLOW_BAOBAB_LOG);
                    })
                    .build()
    );

    public static final ResourceKey<CreativeModeTab> NEKOMASFIXEDCOLOURS_KEY =
            ResourceKey.create(net.minecraft.core.registries.Registries.CREATIVE_MODE_TAB,
                    NekomasFixed.id("nekomasfixedcolours"));

    public static final CreativeModeTab NEKOMASFIXEDCOLOURS = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            NEKOMASFIXEDCOLOURS_KEY,
            FabricItemGroup.builder()
                    .title(Component.translatable("itemgroup.nekomasfixedcolours"))
                    .icon(() -> new ItemStack(ItemRegistry.AMBER_DYE))
                    .displayItems((parameters, entries) -> {
                        entries.accept(ItemRegistry.AMBER_DYE);
                        entries.accept(ItemRegistry.AQUA_DYE);
                        entries.accept(ItemRegistry.INDIGO_DYE);
                        entries.accept(ItemRegistry.MAROON_DYE);
                        entries.accept(ItemRegistry.CLEAR_FROGLIGHT);
                        entries.accept(ItemRegistry.CLOUDY_FROGLIGHT);
                        entries.accept(ItemRegistry.CASCADING_FROGLIGHT);
                        entries.accept(ItemRegistry.CLOUDBURST_FROGLIGHT);
                        entries.accept(ItemRegistry.CHAMOISEE_FROGLIGHT);
                        entries.accept(ItemRegistry.SANGUINE_FROGLIGHT);
                        entries.accept(ItemRegistry.VERMILION_FROGLIGHT);
                        entries.accept(ItemRegistry.MANDARIN_FROGLIGHT);
                        entries.accept(ItemRegistry.LEMON_FROGLIGHT);
                        entries.accept(ItemRegistry.KIWI_FROGLIGHT);
                        entries.accept(ItemRegistry.SEAFOAM_FROGLIGHT);
                        entries.accept(ItemRegistry.TEAL_FROGLIGHT);
                        entries.accept(ItemRegistry.CERULEAN_FROGLIGHT);
                        entries.accept(ItemRegistry.NAVY_FROGLIGHT);
                        entries.accept(ItemRegistry.LAVENDER_FROGLIGHT);
                        entries.accept(ItemRegistry.THULIAN_FROGLIGHT);
                        entries.accept(ItemRegistry.SAKURA_FROGLIGHT);
                        entries.accept(ItemRegistry.WHITE_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.LIGHT_GRAY_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.GRAY_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.BLACK_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.BROWN_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.RED_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.ORANGE_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.YELLOW_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.LIME_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.GREEN_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.CYAN_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.LIGHT_BLUE_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.BLUE_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.PURPLE_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.MAGENTA_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.PINK_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.AMBER_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.AQUA_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.INDIGO_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.MAROON_SPOTTED_WOOL);
                        entries.accept(ItemRegistry.WHITE_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.LIGHT_GRAY_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.GRAY_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.BLACK_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.BROWN_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.RED_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.ORANGE_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.YELLOW_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.LIME_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.GREEN_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.CYAN_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.LIGHT_BLUE_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.BLUE_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.PURPLE_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.MAGENTA_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.PINK_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.AMBER_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.AQUA_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.INDIGO_SPOTTED_CARPET);
                        entries.accept(ItemRegistry.MAROON_SPOTTED_CARPET);
                    })
                    .build()
    );

    public static void registerItemGroup() {
        NekomasFixed.LOGGER.info("Registered item groups: {}, {}", NEKOMASFIXED, NEKOMASFIXEDCOLOURS);
    }
}
