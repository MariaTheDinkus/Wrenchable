package com.zundrel.wrenchable;

import com.zundrel.wrenchable.block.BlockListener;
import com.zundrel.wrenchable.block.PropertyListener;
import com.zundrel.wrenchable.wrench.EntityWrenchable;
import com.zundrel.wrenchable.wrench.Wrench;
import com.zundrel.wrenchable.wrench.WrenchableUtilities;
import com.zundrel.wrenchable.wrench.BlockWrenchable;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class WrenchableEvents {
    public static void init() {
        UseBlockCallback.EVENT.register((playerEntity, world, hand, blockHitResult) -> {
            ItemStack heldStack = playerEntity.getStackInHand(hand);
            BlockPos pos = blockHitResult.getBlockPos();
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (!heldStack.isEmpty() && WrenchableUtilities.isWrench(heldStack.getItem())) {
                Wrench wrench = WrenchableUtilities.getWrench(heldStack.getItem());
                
                if (world.getBlockState(pos).getBlock() instanceof BlockWrenchable) {
                    wrench.onBlockWrenched(world, heldStack, playerEntity, blockHitResult);
                    ((BlockWrenchable) world.getBlockState(pos).getBlock()).onWrenched(world, playerEntity, blockHitResult);

                    if (blockEntity instanceof BlockWrenchable) {
                        ((BlockWrenchable) blockEntity).onWrenched(world, playerEntity, blockHitResult);
                        wrench.onBlockEntityWrenched(world, heldStack, playerEntity, blockEntity, blockHitResult);
                    }

                    return ActionResult.SUCCESS;
                } else if (blockEntity instanceof BlockWrenchable) {
                    wrench.onBlockWrenched(world, heldStack, playerEntity, blockHitResult);
                    wrench.onBlockEntityWrenched(world, heldStack, playerEntity, blockEntity, blockHitResult);
                    ((BlockWrenchable) blockEntity).onWrenched(world, playerEntity, blockHitResult);

                    return ActionResult.SUCCESS;
                } else if (WrenchableRegistry.isBlockWrenchable(world.getBlockState(pos).getBlock())) {
                    BlockListener wrenchable = WrenchableRegistry.getBlockWrenchable(world.getBlockState(pos).getBlock());

                    wrench.onBlockWrenched(world, heldStack, playerEntity, blockHitResult);
                    wrenchable.onWrenched(world, playerEntity, blockHitResult);

                    return ActionResult.SUCCESS;
                } if (WrenchableRegistry.isBlockInstanceWrenchable(world.getBlockState(pos).getBlock())) {
                    wrench.onBlockWrenched(world, heldStack, playerEntity, blockHitResult);
                    WrenchableRegistry.getBlockInstanceWrenchable(world.getBlockState(pos).getBlock()).onWrenched(world, playerEntity, blockHitResult);

                    return ActionResult.SUCCESS;
                } else {
                    for (PropertyListener wrenchable : WrenchableRegistry.PROPERTY_LISTENERS) {
                        if (world.getBlockState(pos).contains(wrenchable.getProperty())) {
                            wrench.onBlockWrenched(world, heldStack, playerEntity, blockHitResult);
                            wrenchable.onWrenched(world, playerEntity, blockHitResult);

                            return ActionResult.SUCCESS;
                        }
                    }
                }
            }

            return ActionResult.PASS;
        });

        UseEntityCallback.EVENT.register(((playerEntity, world, hand, entity, entityHitResult) -> {
            ItemStack heldStack = playerEntity.getStackInHand(hand);

            if (!heldStack.isEmpty() && WrenchableUtilities.isWrench(heldStack.getItem())) {
                Wrench wrench = WrenchableUtilities.getWrench(heldStack.getItem());
                
                if (entity instanceof EntityWrenchable) {
                    wrench.onEntityWrenched(world, heldStack, playerEntity, entityHitResult);
                    ((EntityWrenchable) entity).onWrenched(world, playerEntity, entityHitResult);
                } else if (WrenchableRegistry.isEntityTypeWrenchable(entity)) {
                    wrench.onEntityWrenched(world, heldStack, playerEntity, entityHitResult);
                    WrenchableRegistry.getEntityTypeWrenchable(entity).onWrenched(world, playerEntity, entityHitResult);
                }
            }

            return ActionResult.PASS;
        }));
    }
}
