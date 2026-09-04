package net.greenjab.nekomasfixed.registry.registries;

import net.greenjab.nekomasfixed.NekomasFixed;
import net.greenjab.nekomasfixed.registry.entity.BaobabBoat;
import net.greenjab.nekomasfixed.registry.entity.BaobabChestBoat;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class EntityTypeRegistry {

    public static final EntityType<BaobabBoat> BAOBAB_BOAT = register(
            "baobab_boat",
            EntityType.Builder.<BaobabBoat>of(
                            BaobabBoat::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F).clientTrackingRange(10)
    );
    public static final EntityType<BaobabChestBoat> BAOBAB_CHEST_BOAT = register(
            "baobab_chest_boat",
            EntityType.Builder.<BaobabChestBoat>of(
                            BaobabChestBoat::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F).clientTrackingRange(10)
    );

    private static <T extends net.minecraft.world.entity.Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE,
                ResourceKey.create(Registries.ENTITY_TYPE, NekomasFixed.id(id)),
                builder.build(id));
    }

    public static void registerEntityType() {
        NekomasFixed.LOGGER.info("Registering entity types");
    }
}
