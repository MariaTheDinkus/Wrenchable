package com.zundrel.wrenchable;

import com.zundrel.wrenchable.block.InstanceListener;
import com.zundrel.wrenchable.block.PropertyListener;
import com.zundrel.wrenchable.block.defaults.*;
import com.zundrel.wrenchable.config.WrenchableConfig;
import com.zundrel.wrenchable.wrench.WrenchListener;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WrenchableMain implements ModInitializer {
    public static String MODID = "wrenchable";

    public static InstanceListener DOOR_LISTENER;
    public static InstanceListener PISTON_LISTENER;
    public static InstanceListener SIGN_LISTENER;

    public static PropertyListener AXIS_LISTENER;
    public static PropertyListener FACING_LISTENER;
    public static PropertyListener HORIZONTAL_AXIS_LISTENER;
    public static PropertyListener HORIZONTAL_FACING_LISTENER;
    public static PropertyListener ROTATION_LISTENER;
    public static PropertyListener SLAB_TYPE_LISTENER;

	@Override
	public void onInitialize() {
	    WrenchableConfig.init();

	    DOOR_LISTENER = Registry.register(WrenchableRegistry.BLOCK_INSTANCE_LISTENERS, new Identifier(MODID, "door"), new DoorInstanceListener());

	    PISTON_LISTENER = Registry.register(WrenchableRegistry.BLOCK_INSTANCE_LISTENERS, new Identifier(MODID, "piston"), new PistonInstanceListener());

	    SIGN_LISTENER = Registry.register(WrenchableRegistry.BLOCK_INSTANCE_LISTENERS, new Identifier(MODID, "sign"), new SignInstanceListener());

        AXIS_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "axis"), new AxisPropertyListener());

        FACING_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "facing"), new FacingPropertyListener());

        HORIZONTAL_AXIS_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "horizontal_axis"), new HorizontalAxisPropertyListener());

        HORIZONTAL_FACING_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "horizontal_facing"), new HorizontalFacingPropertyListener());

	    ROTATION_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "rotation"), new RotationPropertyListener());

	    SLAB_TYPE_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "slab_type"), new SlabTypePropertyListener());

        WrenchableEvents.init();
	}
}
