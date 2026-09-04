package net.greenjab.nekomasfixed.registries;

import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.greenjab.nekomasfixed.NekomasFixed;
import net.greenjab.nekomasfixed.registry.registries.BlockEntityTypeRegistry;
import net.greenjab.nekomasfixed.render.block.entity.ClamBlockEntityRenderer;
import net.greenjab.nekomasfixed.render.block.entity.HollowLogBlockEntityRenderer;
import net.greenjab.nekomasfixed.render.block.entity.model.ClamBlockModel;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModBlockEntityRendererRegistry {

    public static final ModelLayerLocation CLAM =
            new ModelLayerLocation(NekomasFixed.id("clam"), "main");

    @SuppressWarnings("deprecation")
    public static void registerBlockEntityRenderers() {
        EntityModelLayerRegistry.registerModelLayer(CLAM, ClamBlockModel::createBodyLayer);
        BlockEntityRendererRegistry.INSTANCE.register(
                BlockEntityTypeRegistry.CLAM_BLOCK_ENTITY, ClamBlockEntityRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(
                BlockEntityTypeRegistry.HOLLOW_LOG_BLOCK_ENTITY, HollowLogBlockEntityRenderer::new);
    }
}
