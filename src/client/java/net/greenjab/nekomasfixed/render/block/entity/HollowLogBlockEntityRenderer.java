package net.greenjab.nekomasfixed.render.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.greenjab.nekomasfixed.registry.block.entity.HollowLogBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class HollowLogBlockEntityRenderer implements BlockEntityRenderer<HollowLogBlockEntity> {

    public HollowLogBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(HollowLogBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
        BlockState stored = blockEntity.getStoredBlock();
        if (stored.isAir()) {
            return;
        }

        poseStack.pushPose();
        poseStack.translate(0.125, 0.125, 0.125);
        poseStack.scale(0.75F, 0.75F, 0.75F);

        BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
        blockRenderer.renderSingleBlock(stored, poseStack, buffer, packedLight, packedOverlay);

        poseStack.popPose();
    }
}