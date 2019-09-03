package com.zundrel.wrenchable.block.defaults;

import com.zundrel.wrenchable.block.PropertyListener;
import grondag.fermion.modkeys.api.ModKeys;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SkullBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class RotationPropertyListener extends PropertyListener {
    public RotationPropertyListener() {
        super(Properties.ROTATION);
    }

    @Override
    public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (ModKeys.isAltPressed(player)) {
            if (block instanceof SkullBlock)
                world.setBlockState(pos, state.with(Properties.ROTATION, MathHelper.floor((double)((player.yaw) * 16.0F / 360.0F) + 0.5D) & 15));
            else
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
    }
}
