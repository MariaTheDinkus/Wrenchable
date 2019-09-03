package com.zundrel.wrenchable;

import com.zundrel.wrenchable.block.BlockListener;
import com.zundrel.wrenchable.block.PropertyListener;
import com.zundrel.wrenchable.wrench.EntityWrenchable;
import com.zundrel.wrenchable.wrench.Wrench;
import com.zundrel.wrenchable.wrench.WrenchUtilities;
import com.zundrel.wrenchable.wrench.BlockWrenchable;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.util.ActionResult;

public class WrenchableEvents {
    public static void init() {
        UseBlockCallback.EVENT.register((playerEntity, world, hand, blockHitResult) -> {
            if (!playerEntity.getStackInHand(hand).isEmpty() && WrenchUtilities.isWrench(playerEntity.getStackInHand(hand).getItem())) {
                if (world.getBlockState(blockHitResult.getBlockPos()).getBlock() instanceof BlockWrenchable) {
                    Wrench wrench = WrenchUtilities.getWrench(playerEntity.getStackInHand(hand).getItem());

                    wrench.onWrenched(world, playerEntity.getStackInHand(hand), playerEntity, blockHitResult);
                    ((BlockWrenchable) world.getBlockState(blockHitResult.getBlockPos()).getBlock()).onWrenched(world, playerEntity, blockHitResult);

                    if (world.getBlockEntity(blockHitResult.getBlockPos()) != null && world.getBlockEntity(blockHitResult.getBlockPos()) instanceof BlockWrenchable) {
                        ((BlockWrenchable) world.getBlockEntity(blockHitResult.getBlockPos())).onWrenched(world, playerEntity, blockHitResult);
                    }

                    return ActionResult.SUCCESS;
                } else if (world.getBlockEntity(blockHitResult.getBlockPos()) != null && world.getBlockEntity(blockHitResult.getBlockPos()) instanceof BlockWrenchable) {
                    Wrench wrench = WrenchUtilities.getWrench(playerEntity.getStackInHand(hand).getItem());

                    wrench.onWrenched(world, playerEntity.getStackInHand(hand), playerEntity, blockHitResult);
                    ((BlockWrenchable) world.getBlockEntity(blockHitResult.getBlockPos())).onWrenched(world, playerEntity, blockHitResult);

                    return ActionResult.SUCCESS;
                } else if (WrenchableRegistry.isBlockWrenchable(world.getBlockState(blockHitResult.getBlockPos()).getBlock())) {
                    BlockListener wrenchable = WrenchableRegistry.getBlockWrenchable(world.getBlockState(blockHitResult.getBlockPos()).getBlock());
                    Wrench wrench = WrenchUtilities.getWrench(playerEntity.getStackInHand(hand).getItem());

                    wrench.onWrenched(world, playerEntity.getStackInHand(hand), playerEntity, blockHitResult);

                    wrenchable.onWrenched(world, playerEntity, blockHitResult);

                    return ActionResult.SUCCESS;
                } if (WrenchableRegistry.isBlockInstanceWrenchable(world.getBlockState(blockHitResult.getBlockPos()).getBlock())) {
                    Wrench wrench = WrenchUtilities.getWrench(playerEntity.getStackInHand(hand).getItem());

                    wrench.onWrenched(world, playerEntity.getStackInHand(hand), playerEntity, blockHitResult);
                    WrenchableRegistry.getBlockInstanceWrenchable(world.getBlockState(blockHitResult.getBlockPos()).getBlock()).onWrenched(world, playerEntity, blockHitResult);

                    return ActionResult.SUCCESS;
                } else {
                    for (PropertyListener wrenchable : WrenchableRegistry.PROPERTY_LISTENERS) {
                        if (world.getBlockState(blockHitResult.getBlockPos()).contains(wrenchable.getProperty())) {
                            Wrench wrench = WrenchUtilities.getWrench(playerEntity.getStackInHand(hand).getItem());

                            wrench.onWrenched(world, playerEntity.getStackInHand(hand), playerEntity, blockHitResult);

                            wrenchable.onWrenched(world, playerEntity, blockHitResult);

                            return ActionResult.SUCCESS;
                        }
                    }
                }
            }

            return ActionResult.PASS;
        });

        UseEntityCallback.EVENT.register(((playerEntity, world, hand, entity, entityHitResult) -> {
            if (!playerEntity.getStackInHand(hand).isEmpty() && WrenchUtilities.isWrench(playerEntity.getStackInHand(hand).getItem())) {
                if (entity instanceof EntityWrenchable) {
                    WrenchUtilities.getWrench(playerEntity.getStackInHand(hand).getItem());
                    ((EntityWrenchable) entity).onWrenched(world, playerEntity, entityHitResult);
                } else if (WrenchableRegistry.isEntityTypeWrenchable(entity)) {
                    WrenchUtilities.getWrench(playerEntity.getStackInHand(hand).getItem());
                    WrenchableRegistry.getEntityTypeWrenchable(entity).onWrenched(world, playerEntity, entityHitResult);
                }
            }

            return ActionResult.PASS;
        }));
    }
}
