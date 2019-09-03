package com.zundrel.wrenchable.wrench;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public interface Wrench {
    default void onWrenched(World world, ItemStack stack, PlayerEntity player, BlockHitResult result) {};
}
