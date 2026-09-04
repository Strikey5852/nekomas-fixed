package net.greenjab.nekomasfixed.registry.registries;

import net.greenjab.nekomasfixed.NekomasFixed;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

/**
 * Keys for custom loot tables referenced from code (worldgen feather-rolls,
 * clam pearl rolls, etc.). The tables themselves live in
 * data/nekomasfixed/loot_table/...
 */
public final class LootTableRegistry {

    public static final ResourceKey<LootTable> CLAM_LOOT_TABLE =
            ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE,
                    NekomasFixed.id("gameplay/clam"));

    private LootTableRegistry() {
    }
}