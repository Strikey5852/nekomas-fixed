package net.greenjab.nekomasfixed.registry.registries;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

public class ClamStateProperty implements ClampedItemPropertyFunction {

    @Override
    public float unclampedCall(ItemStack stack, ClientLevel level, LivingEntity entity, int seed) {
        int state = stack.getOrDefault(ComponentRegistry.CLAM_STATE, 0);
        ItemContainerContents contents = stack.get(DataComponents.CONTAINER);
        boolean hasItem = contents != null && contents.nonEmptyItems().iterator().hasNext();
        // Distinct values within [0,1]: the override is clamped, so 1 vs 2 both hit 1.0.
        // Use 0.5 for "open" and 1.0 for "open with item" so they resolve to different models.
        if (state == 2 || hasItem) {
            return 1.0F;
        } else if (state == 1) {
            return 0.5F;
        }
        return 0.0F;
    }
}
