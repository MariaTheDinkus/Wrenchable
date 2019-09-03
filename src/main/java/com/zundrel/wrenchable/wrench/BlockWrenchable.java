package com.zundrel.wrenchable.wrench;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public interface BlockWrenchable {
    /**
     * This method is called when a block is wrenched.
     * @param world An instance of the world where this block was wrenched.
     * @param player The player who wrenched this block.
     * @param result Information about the block that was wrenched.
     * @author Zundrel
     */
    void onWrenched(World world, PlayerEntity player, BlockHitResult result);
}
