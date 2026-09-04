package net.greenjab.nekomasfixed.render.block.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import org.jspecify.annotations.NonNull;

public class ClamBlockModel extends Model {

    private final ModelPart bottom;
    private final ModelPart lid;
    private final ModelPart bottomHinge;
    private final ModelPart lidHinge;

    public ClamBlockModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.bottom = root.getChild("bottom");
        this.lid = root.getChild("lid");
        this.bottomHinge = root.getChild("bottom_hinge");
        this.lidHinge = root.getChild("lid_hinge");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("bottom",
                CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 0.0F, 4.0F, 14.0F, 2.0F, 12.0F),
                PartPose.ZERO);
        root.addOrReplaceChild("lid",
                CubeListBuilder.create().texOffs(0, 14).addBox(1.0F, 0.0F, 3.0F, 14.0F, 2.0F, 12.0F),
                PartPose.offset(0.0F, 2.0F, 1.0F));
        root.addOrReplaceChild("bottom_hinge",
                CubeListBuilder.create().texOffs(18, 28).addBox(5.0F, 0.0F, 1.0F, 6.0F, 2.0F, 3.0F),
                PartPose.ZERO);
        root.addOrReplaceChild("lid_hinge",
                CubeListBuilder.create().texOffs(0, 28).addBox(5.0F, 0.0F, 0.0F, 6.0F, 2.0F, 3.0F),
                PartPose.offset(0.0F, 2.0F, 1.0F));
        return LayerDefinition.create(mesh, 64, 64);
    }

    public void setupAnim(float openness) {
        float rot = openness * (float) (Math.PI / 2);
        this.lid.xRot = -rot;
        this.lidHinge.xRot = this.lid.xRot;
    }

    @Override
    public void renderToBuffer(@NonNull PoseStack poseStack, @NonNull VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        this.bottom.render(poseStack, buffer, packedLight, packedOverlay, color);
        this.lid.render(poseStack, buffer, packedLight, packedOverlay, color);
        this.bottomHinge.render(poseStack, buffer, packedLight, packedOverlay, color);
        this.lidHinge.render(poseStack, buffer, packedLight, packedOverlay, color);
    }
}
