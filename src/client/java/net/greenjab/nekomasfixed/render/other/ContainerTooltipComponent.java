package net.greenjab.nekomasfixed.render.other;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Environment(EnvType.CLIENT)
public class ContainerTooltipComponent implements ClientTooltipComponent {

    private static final ResourceLocation BACKGROUND_SPRITE =
        ResourceLocation.withDefaultNamespace("container/bundle/background");
    private static final ResourceLocation SLOT_SPRITE =
        ResourceLocation.withDefaultNamespace("container/bundle/slot");
    private final List<ItemStack> items;

    public ContainerTooltipComponent(ItemContainerContents contents) {
        List<ItemStack> all = new java.util.ArrayList<>();
        for (ItemStack stack : contents.nonEmptyItemsCopy()) {
            all.add(stack);
            if (all.size() >= 27) {
                break;
            }
        }
        this.items = all;
    }

    @Override
    public int getHeight() {
        return this.backgroundHeight() + 4;
    }

    @Override
    public int getWidth(@NotNull Font font) {
        return this.backgroundWidth();
    }

    private int backgroundWidth() {
        return this.gridSizeX() * 18 + 2;
    }

    @Override
    public void renderImage(@NotNull Font font, int x, int y, @NotNull GuiGraphics guiGraphics) {
        guiGraphics.blitSprite(BACKGROUND_SPRITE, x, y, this.backgroundWidth(), this.backgroundHeight());
        int index = 0;
        for (int row = 0; row < this.gridSizeY(); row++) {
            for (int col = 0; col < this.gridSizeX(); col++) {
                if (index >= this.items.size()) {
                    break;
                }
                int slotX = x + col * 18 + 1;
                int slotY = y + row * 20 + 1;
                guiGraphics.blitSprite(SLOT_SPRITE, slotX, slotY, 18, 20);
                guiGraphics.renderItem(this.items.get(index), slotX + 1, slotY + 1, index);
                guiGraphics.renderItemDecorations(font, this.items.get(index), slotX + 1, slotY + 1);
                index++;
            }
        }
    }

    private int backgroundHeight() {
        return this.gridSizeY() * 20 + 2;
    }

    private int gridSizeY() {
        if (this.items.isEmpty()) {
            return 0;
        }
        return Mth.ceil(this.items.size() / (double) this.gridSizeX());
    }

    private int gridSizeX() {
        if (this.items.isEmpty()) {
            return 1;
        }
        return Mth.ceil(Math.sqrt(this.items.size()));
    }
}
