package net.greenjab.nekomasfixed.registry.entity;

import net.greenjab.nekomasfixed.registry.registries.EntityTypeRegistry;
import net.greenjab.nekomasfixed.registry.registries.ItemRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class BaobabBoat extends Boat {

    public BaobabBoat(Level level, double x, double y, double z) {
        this(EntityTypeRegistry.BAOBAB_BOAT, level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public BaobabBoat(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
        this.setVariant(Boat.Type.OAK);
    }

    @Override
    public @NonNull Item getDropItem() {
        return ItemRegistry.BAOBAB_BOAT;
    }
}
