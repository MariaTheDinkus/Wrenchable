package com.zundrel.wrenchable.block.defaults;

import com.zundrel.wrenchable.block.PropertyListener;
import grondag.fermion.modkeys.api.ModKeys;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class FacingPropertyListener extends PropertyListener {
    public FacingPropertyListener(Property property) {
        super(property);
    }

    @Override
    public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (getProperty() == Properties.FACING) {
            Direction direction = state.get(Properties.FACING);

            if (ModKeys.isAltPressed(player)) {
                world.setBlockState(pos, state.with(Properties.FACING, result.getSide()));
                world.updateNeighbor(pos, block, pos);
                return;
            }

            if (direction == Direction.UP || direction == Direction.DOWN) {
                world.setBlockState(pos, state.with(Properties.FACING, direction.getOpposite()));
                world.updateNeighbor(pos, block, pos);

                return;
            }
        } else if (getProperty() == Properties.HORIZONTAL_FACING) {
            if (ModKeys.isAltPressed(player) && result.getSide() != Direction.UP && result.getSide() != Direction.DOWN) {
                if (!state.with(Properties.HORIZONTAL_FACING, result.getSide()).canPlaceAt(world, pos))
                    return;

                world.setBlockState(pos, state.with(Properties.HORIZONTAL_FACING, result.getSide()));
                world.updateNeighbor(pos, block, pos);
                return;
            }
        }

        if (player.isSneaking()) {
            if (!state.rotate(BlockRotation.COUNTERCLOCKWISE_90).canPlaceAt(world, pos))
                return;

            world.setBlockState(pos, state.rotate(BlockRotation.COUNTERCLOCKWISE_90));
            world.updateNeighbor(pos, block, pos);
        } else {
            if (!state.rotate(BlockRotation.CLOCKWISE_90).canPlaceAt(world, pos))
                return;

            world.setBlockState(pos, state.rotate(BlockRotation.CLOCKWISE_90));
            world.updateNeighbor(pos, block, pos);
        }
    }
}
