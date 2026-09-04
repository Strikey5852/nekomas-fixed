package net.greenjab.nekomasfixed.render.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.greenjab.nekomasfixed.NekomasFixed;
import net.greenjab.nekomasfixed.registries.ModBlockEntityRendererRegistry;
import net.greenjab.nekomasfixed.registry.block.ClamBlock;
import net.greenjab.nekomasfixed.registry.block.entity.ClamBlockEntity;
import net.greenjab.nekomasfixed.registry.registries.BlockRegistry;
import net.greenjab.nekomasfixed.render.block.entity.model.ClamBlockModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class ClamBlockEntityRenderer implements BlockEntityRenderer<ClamBlockEntity> {

    private final ClamBlockModel model;

    public ClamBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new ClamBlockModel(context.bakeLayer(ModBlockEntityRendererRegistry.CLAM));
    }

    private static ResourceLocation getTexture(net.minecraft.world.level.block.Block block) {
        if (block == BlockRegistry.CLAM_BLUE) {
            return NekomasFixed.id("textures/entity/chest/clam_blue.png");
        } else if (block == BlockRegistry.CLAM_PINK) {
            return NekomasFixed.id("textures/entity/chest/clam_pink.png");
        } else if (block == BlockRegistry.CLAM_PURPLE) {
            return NekomasFixed.id("textures/entity/chest/clam_purple.png");
        }
        return NekomasFixed.id("textures/entity/chest/clam.png");
    }

    @Override
    public void render(ClamBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
        float openness = blockEntity.getOpenNess(partialTick);
        this.model.setupAnim(openness);

        Direction facing = blockEntity.getBlockState().getValue(ClamBlock.FACING);
        ResourceLocation texture = getTexture(blockEntity.getBlockState().getBlock());

        poseStack.pushPose();
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(-facing.toYRot()));
        poseStack.translate(-0.5F, -0.5F, -0.5F);

        var vertexConsumer = buffer.getBuffer(this.model.renderType(texture));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, -1);

        poseStack.popPose();

        if (openness > 0.0F) {
            ItemStack held = blockEntity.getItem(0);
            if (!held.isEmpty()) {
                poseStack.pushPose();
                poseStack.translate(0.5F, 0.5F, 0.5F);
                poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - facing.toYRot()));
                poseStack.translate(0.0F, -0.37F, -0.11F);
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.scale(0.5F, 0.5F, 0.5F);
                Minecraft.getInstance().getItemRenderer().renderStatic(held, ItemDisplayContext.FIXED,
                        packedLight, packedOverlay, poseStack, buffer, blockEntity.getLevel(),
                        (int) blockEntity.getBlockPos().asLong());
                poseStack.popPose();
            }
        }
    }

}
