package net.greenjab.nekomasfixed.render.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.greenjab.nekomasfixed.registries.ModBlockEntityRendererRegistry;
import net.greenjab.nekomasfixed.registry.block.AbstractEndermanHeadBlock;
import net.greenjab.nekomasfixed.registry.block.FloorEndermanHeadHead;
import net.greenjab.nekomasfixed.registry.block.WallEndermanHeadHead;
import net.greenjab.nekomasfixed.registry.block.entity.EndermanHeadBlockEntity;
import net.greenjab.nekomasfixed.render.block.entity.model.EndermanEyesBlockModel;
import net.greenjab.nekomasfixed.render.block.entity.model.EndermanHeadBlockModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import org.jspecify.annotations.NonNull;

import java.util.Random;

public class EndermanHeadBlockEntityRenderer implements BlockEntityRenderer<EndermanHeadBlockEntity> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.withDefaultNamespace("textures/entity/enderman/enderman.png");
    private static final ResourceLocation TEXTURE_EYES =
            ResourceLocation.withDefaultNamespace("textures/entity/enderman/enderman_eyes.png");

    private final EndermanHeadBlockModel headModel;
    private final EndermanEyesBlockModel eyesModel;
    private final Random random = new Random();

    public EndermanHeadBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.headModel = new EndermanHeadBlockModel(context.bakeLayer(ModBlockEntityRendererRegistry.ENDERMAN_HEAD));
        this.eyesModel = new EndermanEyesBlockModel(context.bakeLayer(ModBlockEntityRendererRegistry.ENDERMAN_HEAD));
    }

    @Override
    public void render(EndermanHeadBlockEntity blockEntity, float partialTick, @NonNull PoseStack poseStack,
                       @NonNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        BlockState blockState = blockEntity.getBlockState();
        boolean wall = blockState.getBlock() instanceof WallEndermanHeadHead;
        Direction facing = wall ? blockState.getValue(WallEndermanHeadHead.FACING) : null;
        int segment = wall
                ? RotationSegment.convertToSegment(facing.getOpposite())
                : blockState.getValue(FloorEndermanHeadHead.ROTATION);
        float yaw = RotationSegment.convertToDegrees(segment);
        boolean powered = blockState.getValue(AbstractEndermanHeadBlock.POWER) > 0;

        this.headModel.setupAnim(powered, wall);
        this.eyesModel.setupAnim(powered, wall);

        poseStack.pushPose();
        if (facing == null) {
            poseStack.translate(0.5F, 0.0F, 0.5F);
        } else {
            poseStack.translate(0.5F - facing.getStepX() * 0.2499F, 0.25F, 0.5F - facing.getStepZ() * 0.2499F);
        }

        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
        poseStack.translate(-0.5F, -0.5F, -0.5F);
        if (powered) {
            if (wall) poseStack.translate(this.random.nextGaussian() * 0.02F, this.random.nextGaussian() * 0.02F, 0.0F);
            else poseStack.translate(this.random.nextGaussian() * 0.02F, 0.0F, this.random.nextGaussian() * 0.02F);
        }

        this.headModel.renderToBuffer(poseStack, buffer.getBuffer(this.headModel.renderType(TEXTURE)),
                packedLight, packedOverlay, -1);
        this.eyesModel.renderToBuffer(poseStack, buffer.getBuffer(this.eyesModel.renderType(TEXTURE_EYES)),
                packedLight, packedOverlay, -1);

        poseStack.popPose();
    }
}