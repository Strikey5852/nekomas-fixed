package net.greenjab.nekomasfixed.render.block.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import org.jspecify.annotations.NonNull;

public class EndermanHeadBlockModel extends Model {

    private final ModelPart head;
    private final ModelPart mouth;

    public EndermanHeadBlockModel(ModelPart root) {
        super(RenderType::entityCutout);
        this.head = root.getChild("head");
        this.mouth = root.getChild("mouth");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0)
                .addBox(4.0F, 0.0F, 4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
        root.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 16)
                .addBox(4.0F, 0.0F, 4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(-0.5F)), PartPose.ZERO);
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void setupAnim(boolean powered, boolean wall) {
        this.head.y = 0.0F;
        this.mouth.y = 0.0F;
        if (powered) {
            if (wall) {
                this.head.y -= 2.5F;
                this.mouth.y += 2.5F;
            } else {
                this.head.y -= 5.0F;
            }
        }
    }

    @Override
    public void renderToBuffer(@NonNull PoseStack poseStack, @NonNull VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        this.head.render(poseStack, buffer, packedLight, packedOverlay, color);
        this.mouth.render(poseStack, buffer, packedLight, packedOverlay, color);
    }
}