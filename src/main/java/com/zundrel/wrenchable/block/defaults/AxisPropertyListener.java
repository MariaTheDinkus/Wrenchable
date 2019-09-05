package com.zundrel.wrenchable.block.defaults;

import com.zundrel.wrenchable.block.PropertyListener;
import grondag.fermion.modkeys.api.ModKeys;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class AxisPropertyListener extends PropertyListener {
    public AxisPropertyListener() {
        super(Properties.AXIS);
    }

    @Override
    public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (ModKeys.isAltPressed(player)) {
            Direction.Axis axis = Direction.Axis.X;

            if (result.getSide() == Direction.UP || result.getSide() == Direction.DOWN)
                axis = Direction.Axis.Y;
            else if (result.getSide() == Direction.NORTH || result.getSide() == Direction.SOUTH)
                axis = Direction.Axis.Z;

            world.setBlockState(pos, state.with(Properties.AXIS, axis));
            world.updateNeighbor(pos, block, pos);
            return;
        }

        world.setBlockState(pos, state.cycle(Properties.AXIS));
        world.updateNeighbor(pos, block, pos);
        return;
    }
}
