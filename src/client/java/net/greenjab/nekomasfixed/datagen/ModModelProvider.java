package net.greenjab.nekomasfixed.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.greenjab.nekomasfixed.registry.registries.BlockRegistry;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;

/**
 * Generates blockstates + models for the colour-suite blocks.
 * Phase 1 covers the 20-colour spotted wool (cube_all) and spotted carpet
 * (carpet parent), reproduced from the same source fields used to hand-generate
 * the committed JSONs so the diff against the settled output is byte-identical.
 */
public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    private static Block[] spottedWoolBlocks() {
        return new Block[]{
                BlockRegistry.WHITE_SPOTTED_WOOL,
                BlockRegistry.LIGHT_GRAY_SPOTTED_WOOL,
                BlockRegistry.GRAY_SPOTTED_WOOL,
                BlockRegistry.BLACK_SPOTTED_WOOL,
                BlockRegistry.BROWN_SPOTTED_WOOL,
                BlockRegistry.RED_SPOTTED_WOOL,
                BlockRegistry.ORANGE_SPOTTED_WOOL,
                BlockRegistry.YELLOW_SPOTTED_WOOL,
                BlockRegistry.LIME_SPOTTED_WOOL,
                BlockRegistry.GREEN_SPOTTED_WOOL,
                BlockRegistry.CYAN_SPOTTED_WOOL,
                BlockRegistry.LIGHT_BLUE_SPOTTED_WOOL,
                BlockRegistry.BLUE_SPOTTED_WOOL,
                BlockRegistry.PURPLE_SPOTTED_WOOL,
                BlockRegistry.MAGENTA_SPOTTED_WOOL,
                BlockRegistry.PINK_SPOTTED_WOOL,
                BlockRegistry.AMBER_SPOTTED_WOOL,
                BlockRegistry.AQUA_SPOTTED_WOOL,
                BlockRegistry.INDIGO_SPOTTED_WOOL,
                BlockRegistry.MAROON_SPOTTED_WOOL,
        };
    }

    private static Block[] spottedCarpetBlocks() {
        return new Block[]{
                BlockRegistry.WHITE_SPOTTED_CARPET,
                BlockRegistry.LIGHT_GRAY_SPOTTED_CARPET,
                BlockRegistry.GRAY_SPOTTED_CARPET,
                BlockRegistry.BLACK_SPOTTED_CARPET,
                BlockRegistry.BROWN_SPOTTED_CARPET,
                BlockRegistry.RED_SPOTTED_CARPET,
                BlockRegistry.ORANGE_SPOTTED_CARPET,
                BlockRegistry.YELLOW_SPOTTED_CARPET,
                BlockRegistry.LIME_SPOTTED_CARPET,
                BlockRegistry.GREEN_SPOTTED_CARPET,
                BlockRegistry.CYAN_SPOTTED_CARPET,
                BlockRegistry.LIGHT_BLUE_SPOTTED_CARPET,
                BlockRegistry.BLUE_SPOTTED_CARPET,
                BlockRegistry.PURPLE_SPOTTED_CARPET,
                BlockRegistry.MAGENTA_SPOTTED_CARPET,
                BlockRegistry.PINK_SPOTTED_CARPET,
                BlockRegistry.AMBER_SPOTTED_CARPET,
                BlockRegistry.AQUA_SPOTTED_CARPET,
                BlockRegistry.INDIGO_SPOTTED_CARPET,
                BlockRegistry.MAROON_SPOTTED_CARPET,
        };
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {
        Block[] wools = spottedWoolBlocks();
        Block[] carpets = spottedCarpetBlocks();
        for (int i = 0; i < wools.length; i++) {
            Block wool = wools[i];
            // cube_all with "all" -> block/{id}_spotted_wool
            generator.createTrivialCube(wool);
            // carpet parent reuses the SAME wool texture for the "wool" slot
            // (only the *_spotted_wool.png textures exist), so the default
            // CARPET mapping (which points at block/{id}_spotted_carpet) is overridden.
            generator.createTrivialBlock(carpets[i],
                    TexturedModel.CARPET.updateTexture(mapping ->
                            mapping.put(TextureSlot.WOOL, TextureMapping.wool(wool).get(TextureSlot.WOOL))));
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        // Block items parent to their block model automatically (handled by the
        // ModelProviderMixin filter); nothing extra needed for these blocks.
    }
}