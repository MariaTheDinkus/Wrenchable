package com.zundrel.wrenchable.wrench;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public interface Wrench {
    /**
     * This method is called when a block is wrenched. Durability handling should be done in this method.
     * @param world An instance of the world where this block was wrenched.
     * @param stack The wrench ItemStack which the player is currently holding.
     * @param player The player who wrenched this block.
     * @param result Information about the block that was wrenched.
     * @author Zundrel
     */
    default void onBlockWrenched(World world, ItemStack stack, PlayerEntity player, BlockHitResult result) {};

    /**
     * This method is called when a block is wrenched. Do not do durability handling in this method.
     * @param world An instance of the world where this block was wrenched.
     * @param stack The wrench ItemStack which the player is currently holding.
     * @param player The player who wrenched this block.
     * @param blockEntity The BlockEntity of the block that was wrenched.
     * @param result Information about the block that was wrenched.
     * @author Zundrel
     */
    default void onBlockEntityWrenched(World world, ItemStack stack, PlayerEntity player, BlockEntity blockEntity, BlockHitResult result) {};

    /**
     * This method is called when an entity is wrenched. Durability handling should be done in this method.
     * @param world An instance of the world where this entity was wrenched.
     * @param stack The wrench ItemStack which the player is currently holding.
     * @param player The player who wrenched this entity.
     * @param result Information about the entity that was wrenched.
     * @author Zundrel
     */
    default void onEntityWrenched(World world, ItemStack stack, PlayerEntity player, EntityHitResult result) {};
}
