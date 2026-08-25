package net.greenjab.nekomasfixed;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.greenjab.nekomasfixed.mixin.client.ItemPropertiesAccessor;
import net.greenjab.nekomasfixed.registries.ModBlockEntityRendererRegistry;
import net.greenjab.nekomasfixed.registries.ModEntityRendererRegistry;
import net.greenjab.nekomasfixed.registry.registries.BlockRegistry;
import net.greenjab.nekomasfixed.registry.registries.ClamStateProperty;
import net.greenjab.nekomasfixed.registry.registries.ItemRegistry;
import net.minecraft.client.renderer.RenderType;

public class NekomasFixedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.BAOBAB_SAPLING, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.BAOBAB_FRUIT, RenderType.cutout());
        ModEntityRendererRegistry.registerEntityRenderer();
        ModBlockEntityRendererRegistry.registerBlockEntityRenderers();

        ClamStateProperty clamState = new ClamStateProperty();
        ItemPropertiesAccessor.invokeRegister(ItemRegistry.CLAM, net.greenjab.nekomasfixed.NekomasFixed.id("clam_state"), clamState);
        ItemPropertiesAccessor.invokeRegister(ItemRegistry.CLAM_BLUE, net.greenjab.nekomasfixed.NekomasFixed.id("clam_state"), clamState);
        ItemPropertiesAccessor.invokeRegister(ItemRegistry.CLAM_PINK, net.greenjab.nekomasfixed.NekomasFixed.id("clam_state"), clamState);
        ItemPropertiesAccessor.invokeRegister(ItemRegistry.CLAM_PURPLE, net.greenjab.nekomasfixed.NekomasFixed.id("clam_state"), clamState);
    }
}
