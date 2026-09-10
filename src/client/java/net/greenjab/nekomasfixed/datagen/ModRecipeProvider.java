package net.greenjab.nekomasfixed.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.greenjab.nekomasfixed.NekomasFixed;
import net.greenjab.nekomasfixed.registry.registries.ItemRegistry;
import net.greenjab.nekomasfixed.util.AllDyes;
import net.greenjab.nekomasfixed.util.BlockDyeMap;
import net.greenjab.nekomasfixed.util.ModTags;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

/**
 * Generates the colour-suite recipes + their unlock advancements.
 * Bricks use vanilla shaped/stonecutting builders (main's brick recipes are a
 * plain-to-coloured craft, not a recolour). Spotted wool/carpet + froglights
 * use the mod's custom `nekomasfixed:recolour` type (RecolourRecipeBuilder).
 */
public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    private static Item dyeItem(AllDyes dye) {
        return switch (dye) {
            case WHITE -> Items.WHITE_DYE;
            case ORANGE -> Items.ORANGE_DYE;
            case MAGENTA -> Items.MAGENTA_DYE;
            case LIGHT_BLUE -> Items.LIGHT_BLUE_DYE;
            case YELLOW -> Items.YELLOW_DYE;
            case LIME -> Items.LIME_DYE;
            case PINK -> Items.PINK_DYE;
            case GRAY -> Items.GRAY_DYE;
            case LIGHT_GRAY -> Items.LIGHT_GRAY_DYE;
            case CYAN -> Items.CYAN_DYE;
            case PURPLE -> Items.PURPLE_DYE;
            case BLUE -> Items.BLUE_DYE;
            case BROWN -> Items.BROWN_DYE;
            case GREEN -> Items.GREEN_DYE;
            case RED -> Items.RED_DYE;
            case BLACK -> Items.BLACK_DYE;
            case AMBER -> ItemRegistry.AMBER_DYE;
            case AQUA -> ItemRegistry.AQUA_DYE;
            case INDIGO -> ItemRegistry.INDIGO_DYE;
            case MAROON -> ItemRegistry.MAROON_DYE;
        };
    }

    private void generateBaobabRecipes(RecipeOutput output) {
        // Baobab wood-family recipes (vanilla-shaped builders).
        Item planks = ItemRegistry.BAOBAB_PLANKS;
        Item log = ItemRegistry.BAOBAB_LOG;

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks, 4)
                .requires(ModTags.BAOBAB_LOGS_ITEM).group("planks")
                .unlockedBy("has_log", has(log))
                .save(output, NekomasFixed.id("baobab_planks"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ItemRegistry.BAOBAB_WOOD, 3)
                .pattern("##").pattern("##").define('#', log)
                .group("bark").unlockedBy("has_log", has(log))
                .save(output, NekomasFixed.id("baobab_wood"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ItemRegistry.STRIPPED_BAOBAB_WOOD, 3)
                .pattern("##").pattern("##").define('#', ItemRegistry.STRIPPED_BAOBAB_LOG)
                .group("bark").unlockedBy("has_log", has(ItemRegistry.STRIPPED_BAOBAB_LOG))
                .save(output, NekomasFixed.id("stripped_baobab_wood"));

        baobabSimple(output);
    }

    private void baobabSimple(RecipeOutput output) {
        Item planks = ItemRegistry.BAOBAB_PLANKS;
        Item log = ItemRegistry.BAOBAB_LOG;
        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, ItemRegistry.BAOBAB_BUTTON)
                .requires(planks).group("wooden_button")
                .unlockedBy("has_planks", has(planks))
                .save(output, NekomasFixed.id("baobab_button"));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ItemRegistry.BAOBAB_DOOR, 3)
                .pattern("##").pattern("##").pattern("##").define('#', planks)
                .group("door").unlockedBy("has_planks", has(planks))
                .save(output, NekomasFixed.id("baobab_door"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ItemRegistry.BAOBAB_FENCE, 3)
                .pattern("W#W").pattern("W#W").define('#', Items.STICK).define('W', planks)
                .unlockedBy("has_planks", has(planks))
                .save(output, NekomasFixed.id("baobab_fence"));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ItemRegistry.BAOBAB_FENCE_GATE)
                .pattern("#W#").pattern("#W#").define('#', Items.STICK).define('W', planks)
                .group("fence_gate").unlockedBy("has_planks", has(planks))
                .save(output, NekomasFixed.id("baobab_fence_gate"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ItemRegistry.BAOBAB_SLAB, 6)
                .pattern("###").define('#', planks).group("wooden_slab")
                .unlockedBy("has_planks", has(planks))
                .save(output, NekomasFixed.id("baobab_slab"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ItemRegistry.BAOBAB_STAIRS, 4)
                .pattern("#  ").pattern("## ").pattern("###").define('#', planks).group("wooden_stairs")
                .unlockedBy("has_planks", has(planks))
                .save(output, NekomasFixed.id("baobab_stairs"));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ItemRegistry.BAOBAB_TRAPDOOR, 2)
                .pattern("###").pattern("###").define('#', planks).group("wooden_trapdoor")
                .unlockedBy("has_planks", has(planks))
                .save(output, NekomasFixed.id("baobab_trapdoor"));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ItemRegistry.BAOBAB_PRESSURE_PLATE)
                .pattern("##").define('#', planks).group("wooden_pressure_plate")
                .unlockedBy("has_planks", has(planks))
                .save(output, NekomasFixed.id("baobab_pressure_plate"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ItemRegistry.BAOBAB_SIGN, 3)
                .pattern("###").pattern("###").pattern(" X ").define('#', planks).define('X', Items.STICK)
                .group("sign").unlockedBy("has_planks", has(planks))
                .save(output, NekomasFixed.id("baobab_sign"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ItemRegistry.BAOBAB_HANGING_SIGN, 6)
                .pattern("X X").pattern("###").pattern("###").define('#', log).define('X', Items.CHAIN)
                .group("hanging_sign").unlockedBy("has_log", has(log))
                .save(output, NekomasFixed.id("baobab_hanging_sign"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ItemRegistry.BAOBAB_BOAT)
                .pattern("# #").pattern("###").define('#', planks).group("boat")
                .unlockedBy("has_planks", has(planks))
                .save(output, NekomasFixed.id("baobab_boat"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ItemRegistry.BAOBAB_CHEST_BOAT)
                .requires(Items.CHEST).requires(planks).group("chest_boat")
                .unlockedBy("has_planks", has(planks))
                .save(output, NekomasFixed.id("baobab_chest_boat"));
    }

    private void generateHollowLogRecipes(RecipeOutput output) {
        // Each hollow log/stem/block crafts back into that wood's planks.
        // Vanilla woods give vanilla planks; baobab gives the mod's baobab planks.
        record Pair(Item hollow, Item planks) {
        }
        Pair[] pairs = {
                new Pair(ItemRegistry.HOLLOW_OAK_LOG, Items.OAK_PLANKS),
                new Pair(ItemRegistry.HOLLOW_SPRUCE_LOG, Items.SPRUCE_PLANKS),
                new Pair(ItemRegistry.HOLLOW_BIRCH_LOG, Items.BIRCH_PLANKS),
                new Pair(ItemRegistry.HOLLOW_JUNGLE_LOG, Items.JUNGLE_PLANKS),
                new Pair(ItemRegistry.HOLLOW_ACACIA_LOG, Items.ACACIA_PLANKS),
                new Pair(ItemRegistry.HOLLOW_DARK_OAK_LOG, Items.DARK_OAK_PLANKS),
                new Pair(ItemRegistry.HOLLOW_MANGROVE_LOG, Items.MANGROVE_PLANKS),
                new Pair(ItemRegistry.HOLLOW_CHERRY_LOG, Items.CHERRY_PLANKS),
                new Pair(ItemRegistry.HOLLOW_BAMBOO_BLOCK, Items.BAMBOO_PLANKS),
                new Pair(ItemRegistry.HOLLOW_CRIMSON_STEM, Items.CRIMSON_PLANKS),
                new Pair(ItemRegistry.HOLLOW_WARPED_STEM, Items.WARPED_PLANKS),
                new Pair(ItemRegistry.HOLLOW_BAOBAB_LOG, ItemRegistry.BAOBAB_PLANKS)
        };
        for (Pair p : pairs) {
            String wood = BuiltInRegistries.ITEM.getKey(p.planks()).getPath().replace("_planks", "");
            ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, p.planks())
                    .requires(p.hollow()).group("planks")
                    .unlockedBy("has_hollow_log", has(p.hollow()))
                    .save(output, NekomasFixed.id(wood + "_planks_from_hollow_log"));
        }
    }

    private void generateCakesAndDyes(RecipeOutput output) {
        // Cakes: all share the ADA/BEB/CCC shaped pattern; only the flavour item (D) differs.
        // RecipeCategory.FOOD puts the advancement under food/ and, via
        // RecipeBuilder.determineBookCategory, writes the recipe category as misc
        // (matching the committed output). Cakes have no loot (drop nothing on mine).
        record Cake(Item result, Item flavour) {
        }
        Cake[] cakes = {
                new Cake(ItemRegistry.APPLE_CAKE, Items.APPLE),
                new Cake(ItemRegistry.BEETROOT_CAKE, Items.BEETROOT),
                new Cake(ItemRegistry.CHOCOLATE_CAKE, Items.COCOA_BEANS),
                new Cake(ItemRegistry.COOKIE_CAKE, Items.COOKIE),
                new Cake(ItemRegistry.GLOWBERRY_CAKE, Items.GLOW_BERRIES),
                new Cake(ItemRegistry.PAN_CAKE, Items.HONEY_BOTTLE),
                new Cake(ItemRegistry.SWEETBERRY_CAKE, Items.SWEET_BERRIES),
                new Cake(ItemRegistry.VANILLA_CAKE, Items.MILK_BUCKET)
        };
        for (Cake c : cakes) {
            String name = BuiltInRegistries.ITEM.getKey(c.result()).getPath();
            ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, c.result())
                    .pattern("ADA").pattern("BEB").pattern("CCC")
                    .define('A', Items.MILK_BUCKET).define('B', Items.SUGAR)
                    .define('C', Items.WHEAT).define('D', c.flavour()).define('E', Items.EGG)
                    .unlockedBy("has_ingredients", has(Items.SUGAR))
                    .save(output, NekomasFixed.id(name));
        }

        // Ancient dyes: amber/aqua mix a base dye with white; indigo/maroon come from flowers.
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.AMBER_DYE, 2)
                .requires(ItemRegistry.MAROON_DYE).requires(Items.WHITE_DYE)
                .group("amber_dye").unlockedBy("has_dye", has(ItemRegistry.MAROON_DYE))
                .save(output, NekomasFixed.id("amber_dye"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.AQUA_DYE, 2)
                .requires(ItemRegistry.INDIGO_DYE).requires(Items.WHITE_DYE)
                .group("aqua_dye").unlockedBy("has_dye", has(ItemRegistry.INDIGO_DYE))
                .save(output, NekomasFixed.id("aqua_dye"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.INDIGO_DYE, 2)
                .requires(Items.PITCHER_PLANT)
                .group("indigo_dye").unlockedBy("has_pitcher_plant", has(Items.PITCHER_PLANT))
                .save(output, NekomasFixed.id("indigo_dye"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemRegistry.MAROON_DYE, 2)
                .requires(Items.TORCHFLOWER)
                .group("maroon_dye").unlockedBy("has_torchflower", has(Items.TORCHFLOWER))
                .save(output, NekomasFixed.id("maroon_dye"));

        // Pearl block: four pearls craft into one block.
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.PEARL_BLOCK)
                .pattern("##").pattern("##").define('#', ItemRegistry.PEARL)
                .unlockedBy("has_pearl", has(ItemRegistry.PEARL))
                .save(output, NekomasFixed.id("pearl_block"));

        // Single-item recipes: glow torch and glistering melon (misc category).
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.GLOW_TORCH, 4)
                .pattern("X").pattern("#").define('X', Items.GLOW_INK_SAC).define('#', Items.STICK)
                .unlockedBy("has_glow_ink_sac", has(Items.GLOW_INK_SAC))
                .save(output, NekomasFixed.id("glow_torch"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.GLISTERING_MELON)
                .pattern("###").pattern("###").pattern("###").define('#', Items.GLISTERING_MELON_SLICE)
                .unlockedBy("has_glistering_melon_slice", has(Items.GLISTERING_MELON_SLICE))
                .save(output, NekomasFixed.id("glistering_melon"));
    }

    @Override
    public void buildRecipes(RecipeOutput output) {
        generateBaobabRecipes(output);
        generateHollowLogRecipes(output);
        generateCakesAndDyes(output);

        for (AllDyes dye : AllDyes.values()) {
            Item dyeItem = dyeItem(dye);
            Item bricks = BlockDyeMap.BRICKS.get(dye).asItem();
            Item slab = BlockDyeMap.BRICK_SLAB.get(dye).asItem();
            Item stairs = BlockDyeMap.BRICK_STAIRS.get(dye).asItem();
            Item wall = BlockDyeMap.BRICK_WALL.get(dye).asItem();
            String c = dye.getSerializedName();

            ring(output, c + "_bricks_dyed", "dyed_bricks_dyed", Items.BRICKS, dyeItem,
                    bricks, 8, "has_plain_bricks", Items.BRICKS, "has_needed_dye", dyeItem);

            slabBuild(output, c + "_brick_slab", "dyed_brick_slab", bricks, slab, 6, "has_blocks", bricks);
            stonecutting(output, c + "_brick_slab_from_" + c + "_bricks_stonecutting", bricks, slab, 2, "has_blocks", bricks);
            ring(output, c + "_brick_slab_dyed", "dyed_brick_slab_dyed", Items.BRICK_SLAB, dyeItem,
                    slab, 8, "has_slab", Items.BRICK_SLAB, "has_needed_dye", dyeItem);

            stairsBuild(output, c + "_brick_stairs", "dyed_brick_stairs", bricks, stairs, "has_bricks", bricks);
            stonecutting(output, c + "_brick_stairs_from_" + c + "_bricks_stonecutting", bricks, stairs, 1, "has_bricks", bricks);
            ring(output, c + "_brick_stairs_dyed", "dyed_brick_stairs_dyed", Items.BRICK_STAIRS, dyeItem,
                    stairs, 8, "has_stairs", Items.BRICK_STAIRS, "has_needed_dye", dyeItem);

            wallBuild(output, c + "_brick_wall", "dyed_brick_wall", bricks, wall, "has_bricks", bricks);
            stonecutting(output, c + "_brick_wall_from_" + c + "_bricks_stonecutting", bricks, wall, 1, "has_bricks", bricks);
            ring(output, c + "_brick_wall_dyed", "dyed_brick_wall_dyed", Items.BRICK_WALL, dyeItem,
                    wall, 8, "has_walls", Items.BRICK_WALL, "has_needed_dye", dyeItem);

            recolour(output, "dye_" + c + "_spotted_wool", "spotted_wool",
                    ModTags.SPOTTED_WOOL_ITEM, dyeItem, BlockDyeMap.SPOTTED_WOOL.get(dye).asItem(),
                    RecipeCategory.BUILDING_BLOCKS, CraftingBookCategory.BUILDING, "has_needed_dye", has(dyeItem));
            recolour(output, "dye_" + c + "_spotted_carpet", "spotted_carpet",
                    ModTags.SPOTTED_CARPET_ITEM, dyeItem, BlockDyeMap.SPOTTED_CARPET.get(dye).asItem(),
                    RecipeCategory.DECORATIONS, CraftingBookCategory.BUILDING, "has_needed_dye", has(dyeItem));
        }
        BlockDyeMap.FROGLIGHT.forEach((dye, block) ->
                recolour(output, "dye_" + BuiltInRegistries.BLOCK.getKey(block).getPath(), "froglight",
                        ModTags.FROGLIGHTS_ITEM, dyeItem(dye), block.asItem(),
                        RecipeCategory.DECORATIONS, CraftingBookCategory.MISC, "has_item", has(ModTags.FROGLIGHTS_ITEM)));
    }

    private void ring(RecipeOutput output, String rid, String group, Item outside, Item inside,
                      Item result, int count, String cn1, Item c1, String cn2, Item c2) {
        // Dye-ring crafts unlock on the input block OR the dye (consistent across
        // all shapes; the base-bricks committed advancement already used both).
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, count)
                .pattern("###")
                .pattern("#D#")
                .pattern("###")
                .define('#', outside)
                .define('D', inside)
                .group(group)
                .unlockedBy(cn1, has(c1))
                .unlockedBy(cn2, has(c2));
        builder.save(output, NekomasFixed.id(rid));
    }

    private void slabBuild(RecipeOutput output, String rid, String group, Item bricks, Item slab,
                           int count, String crit, Item critItem) {
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slab, count)
                .pattern("###")
                .define('#', bricks)
                .group(group)
                .unlockedBy(crit, has(critItem));
        builder.save(output, NekomasFixed.id(rid));
    }

    private void stairsBuild(RecipeOutput output, String rid, String group, Item bricks, Item stairs,
                             String crit, Item critItem) {
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairs, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', bricks)
                .group(group)
                .unlockedBy(crit, has(critItem));
        builder.save(output, NekomasFixed.id(rid));
    }

    private void wallBuild(RecipeOutput output, String rid, String group, Item bricks, Item wall,
                           String crit, Item critItem) {
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wall, 6)
                .pattern("###")
                .pattern("###")
                .define('#', bricks)
                .group(group)
                .unlockedBy(crit, has(critItem));
        builder.save(output, NekomasFixed.id(rid));
    }

    private void stonecutting(RecipeOutput output, String rid, Item input, Item result, int count,
                              String crit, Item critItem) {
        SingleItemRecipeBuilder builder = count == 1
                ? SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), RecipeCategory.BUILDING_BLOCKS, result)
                : SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), RecipeCategory.BUILDING_BLOCKS, result, count);
        builder.unlockedBy(crit, has(critItem));
        builder.save(output, NekomasFixed.id(rid));
    }

    private void recolour(RecipeOutput output, String rid, String group,
                          net.minecraft.tags.TagKey<net.minecraft.world.item.Item> inputTag, Item material,
                          Item result, RecipeCategory category, CraftingBookCategory bookCategory,
                          String criterionName, Criterion<?> criterion) {
        RecolourRecipeBuilder.recolour(category, bookCategory,
                        Ingredient.of(inputTag), Ingredient.of(material), new ItemStack(result), group)
                .unlockedBy(criterionName, criterion)
                .save(output, NekomasFixed.id(rid));
    }
}
