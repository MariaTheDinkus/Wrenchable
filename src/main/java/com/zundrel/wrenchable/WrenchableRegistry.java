package com.zundrel.wrenchable;

import com.zundrel.wrenchable.block.InstanceListener;
import com.zundrel.wrenchable.block.BlockListener;
import com.zundrel.wrenchable.block.PropertyListener;
import com.zundrel.wrenchable.wrench.Wrench;
import com.zundrel.wrenchable.wrench.WrenchListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;

public class WrenchableRegistry {
    public static final DefaultedRegistry<BlockListener> BLOCK_LISTENERS = register("block_wrenchable", new DefaultedRegistry<>(""));
    public static final DefaultedRegistry<InstanceListener> BLOCK_INSTANCE_LISTENERS = register("block_instance_wrenchable", new DefaultedRegistry<>(""));
    public static final DefaultedRegistry<PropertyListener> PROPERTY_LISTENERS = register("property_wrenchable", new DefaultedRegistry<>(""));
    public static final DefaultedRegistry<WrenchListener> WRENCH_LISTENERS = register("wrench", new DefaultedRegistry<>(""));

    private static <T, R extends MutableRegistry<T>> R register(String string_1, R mutableRegistry_1) {
        Identifier identifier_1 = new Identifier(WrenchableMain.MODID, string_1);
        return Registry.REGISTRIES.add(identifier_1, mutableRegistry_1);
    }

    public static BlockListener findBlockWrenchable(Block block) {
        return BLOCK_LISTENERS.stream().filter(it -> it.getBlock().equals(block)).findAny().orElse(null);
    }

    public static boolean hasBlockWrenchable(Block block) {
        return findBlockWrenchable(block) != null;
    }

    public static InstanceListener findBlockInstanceWrenchable(Block block) {
        return BLOCK_INSTANCE_LISTENERS.stream().filter(it -> it.getBlock().isInstance(block)).findAny().orElse(null);
    }

    public static boolean isBlockInstanceWrenchable(Block block) {
        return findBlockInstanceWrenchable(block) != null;
    }

    public static PropertyListener findPropertyWrenchable(Property property) {
        return PROPERTY_LISTENERS.stream().filter(it -> it.getProperty().equals(property)).findAny().orElse(null);
    }

    public static boolean hasPropertyWrenchable(Property property) {
        return findPropertyWrenchable(property) != null;
    }

    public static Wrench getWrench(Item item) {
        return WRENCH_LISTENERS.stream().filter(it -> it.getItem().equals(item)).findAny().orElse(null);
    }

    public static boolean isWrench(Item item) {
        return getWrench(item) != null;
    }
}
