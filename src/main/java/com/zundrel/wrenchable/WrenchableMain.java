package com.zundrel.wrenchable;

import com.zundrel.wrenchable.block.InstanceListener;
import com.zundrel.wrenchable.block.PropertyListener;
import com.zundrel.wrenchable.block.defaults.*;
import com.zundrel.wrenchable.wrench.WrenchListener;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WrenchableMain implements ModInitializer {
    public static String MODID = "wrenchable";

    public static InstanceListener DOOR_LISTENER;
    public static InstanceListener SIGN_LISTENER;
    public static InstanceListener END_PORTAL_FRAME_LISTENER;

    public static PropertyListener ROTATION_LISTENER;
    public static PropertyListener FACING_LISTENER;
    public static PropertyListener HORIZONTAL_FACING_LISTENER;

	@Override
	public void onInitialize() {
	    DOOR_LISTENER = Registry.register(WrenchableRegistry.BLOCK_INSTANCE_LISTENERS, new Identifier(MODID, "door"), new DoorInstanceListener());

	    SIGN_LISTENER = Registry.register(WrenchableRegistry.BLOCK_INSTANCE_LISTENERS, new Identifier(MODID, "sign"), new SignInstanceListener());

        END_PORTAL_FRAME_LISTENER = Registry.register(WrenchableRegistry.BLOCK_INSTANCE_LISTENERS, new Identifier(MODID, "eye"), new EndPortalFrameInstanceListener());

	    ROTATION_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "rotation"), new RotationPropertyListener());

	    FACING_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "facing"), new FacingPropertyListener());

        HORIZONTAL_FACING_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "horizontal_facing"), new HorizontalFacingPropertyListener());

        WrenchableEvents.init();
	}
}
