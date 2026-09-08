package net.greenjab.nekomasfixed.registry.registries;

import net.greenjab.nekomasfixed.NekomasFixed;
import net.greenjab.nekomasfixed.recipe.RecolourRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class RecipeRegistry {

    public static final RecipeSerializer<RecolourRecipe> RECOLOUR = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            NekomasFixed.id("recolour"),
            new RecolourRecipe.Serializer());

    public static void registerRecipes() {
        NekomasFixed.LOGGER.info("Registering recipes");
    }
}