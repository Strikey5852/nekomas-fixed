package net.greenjab.nekomasfixed.registry.registries;

import net.greenjab.nekomasfixed.NekomasFixed;
import net.greenjab.nekomasfixed.registry.block.GlowTorchBlock;
import net.greenjab.nekomasfixed.registry.block.WallGlowTorchBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class BlockRegistry {
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

    private static Block register(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        return Registry.register(BuiltInRegistries.BLOCK,
            ResourceKey.create(Registries.BLOCK, NekomasFixed.id(id)),
            factory.apply(settings));
    }

    public static void registerBlocks() {
        NekomasFixed.LOGGER.info("Registered block : {}", GLOW_TORCH);
    }
}
