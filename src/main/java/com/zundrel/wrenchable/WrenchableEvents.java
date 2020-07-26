package com.zundrel.wrenchable;

import com.zundrel.wrenchable.block.BlockListener;
import com.zundrel.wrenchable.block.PropertyListener;
import com.zundrel.wrenchable.entity.EntityWrenchable;
import com.zundrel.wrenchable.wrench.Wrench;
import com.zundrel.wrenchable.block.BlockWrenchable;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class WrenchableEvents {
    public static void init() {
        UseBlockCallback.EVENT.register((playerEntity, world, hand, blockHitResult) -> {
            ItemStack heldStack = playerEntity.getStackInHand(hand);
            BlockPos pos = blockHitResult.getBlockPos();
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (!heldStack.isEmpty() && heldStack.getItem() == Items.SLIME_BALL && world.getBlockState(pos).getBlock() == Blocks.PISTON) {
                if (!world.getBlockState(pos).get(Properties.EXTENDED)) {
                    world.setBlockState(pos, Blocks.STICKY_PISTON.getDefaultState().with(Properties.FACING, world.getBlockState(pos).get(Properties.FACING)));
                    world.playSound(null, pos, SoundEvents.ENTITY_SLIME_SQUISH, SoundCategory.BLOCKS, 1, 1F);
                    if (!playerEntity.isCreative())
                        heldStack.decrement(1);
                }
            }

            if (!heldStack.isEmpty() && WrenchableUtilities.isWrench(heldStack.getItem())) {
                Wrench wrench = WrenchableUtilities.getWrench(heldStack.getItem());
                
                if (world.getBlockState(pos).getBlock() instanceof BlockWrenchable) {
                    wrench.onBlockWrenched(world, heldStack, playerEntity, hand, blockHitResult);
                    ((BlockWrenchable) world.getBlockState(pos).getBlock()).onWrenched(world, playerEntity, blockHitResult);

                    if (blockEntity instanceof BlockWrenchable) {
                        ((BlockWrenchable) blockEntity).onWrenched(world, playerEntity, blockHitResult);
                        wrench.onBlockEntityWrenched(world, heldStack, playerEntity, hand, blockEntity, blockHitResult);
                    }

                    return ActionResult.SUCCESS;
                } else if (blockEntity instanceof BlockWrenchable) {
                    wrench.onBlockWrenched(world, heldStack, playerEntity, hand, blockHitResult);
                    wrench.onBlockEntityWrenched(world, heldStack, playerEntity, hand, blockEntity, blockHitResult);
                    ((BlockWrenchable) blockEntity).onWrenched(world, playerEntity, blockHitResult);

                    return ActionResult.SUCCESS;
                } else if (WrenchableRegistry.isBlockWrenchable(world.getBlockState(pos).getBlock())) {
                    BlockListener wrenchable = WrenchableRegistry.getBlockWrenchable(world.getBlockState(pos).getBlock());

                    wrench.onBlockWrenched(world, heldStack, playerEntity, hand, blockHitResult);
                    wrenchable.onWrenched(world, playerEntity, blockHitResult);

                    return ActionResult.SUCCESS;
                } if (WrenchableRegistry.isBlockInstanceWrenchable(world.getBlockState(pos).getBlock())) {
                    wrench.onBlockWrenched(world, heldStack, playerEntity, hand, blockHitResult);
                    WrenchableRegistry.getBlockInstanceWrenchable(world.getBlockState(pos).getBlock()).onWrenched(world, playerEntity, blockHitResult);

                    return ActionResult.SUCCESS;
                } else {
                    for (PropertyListener wrenchable : WrenchableRegistry.PROPERTY_LISTENERS) {
                        if (world.getBlockState(pos).contains(wrenchable.getProperty())) {
                            wrench.onBlockWrenched(world, heldStack, playerEntity, hand, blockHitResult);
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
                    wrench.onEntityWrenched(world, heldStack, playerEntity, hand, entityHitResult);
                    ((EntityWrenchable) entity).onWrenched(world, playerEntity, entityHitResult);
                } else if (WrenchableRegistry.isEntityTypeWrenchable(entity)) {
                    wrench.onEntityWrenched(world, heldStack, playerEntity, hand, entityHitResult);
                    WrenchableRegistry.getEntityTypeWrenchable(entity).onWrenched(world, playerEntity, entityHitResult);
                }
            }

            return ActionResult.PASS;
        }));
    }
}
