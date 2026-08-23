package net.greenjab.nekomasfixed;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.greenjab.nekomasfixed.registry.registries.BlockRegistry;
import net.minecraft.client.renderer.RenderType;

public class NekomasFixedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.BAOBAB_SAPLING, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.BAOBAB_FRUIT, RenderType.cutout());
    }
}
