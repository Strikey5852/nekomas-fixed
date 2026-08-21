package net.greenjab.nekomasfixed.registry.registries;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.greenjab.nekomasfixed.NekomasFixed;
import net.greenjab.nekomasfixed.registry.block.GlowTorchBlock;
import net.greenjab.nekomasfixed.registry.block.WallGlowTorchBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

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
    public static final Block BAOBAB_LOG = register(
        "baobab_log",
        RotatedPillarBlock::new,
        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor(MapColor.WOOD)
    );
    public static final Block BAOBAB_WOOD = register(
        "baobab_wood",
        RotatedPillarBlock::new,
        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
    );
    public static final Block STRIPPED_BAOBAB_LOG = register(
        "stripped_baobab_log",
        RotatedPillarBlock::new,
        BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
    );
    public static final Block STRIPPED_BAOBAB_WOOD = register(
        "stripped_baobab_wood",
        RotatedPillarBlock::new,
        BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
    );
    public static final Block BAOBAB_PLANKS = register(
        "baobab_planks",
        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
    );
    public static final Block BAOBAB_STAIRS = register(
        "baobab_stairs",
        settings -> new StairBlock(BAOBAB_PLANKS.defaultBlockState(), settings),
        BlockBehaviour.Properties.ofLegacyCopy(BAOBAB_PLANKS)
    );
    public static final Block BAOBAB_SLAB = register(
        "baobab_slab",
        SlabBlock::new,
        BlockBehaviour.Properties.of().mapColor(MapColor.WOOD)
            .instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F)
            .sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block BAOBAB_FENCE = register(
        "baobab_fence",
        FenceBlock::new,
        BlockBehaviour.Properties.of().mapColor(BAOBAB_PLANKS.defaultMapColor())
            .instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F)
            .sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block BAOBAB_FENCE_GATE = register(
        "baobab_fence_gate",
        settings -> new FenceGateBlock(BAOBAB_WOODTYPE, settings),
        BlockBehaviour.Properties.of().mapColor(BAOBAB_PLANKS.defaultMapColor())
            .forceSolidOn().instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F).ignitedByLava()
    );
    public static final Block BAOBAB_DOOR = register(
        "baobab_door",
        settings -> new DoorBlock(BAOBAB_BLOCKSETTYPE, settings),
        BlockBehaviour.Properties.of().mapColor(BAOBAB_PLANKS.defaultMapColor())
            .instrument(NoteBlockInstrument.BASS).strength(3.0F)
            .noOcclusion().pushReaction(PushReaction.DESTROY).ignitedByLava()
    );
    public static final Block BAOBAB_TRAPDOOR = register(
        "baobab_trapdoor",
        settings -> new TrapDoorBlock(BAOBAB_BLOCKSETTYPE, settings),
        BlockBehaviour.Properties.of().mapColor(MapColor.WOOD)
            .instrument(NoteBlockInstrument.BASS).strength(3.0F)
            .noOcclusion().isValidSpawn(Blocks::never).ignitedByLava()
    );
    public static final Block BAOBAB_PRESSURE_PLATE = register(
        "baobab_pressure_plate",
        settings -> new PressurePlateBlock(BAOBAB_BLOCKSETTYPE, settings),
        BlockBehaviour.Properties.of().mapColor(BAOBAB_PLANKS.defaultMapColor())
            .forceSolidOn().instrument(NoteBlockInstrument.BASS)
            .noCollission().strength(0.5F)
            .pushReaction(PushReaction.DESTROY).ignitedByLava()
    );
    public static final Block BAOBAB_BUTTON = register(
        "baobab_button",
        settings -> new ButtonBlock(BAOBAB_BLOCKSETTYPE, 30, settings),
        BlockBehaviour.Properties.of().mapColor(BAOBAB_PLANKS.defaultMapColor())
            .forceSolidOn().instrument(NoteBlockInstrument.BASS)
            .noCollission().strength(0.5F)
            .pushReaction(PushReaction.DESTROY).ignitedByLava()
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

    private static Block register(String id, BlockBehaviour.Properties settings) {
        return register(id, Block::new, settings);
    }

    private static Block register(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        return Registry.register(BuiltInRegistries.BLOCK,
            ResourceKey.create(Registries.BLOCK, NekomasFixed.id(id)),
            factory.apply(settings));
    }

    public static void registerBlocks() {
        NekomasFixed.LOGGER.info("Registered block : {}", GLOW_TORCH);

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
