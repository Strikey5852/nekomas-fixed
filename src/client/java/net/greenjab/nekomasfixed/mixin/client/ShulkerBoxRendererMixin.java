package net.greenjab.nekomasfixed.mixin.client;

import net.greenjab.nekomasfixed.NekomasFixed;
import net.greenjab.nekomasfixed.registry.registries.BlockRegistry;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.ShulkerBoxRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ShulkerBoxRenderer.class)
public class ShulkerBoxRendererMixin {

    // ShulkerBoxRenderer picks the sheet from the block's DyeColor, so ancient
    // shulkers would show their representational colour. Swap in the custom
    // entity/shulker sprite.
    @ModifyVariable(
            method = "render(Lnet/minecraft/world/level/block/entity/ShulkerBoxBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",
            at = @At("STORE"),
            ordinal = 0
    )
    private Material nekomasfixed$swapAncientShulkerTexture(
            Material material, ShulkerBoxBlockEntity blockEntity, float partialTick,
            com.mojang.blaze3d.vertex.PoseStack poseStack,
            net.minecraft.client.renderer.MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (material.atlasLocation().equals(Sheets.SHULKER_SHEET)) {
            var block = blockEntity.getBlockState().getBlock();
            var sprite = block == BlockRegistry.AMBER_SHULKER_BOX ? "entity/shulker/shulker_amber"
                    : block == BlockRegistry.AQUA_SHULKER_BOX ? "entity/shulker/shulker_aqua"
                    : block == BlockRegistry.INDIGO_SHULKER_BOX ? "entity/shulker/shulker_indigo"
                    : block == BlockRegistry.MAROON_SHULKER_BOX ? "entity/shulker/shulker_maroon"
                    : null;
            if (sprite != null) {
                return new Material(Sheets.SHULKER_SHEET, NekomasFixed.id(sprite));
            }
        }
        return material;
    }
}