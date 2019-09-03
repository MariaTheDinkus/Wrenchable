package com.zundrel.wrenchable.block.defaults;

import com.zundrel.wrenchable.block.InstanceListener;
import grondag.fermion.modkeys.api.ModKeys;
import net.minecraft.block.*;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class SignInstanceListener extends InstanceListener {
    public SignInstanceListener() {
        super(AbstractSignBlock.class);
    }

    @Override
    public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        SignBlockEntity blockEntity = (SignBlockEntity) world.getBlockEntity(pos);

        if (ModKeys.isControlPressed(player) && world.getBlockEntity(result.getBlockPos()) instanceof SignBlockEntity) {
            blockEntity.setEditor(player);
            blockEntity.setEditable(true);
            player.openEditSignScreen((SignBlockEntity) world.getBlockEntity(result.getBlockPos()));
            return;
        }

        if (block instanceof SignBlock) {
            if (ModKeys.isAltPressed(player)) {
                world.setBlockState(pos, state.with(Properties.ROTATION, MathHelper.floor((double)((180.0F + player.yaw) * 16.0F / 360.0F) + 0.5D) & 15));
                world.updateNeighbor(pos, block, pos);
                return;
            }

            if (player.isSneaking()) {
                if (state.get(Properties.ROTATION) > 0) {
                    world.setBlockState(pos, state.with(Properties.ROTATION, state.get(Properties.ROTATION) - 1));
                } else {
                    world.setBlockState(pos, state.with(Properties.ROTATION, 15));
                }

                world.updateNeighbor(pos, block, pos);
                return;
            } else {
                if (state.get(Properties.ROTATION) < 15) {
                    world.setBlockState(pos, state.with(Properties.ROTATION, state.get(Properties.ROTATION) + 1));
                } else {
                    world.setBlockState(pos, state.with(Properties.ROTATION, 0));
                }

                world.updateNeighbor(pos, block, pos);
                return;
            }
        } else if (block instanceof WallSignBlock) {
            if (player.isSneaking()) {
                if (!state.rotate(BlockRotation.COUNTERCLOCKWISE_90).canPlaceAt(world, pos))
                    return;

                world.setBlockState(pos, state.rotate(BlockRotation.COUNTERCLOCKWISE_90));

                world.updateNeighbor(pos, block, pos);
                return;
            } else {
                if (!state.rotate(BlockRotation.CLOCKWISE_90).canPlaceAt(world, pos))
                    return;

                world.setBlockState(pos, state.rotate(BlockRotation.CLOCKWISE_90));

                world.updateNeighbor(pos, block, pos);
                return;
            }
        }
    }
}
