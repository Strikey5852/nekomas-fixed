package net.greenjab.nekomasfixed.render.block.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import org.jspecify.annotations.NonNull;

public class EndermanEyesBlockModel extends Model {

    private final ModelPart head;

    public EndermanEyesBlockModel(ModelPart root) {
        super(RenderType::eyes);
        this.head = root.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0)
                .addBox(4.0F, 0.0F, 4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.1F)), PartPose.ZERO);
        return LayerDefinition.create(mesh, 64, 32);
    }

    public void setupAnim(boolean powered, boolean wall) {
        this.head.y = 0.0F;
        if (powered) {
            if (wall) this.head.y -= 2.5F;
            else this.head.y -= 5.0F;
        }
    }

    @Override
    public void renderToBuffer(@NonNull PoseStack poseStack, @NonNull VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        this.head.render(poseStack, buffer, packedLight, packedOverlay, color);
    }
}