package com.zundrel.wrenchable.wrench;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public interface BlockWrenchable {
    void onWrenched(World world, PlayerEntity player, BlockHitResult result);
}
