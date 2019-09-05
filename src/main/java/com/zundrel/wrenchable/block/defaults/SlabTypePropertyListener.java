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
import net.minecraft.world.World;

public class SlabTypePropertyListener extends PropertyListener {
    public SlabTypePropertyListener() {
        super(Properties.SLAB_TYPE);
    }

    @Override
    public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (state.get(Properties.SLAB_TYPE) == SlabType.BOTTOM) {
            world.setBlockState(pos, state.with(Properties.SLAB_TYPE, SlabType.TOP));
            world.updateNeighbor(pos, block, pos);
            return;
        } else if (state.get(Properties.SLAB_TYPE) == SlabType.TOP) {
            world.setBlockState(pos, state.with(Properties.SLAB_TYPE, SlabType.BOTTOM));
            world.updateNeighbor(pos, block, pos);
            return;
        }
    }
}
