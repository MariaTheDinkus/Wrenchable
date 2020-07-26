package com.zundrel.wrenchable.block.defaults;

import com.zundrel.wrenchable.block.InstanceListener;
import com.zundrel.wrenchable.WrenchableUtilities;
import grondag.fermion.modkeys.api.ModKeys;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DoorInstanceListener extends InstanceListener {
    public DoorInstanceListener() {
        super(DoorBlock.class);
    }

    @Override
    public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (ModKeys.isPrimaryPressed(player)) {
            world.setBlockState(pos, state.cycle(Properties.DOOR_HINGE));
            world.updateNeighbor(pos, block, pos);
            return;
        }

        WrenchableUtilities.doHorizontalFacingBehavior(world, player, result);
    }
}
