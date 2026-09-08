package net.greenjab.nekomasfixed.registry.item;

import net.minecraft.world.item.Item;

// 1.21.1 note: vanilla DyeItem requires a DyeColor, which only has the 16 standard
// colours (no amber/aqua/indigo/maroon). Ancient dyes are plain items used purely in
// crafting, so they do not extend DyeItem.
public class ModDyeItems extends Item {
    public ModDyeItems(Item.Properties settings) {
        super(settings);
    }
}