package com.zundrel.wrenchable.wrench;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public interface EntityWrenchable {
    /**
     * This method is called when an entity is wrenched. Durability handling should be done in this method.
     * @param world An instance of the world where this entity was wrenched.
     * @param player The player who wrenched this entity.
     * @param result Information about the entity that was wrenched.
     * @author Zundrel
     */
    void onWrenched(World world, PlayerEntity player, EntityHitResult result);
}
