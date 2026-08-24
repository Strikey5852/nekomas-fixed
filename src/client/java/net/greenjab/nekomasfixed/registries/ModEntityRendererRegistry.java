package net.greenjab.nekomasfixed.registries;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.greenjab.nekomasfixed.NekomasFixed;
import net.greenjab.nekomasfixed.registry.registries.EntityTypeRegistry;
import net.greenjab.nekomasfixed.render.entity.BaobabBoatRenderer;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModEntityRendererRegistry {

    public static final ModelLayerLocation BAOBAB_BOAT_LAYER =
        new ModelLayerLocation(NekomasFixed.id("boat/baobab"), "main");
    public static final ModelLayerLocation BAOBAB_CHEST_BOAT_LAYER =
        new ModelLayerLocation(NekomasFixed.id("chest_boat/baobab"), "main");

    public static void registerEntityRenderer() {
        EntityModelLayerRegistry.registerModelLayer(BAOBAB_BOAT_LAYER, BoatModel::createBodyModel);
        EntityModelLayerRegistry.registerModelLayer(BAOBAB_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);

        EntityRendererRegistry.register(EntityTypeRegistry.BAOBAB_BOAT, context ->
            new BaobabBoatRenderer(context, BAOBAB_BOAT_LAYER, false,
                NekomasFixed.id("textures/entity/boat/baobab.png")));
        EntityRendererRegistry.register(EntityTypeRegistry.BAOBAB_CHEST_BOAT, context ->
            new BaobabBoatRenderer(context, BAOBAB_CHEST_BOAT_LAYER, true,
                NekomasFixed.id("textures/entity/chest_boat/baobab.png")));
    }
}
