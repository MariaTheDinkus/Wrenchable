package com.zundrel.wrenchable;

import com.zundrel.wrenchable.block.BlockWrenchableListener;
import com.zundrel.wrenchable.block.PropertyWrenchableListener;
import com.zundrel.wrenchable.wrench.Wrench;
import com.zundrel.wrenchable.wrench.WrenchUtilities;
import com.zundrel.wrenchable.wrench.Wrenchable;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.util.ActionResult;

public class WrenchableEvents {
    public static void init() {
        UseBlockCallback.EVENT.register((playerEntity, world, hand, blockHitResult) -> {
            if (!playerEntity.getStackInHand(hand).isEmpty() && WrenchUtilities.isWrench(playerEntity.getStackInHand(hand).getItem())) {
                if (world.getBlockState(blockHitResult.getBlockPos()).getBlock() instanceof Wrenchable) {
                    Wrench wrench = WrenchUtilities.getWrench(playerEntity.getStackInHand(hand).getItem());

                    wrench.onWrenched(world, playerEntity.getStackInHand(hand), playerEntity, blockHitResult);
                    ((Wrenchable) world.getBlockState(blockHitResult.getBlockPos()).getBlock()).onWrenched(world, playerEntity, blockHitResult);

                    if (world.getBlockEntity(blockHitResult.getBlockPos()) != null && world.getBlockEntity(blockHitResult.getBlockPos()) instanceof Wrenchable) {
                        ((Wrenchable) world.getBlockEntity(blockHitResult.getBlockPos())).onWrenched(world, playerEntity, blockHitResult);
                    }

                    return ActionResult.SUCCESS;
                } else if (world.getBlockEntity(blockHitResult.getBlockPos()) != null && world.getBlockEntity(blockHitResult.getBlockPos()) instanceof Wrenchable) {
                    Wrench wrench = WrenchUtilities.getWrench(playerEntity.getStackInHand(hand).getItem());

                    wrench.onWrenched(world, playerEntity.getStackInHand(hand), playerEntity, blockHitResult);
                    ((Wrenchable) world.getBlockEntity(blockHitResult.getBlockPos())).onWrenched(world, playerEntity, blockHitResult);

                    return ActionResult.SUCCESS;
                } else if (WrenchableRegistry.hasBlockWrenchable(world.getBlockState(blockHitResult.getBlockPos()).getBlock())) {
                    BlockWrenchableListener wrenchable = WrenchableRegistry.findBlockWrenchable(world.getBlockState(blockHitResult.getBlockPos()).getBlock());
                    Wrench wrench = WrenchUtilities.getWrench(playerEntity.getStackInHand(hand).getItem());

                    wrench.onWrenched(world, playerEntity.getStackInHand(hand), playerEntity, blockHitResult);

                    wrenchable.onWrenched(world, playerEntity, blockHitResult);

                    return ActionResult.SUCCESS;
                } else {
                    for (PropertyWrenchableListener wrenchable : WrenchableRegistry.PROPERTY_LISTENERS) {
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
    }
}
