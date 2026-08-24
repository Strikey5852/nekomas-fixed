package net.greenjab.nekomasfixed.registry.entity;

import net.greenjab.nekomasfixed.registry.registries.EntityTypeRegistry;
import net.greenjab.nekomasfixed.registry.registries.ItemRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class BaobabChestBoat extends ChestBoat {

    public BaobabChestBoat(Level level, double x, double y, double z) {
        this(EntityTypeRegistry.BAOBAB_CHEST_BOAT, level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public BaobabChestBoat(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
        this.setVariant(Boat.Type.OAK);
    }

    @Override
    public @NonNull Item getDropItem() {
        return ItemRegistry.BAOBAB_CHEST_BOAT;
    }
}
