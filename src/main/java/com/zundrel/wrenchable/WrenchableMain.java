package com.zundrel.wrenchable;

import com.zundrel.wrenchable.block.BlockInstanceListener;
import com.zundrel.wrenchable.block.PropertyListener;
import com.zundrel.wrenchable.block.defaults.FacingPropertyListener;
import com.zundrel.wrenchable.block.defaults.RotationPropertyListener;
import com.zundrel.wrenchable.block.defaults.SignPropertyListener;
import com.zundrel.wrenchable.wrench.WrenchListener;
import grondag.fermion.modkeys.api.ModKeys;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.*;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class WrenchableMain implements ModInitializer {
    public static String MODID = "wrenchable";

    public static BlockInstanceListener SIGN_LISTENER;

    public static PropertyListener ROTATION_LISTENER;
    public static PropertyListener FACING_LISTENER;
    public static PropertyListener HORIZONTAL_FACING_LISTENER;

	@Override
	public void onInitialize() {
	    SIGN_LISTENER = Registry.register(WrenchableRegistry.BLOCK_INSTANCE_LISTENERS, new Identifier(MODID, "sign"), new SignPropertyListener());

	    ROTATION_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "rotation"), new RotationPropertyListener());

	    FACING_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "facing"), new FacingPropertyListener(Properties.FACING));

        HORIZONTAL_FACING_LISTENER = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "horizontal_facing"), new FacingPropertyListener(Properties.HORIZONTAL_FACING));

        WrenchableEvents.init();
	}
}
