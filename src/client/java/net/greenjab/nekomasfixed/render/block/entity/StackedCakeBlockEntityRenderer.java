package net.greenjab.nekomasfixed.render.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.greenjab.nekomasfixed.registry.block.entity.StackedCakeBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

public class StackedCakeBlockEntityRenderer implements BlockEntityRenderer<StackedCakeBlockEntity> {

    public StackedCakeBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(StackedCakeBlockEntity blockEntity, float partialTick, @NonNull PoseStack poseStack,
                       @NonNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        BlockState layer2 = blockEntity.LAYER_2_STATE;
        BlockState layer3 = blockEntity.LAYER_3_STATE;
        BlockState candleState = blockEntity.CANDLE_STATE;

        if (!layer2.isAir()) {
            renderLayer(layer2, 1, poseStack, buffer, packedLight, packedOverlay);
        }
        if (!layer3.isAir()) {
            renderLayer(layer3, 2, poseStack, buffer, packedLight, packedOverlay);
        }
        if (!candleState.isAir()) {
            float cakeTop = 0.5F;          // top of the base layer
            if (!layer2.isAir()) cakeTop = 0.9F;   // after the 2nd layer
            if (!layer3.isAir()) cakeTop = 1.2F;   // after the 3rd layer
            renderCandle(candleState, cakeTop,
                    poseStack, buffer, packedLight, packedOverlay);
        }
    }

    private void renderLayer(BlockState state, int layer, PoseStack poseStack, MultiBufferSource buffer,
                             int packedLight, int packedOverlay) {
        poseStack.pushPose();
        float scale = (float) (1.0 - 0.2 * layer);
        // Center the (tapered) layer horizontally, and stack it above previous layers.
        poseStack.translate((1.0F - scale) / 2.0F, 0.5F * layer - 0.1F * (layer - 1), (1.0F - scale) / 2.0F);
        poseStack.scale(scale, scale, scale);

        BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
        blockRenderer.renderSingleBlock(state, poseStack, buffer, packedLight, packedOverlay);

        poseStack.popPose();
    }

    private void renderCandle(BlockState state, float cakeTop, PoseStack poseStack, MultiBufferSource buffer,
                              int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0, cakeTop, 0);

        BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
        blockRenderer.renderSingleBlock(state, poseStack, buffer, packedLight, packedOverlay);

        poseStack.popPose();
    }
}