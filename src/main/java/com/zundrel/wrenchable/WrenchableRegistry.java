package com.zundrel.wrenchable;

import com.zundrel.wrenchable.block.BlockWrenchableListener;
import com.zundrel.wrenchable.block.PropertyWrenchableListener;
import net.minecraft.block.Block;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;

public class WrenchableRegistry {
    public static final DefaultedRegistry<BlockWrenchableListener> BLOCK_LISTENERS = register("block_wrenchable", new DefaultedRegistry<>("facing"));
    public static final DefaultedRegistry<PropertyWrenchableListener> PROPERTY_LISTENERS = register("property_wrenchable", new DefaultedRegistry<>("facing"));

    private static <T, R extends MutableRegistry<T>> R register(String string_1, R mutableRegistry_1) {
        Identifier identifier_1 = new Identifier(WrenchableMain.MODID, string_1);
        return Registry.REGISTRIES.add(identifier_1, mutableRegistry_1);
    }

    public static BlockWrenchableListener findBlockWrenchable(Block block) {
        return BLOCK_LISTENERS.stream().filter(it -> it.getBlock().equals(block)).findAny().orElse(null);
    }

    public static boolean hasBlockWrenchable(Block block) {
        return findBlockWrenchable(block) != null;
    }

    public static PropertyWrenchableListener findPropertyWrenchable(Property property) {
        return PROPERTY_LISTENERS.stream().filter(it -> it.getProperty().equals(property)).findAny().orElse(null);
    }

    public static boolean hasPropertyWrenchable(Property property) {
        return findPropertyWrenchable(property) != null;
    }
}
