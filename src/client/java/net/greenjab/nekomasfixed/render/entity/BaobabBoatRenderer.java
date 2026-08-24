package net.greenjab.nekomasfixed.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import org.joml.Quaternionf;
import org.jspecify.annotations.NonNull;

public class BaobabBoatRenderer extends EntityRenderer<Boat> {
    private final ListModel<Boat> model;
    private final ResourceLocation texture;

    public BaobabBoatRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer, boolean hasChest, ResourceLocation texture) {
        super(context);
        this.shadowRadius = 0.8F;
        this.texture = texture;
        ModelPart root = context.bakeLayer(layer);
        this.model = hasChest ? new ChestBoatModel(root) : new BoatModel(root);
    }

    @Override
    public void render(Boat boat, float entityYaw, float partialTick, PoseStack poseStack, @NonNull MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.375F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
        float f = (float) boat.getHurtTime() - partialTick;
        float g = boat.getDamage() - partialTick;
        if (g < 0.0F) g = 0.0F;
        if (f > 0.0F) {
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * g / 10.0F * (float) boat.getHurtDir()));
        }
        float h = boat.getBubbleAngle(partialTick);
        if (!Mth.equal(h, 0.0F)) {
            poseStack.mulPose(new Quaternionf().setAngleAxis(boat.getBubbleAngle(partialTick) * ((float) Math.PI / 180F), 1.0F, 0.0F, 1.0F));
        }
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        this.model.setupAnim(boat, partialTick, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = buffer.getBuffer(this.model.renderType(this.texture));
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
        if (!boat.isUnderWater() && this.model instanceof WaterPatchModel waterPatchModel) {
            waterPatchModel.waterPatch().render(poseStack, buffer.getBuffer(RenderType.waterMask()), packedLight, OverlayTexture.NO_OVERLAY);
        }
        poseStack.popPose();
        super.render(boat, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public @NonNull ResourceLocation getTextureLocation(@NonNull Boat boat) {
        return this.texture;
    }
}
