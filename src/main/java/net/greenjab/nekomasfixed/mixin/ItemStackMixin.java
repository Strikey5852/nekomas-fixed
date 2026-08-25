package net.greenjab.nekomasfixed.mixin;

import net.greenjab.nekomasfixed.registry.other.ContainerTooltipData;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(method = "getTooltipImage", at = @At("HEAD"), cancellable = true)
    private void useContainerTooltip(CallbackInfoReturnable<Optional<TooltipComponent>> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        if (!stack.has(DataComponents.HIDE_TOOLTIP) && stack.has(DataComponents.CONTAINER)) {
            ItemContainerContents contents = stack.get(DataComponents.CONTAINER);
            // Only show the container tooltip when the clam actually holds an item.
            if (contents != null && contents.nonEmptyItems().iterator().hasNext()) {
                cir.setReturnValue(Optional.of(new ContainerTooltipData(contents)));
            }
        }
    }
}
