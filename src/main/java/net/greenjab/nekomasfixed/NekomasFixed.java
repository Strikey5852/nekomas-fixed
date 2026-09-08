package net.greenjab.nekomasfixed;

import net.fabricmc.api.ModInitializer;
import net.greenjab.nekomasfixed.registry.registries.*;
import net.greenjab.nekomasfixed.registry.worldgen.ModWorldGeneration;
import net.greenjab.nekomasfixed.util.ModTreeDecorators;
import net.greenjab.nekomasfixed.util.ModTrunkPlacers;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NekomasFixed implements ModInitializer {
    public static final String MOD_NAME = "Nekomas' Fixed Minecraft";
    public static final String NAMESPACE = "nekomasfixed";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAMESPACE);

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(NAMESPACE, path);
    }

    @Override
    public void onInitialize() {
        LOGGER.info("[{}] loaded (1.21.1 port, scaffold)", MOD_NAME);
        ComponentRegistry.registerComponents();
        ModTreeDecorators.register();
        ModTrunkPlacers.register();
        BlockRegistry.registerBlocks();
        ItemRegistry.registerItems();
        EntityTypeRegistry.registerEntityType();
        BlockEntityTypeRegistry.registerBlockEntityTypes();
        RecipeRegistry.registerRecipes();
        ItemGroupRegistry.registerItemGroup();
        BlockEntityRegistry.attachBlockEntities();
        ModWorldGeneration.generateModWorldGen();
    }
}
