package com.zundrel.wrenchable.wrench;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public interface EntityWrenchable {
    void onWrenched(World world, PlayerEntity player, EntityHitResult result);
}
