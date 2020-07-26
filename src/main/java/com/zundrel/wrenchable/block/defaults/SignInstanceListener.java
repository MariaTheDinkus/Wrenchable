package com.zundrel.wrenchable.block.defaults;

import com.zundrel.wrenchable.block.InstanceListener;
import com.zundrel.wrenchable.WrenchableUtilities;
import grondag.fermion.modkeys.api.ModKeys;
import net.minecraft.block.*;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
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

        if (ModKeys.isPrimaryPressed(player) && world.getBlockEntity(result.getBlockPos()) instanceof SignBlockEntity) {
            blockEntity.setEditor(player);
            if (world.isClient()) {
                blockEntity.setEditable(true);
            }
            player.openEditSignScreen((SignBlockEntity) world.getBlockEntity(result.getBlockPos()));
            return;
        }

        if (block instanceof SignBlock) {
            WrenchableUtilities.doRotationBehavior(world, player, result);
        } else if (block instanceof WallSignBlock) {
            WrenchableUtilities.doHorizontalFacingBehavior(world, player, result);
        }
    }
}
