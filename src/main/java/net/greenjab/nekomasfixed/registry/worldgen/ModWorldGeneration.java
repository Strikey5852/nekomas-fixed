package net.greenjab.nekomasfixed.registry.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModWorldGeneration {

    public static void generateModWorldGen() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.WINDSWEPT_SAVANNA),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ModPlacedFeatures.BAOBAB_PLACED_KEY);
    }
}
