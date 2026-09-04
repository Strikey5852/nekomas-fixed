package net.greenjab.nekomasfixed.registry.registries;

import net.greenjab.nekomasfixed.NekomasFixed;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;

public class ComponentRegistry {

    public static final DataComponentType<Integer> CLAM_STATE = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE, NekomasFixed.id("clam_state"),
            DataComponentType.<Integer>builder()
                    .persistent(ExtraCodecs.intRange(0, 3))
                    .networkSynchronized(ByteBufCodecs.INT)
                    .build());

    public static void registerComponents() {
        NekomasFixed.LOGGER.info("Registering components");
    }
}
