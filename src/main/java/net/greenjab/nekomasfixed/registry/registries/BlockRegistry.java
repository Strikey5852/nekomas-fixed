package net.greenjab.nekomasfixed.registry.registries;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.greenjab.nekomasfixed.NekomasFixed;
import net.greenjab.nekomasfixed.registry.block.*;
import net.greenjab.nekomasfixed.registry.block.enums.ClamType;
import net.greenjab.nekomasfixed.registry.worldgen.ModConfiguredFeatures;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.Optional;
import java.util.function.Function;

public class BlockRegistry {
    public static final BlockSetType BAOBAB_BLOCKSETTYPE = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(NekomasFixed.id("baobab"));
    public static final WoodType BAOBAB_WOODTYPE = WoodTypeBuilder.copyOf(WoodType.OAK).register(NekomasFixed.id("baobab"), BAOBAB_BLOCKSETTYPE);
    public static final Block GLOW_TORCH = register(
            "glow_torch",
            GlowTorchBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instabreak()
                    .noCollission()
                    .lightLevel(state -> state.getValue(GlowTorchBlock.WATERLOGGED) ? 13 : 0)
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final Block GLOW_WALL_TORCH = register(
            "glow_wall_torch",
            WallGlowTorchBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instabreak()
                    .noCollission()
                    .lightLevel(state -> state.getValue(GlowTorchBlock.WATERLOGGED) ? 13 : 0)
                    .sound(SoundType.WOOD)
                    .pushReaction(PushReaction.DESTROY)
    );
    public static final Block GEYSER = register(
            "geyser",
            GeyserBlock::new,
            BlockBehaviour.Properties.of()
                    .randomTicks()
                    .strength(0.5f, 0.5f)
                    .lightLevel(state -> 15)
    );
    public static final Block BAOBAB_LOG = register(
            "baobab_log",
            RotatedPillarBlock::new,
            baobabWoodProperties()
    );
    public static final Block BAOBAB_WOOD = register(
            "baobab_wood",
            RotatedPillarBlock::new,
            baobabWoodProperties()
    );
    public static final Block STRIPPED_BAOBAB_LOG = register(
            "stripped_baobab_log",
            RotatedPillarBlock::new,
            baobabWoodProperties()
    );
    public static final Block STRIPPED_BAOBAB_WOOD = register(
            "stripped_baobab_wood",
            RotatedPillarBlock::new,
            baobabWoodProperties()
    );
    public static final Block BAOBAB_PLANKS = register(
            "baobab_planks",
            baobabWoodProperties()
    );
    public static final Block BAOBAB_STAIRS = register(
            "baobab_stairs",
            settings -> new StairBlock(BAOBAB_PLANKS.defaultBlockState(), settings),
            baobabWoodProperties().strength(2.0F, 3.0F)
    );
    public static final Block BAOBAB_SLAB = register(
            "baobab_slab",
            SlabBlock::new,
            baobabWoodProperties().strength(2.0F, 3.0F)
    );
    public static final Block BAOBAB_FENCE = register(
            "baobab_fence",
            FenceBlock::new,
            baobabWoodProperties().strength(2.0F, 3.0F)
    );
    public static final Block BAOBAB_FENCE_GATE = register(
            "baobab_fence_gate",
            settings -> new FenceGateBlock(BAOBAB_WOODTYPE, settings),
            baobabWoodProperties().forceSolidOn().strength(2.0F, 3.0F)
    );
    public static final Block BAOBAB_DOOR = register(
            "baobab_door",
            settings -> new DoorBlock(BAOBAB_BLOCKSETTYPE, settings),
            baobabWoodProperties().strength(3.0F).noOcclusion().pushReaction(PushReaction.DESTROY)
    );
    public static final Block BAOBAB_TRAPDOOR = register(
            "baobab_trapdoor",
            settings -> new TrapDoorBlock(BAOBAB_BLOCKSETTYPE, settings),
            baobabWoodProperties().strength(3.0F).noOcclusion().isValidSpawn(Blocks::never)
    );
    public static final Block BAOBAB_PRESSURE_PLATE = register(
            "baobab_pressure_plate",
            settings -> new PressurePlateBlock(BAOBAB_BLOCKSETTYPE, settings),
            baobabWoodProperties().noCollission().forceSolidOn().strength(0.5F).pushReaction(PushReaction.DESTROY)
    );
    public static final Block BAOBAB_BUTTON = register(
            "baobab_button",
            settings -> new ButtonBlock(BAOBAB_BLOCKSETTYPE, 30, settings),
            baobabWoodProperties().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY)
    );
    public static final Block BAOBAB_SIGN = register(
            "baobab_sign",
            settings -> new StandingSignBlock(BAOBAB_WOODTYPE, settings),
            BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava()
    );
    public static final Block BAOBAB_WALL_SIGN = register(
            "baobab_wall_sign",
            settings -> new WallSignBlock(BAOBAB_WOODTYPE, settings),
            BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava()
    );
    public static final Block BAOBAB_HANGING_SIGN = register(
            "baobab_hanging_sign",
            settings -> new CeilingHangingSignBlock(BAOBAB_WOODTYPE, settings),
            BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava()
    );
    public static final Block BAOBAB_WALL_HANGING_SIGN = register(
            "baobab_wall_hanging_sign",
            settings -> new WallHangingSignBlock(BAOBAB_WOODTYPE, settings),
            BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F).ignitedByLava()
    );
    public static final Block BAOBAB_LEAVES = register(
            "baobab_leaves",
            LeavesBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .strength(0.2F)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .isValidSpawn(((blockState, blockGetter, blockPos, object) -> false))
                    .isSuffocating(((blockState, blockGetter, blockPos) -> false))
                    .isViewBlocking(((blockState, blockGetter, blockPos) -> false))
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(((blockState, blockGetter, blockPos) -> false))
    );
    public static final Block ROPE = register(
            "rope",
            RopeBlock::new,
            BlockBehaviour.Properties.of()
                    .strength(0.2F)
                    .isRedstoneConductor((state, level, pos) -> false)
                    .ignitedByLava()
                    .noCollission()
    );
    public static final Block BAOBAB_FRUIT = register(
            "baobab_fruit",
            BaobabFruitBlock::new,
            BlockBehaviour.Properties.of()
                    .randomTicks()
                    .strength(0.2F)
                    .isViewBlocking((state, level, pos) -> false)
                    .ignitedByLava()
                    .instabreak()
    );
    public static final Block BAOBAB_SAPLING = register(
            "baobab_sapling",
            settings -> new SaplingBlock(
                    new TreeGrower("nekomasfixed:baobab", Optional.of(ModConfiguredFeatures.BAOBAB_KEY), Optional.empty(), Optional.empty()),
                    settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_SAPLING)
    );

    public static final Block CLAM = register(
            "clam",
            settings -> new ClamBlock(ClamType.REGULAR, settings),
            clamBlockProperties()
    );
    public static final Block CLAM_BLUE = register(
            "clam_blue",
            settings -> new ClamBlock(ClamType.BLUE, settings),
            clamBlockProperties()
    );
    public static final Block CLAM_PINK = register(
            "clam_pink",
            settings -> new ClamBlock(ClamType.PINK, settings),
            clamBlockProperties()
    );
    public static final Block CLAM_PURPLE = register(
            "clam_purple",
            settings -> new ClamBlock(ClamType.PURPLE, settings),
            clamBlockProperties()
    );
    public static final Block PEARL_BLOCK = register(
            "pearl_block",
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SNOW)
                    .strength(0.5F)
                    .sound(SoundType.STONE)
    );
    public static final Block HOLLOW_OAK_LOG = register("hollow_oak_log", HollowLogBlock::new,
            hollowLogProperties(Blocks.OAK_LOG));
    public static final Block HOLLOW_SPRUCE_LOG = register("hollow_spruce_log", HollowLogBlock::new,
            hollowLogProperties(Blocks.SPRUCE_LOG));
    public static final Block HOLLOW_BIRCH_LOG = register("hollow_birch_log", HollowLogBlock::new,
            hollowLogProperties(Blocks.BIRCH_LOG));
    public static final Block HOLLOW_JUNGLE_LOG = register("hollow_jungle_log", HollowLogBlock::new,
            hollowLogProperties(Blocks.JUNGLE_LOG));
    public static final Block HOLLOW_ACACIA_LOG = register("hollow_acacia_log", HollowLogBlock::new,
            hollowLogProperties(Blocks.ACACIA_LOG));
    public static final Block HOLLOW_DARK_OAK_LOG = register("hollow_dark_oak_log", HollowLogBlock::new,
            hollowLogProperties(Blocks.DARK_OAK_LOG));
    public static final Block HOLLOW_MANGROVE_LOG = register("hollow_mangrove_log", HollowLogBlock::new,
            hollowLogProperties(Blocks.MANGROVE_LOG));
    public static final Block HOLLOW_CHERRY_LOG = register("hollow_cherry_log", HollowLogBlock::new,
            hollowLogProperties(Blocks.CHERRY_LOG));
    public static final Block HOLLOW_BAMBOO_BLOCK = register("hollow_bamboo_block", HollowLogBlock::new,
            hollowLogProperties(Blocks.BAMBOO_BLOCK));
    public static final Block HOLLOW_CRIMSON_STEM = register("hollow_crimson_stem", HollowLogBlock::new,
            hollowLogProperties(Blocks.CRIMSON_HYPHAE));
    public static final Block HOLLOW_WARPED_STEM = register("hollow_warped_stem", HollowLogBlock::new,
            hollowLogProperties(Blocks.WARPED_HYPHAE));
    public static final Block HOLLOW_BAOBAB_LOG = register("hollow_baobab_log", HollowLogBlock::new,
            hollowLogProperties(BAOBAB_LOG));

    // Build properties copied from the base log, with the emitted light driven by the
    // hollow log's LIGHT_LEVEL state property. Non-occluding (1.21.1 culls
    // neighbor faces by opaque-cube flag, unlike 26.x shape-based culling) so a
    // block behind the hollow opening stays visible.
    private static BlockBehaviour.Properties hollowLogProperties(Block baseLog) {
        return BlockBehaviour.Properties.ofFullCopy(baseLog)
                .lightLevel(state -> state.getValue(HollowLogBlock.LIGHT_LEVEL))
                .noOcclusion();
    }

    private static BlockBehaviour.Properties clamBlockProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.SAND)
                .strength(0.5F)
                .sound(SoundType.STONE)
                .noOcclusion()
                .isRedstoneConductor((state, level, pos) -> false);
    }

    private static BlockBehaviour.Properties baobabWoodProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .instrument(NoteBlockInstrument.BASS)
                .sound(SoundType.WOOD)
                .ignitedByLava();
    }

    private static Block register(String id, BlockBehaviour.Properties settings) {
        return register(id, Block::new, settings);
    }

    private static Block register(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        return Registry.register(BuiltInRegistries.BLOCK,
                ResourceKey.create(Registries.BLOCK, NekomasFixed.id(id)),
                factory.apply(settings));
    }

    public static void registerBlocks() {
        NekomasFixed.LOGGER.info("Registering blocks");

        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        fireBlock.setFlammable(BAOBAB_PLANKS, 5, 20);
        fireBlock.setFlammable(BAOBAB_SLAB, 5, 20);
        fireBlock.setFlammable(BAOBAB_FENCE_GATE, 5, 20);
        fireBlock.setFlammable(BAOBAB_FENCE, 5, 20);
        fireBlock.setFlammable(BAOBAB_STAIRS, 5, 20);
        fireBlock.setFlammable(BAOBAB_LOG, 5, 5);
        fireBlock.setFlammable(BAOBAB_WOOD, 5, 5);
        fireBlock.setFlammable(STRIPPED_BAOBAB_LOG, 5, 5);
        fireBlock.setFlammable(STRIPPED_BAOBAB_WOOD, 5, 5);

        StrippableBlockRegistry.register(BAOBAB_LOG, STRIPPED_BAOBAB_LOG);
        StrippableBlockRegistry.register(BAOBAB_WOOD, STRIPPED_BAOBAB_WOOD);
    }
}
