package com.zundrel.wrenchable.block.defaults;

import com.zundrel.wrenchable.block.InstanceListener;
import grondag.fermion.modkeys.api.ModKeys;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class EndPortalFrameInstanceListener extends InstanceListener {
    public EndPortalFrameInstanceListener() {
        super(EndPortalFrameBlock.class);
    }

    @Override
    public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
        BlockPos pos = result.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        if (ModKeys.isControlPressed(player) && state.get(Properties.EYE)) {
            world.setBlockState(pos, state.with(Properties.EYE, false));
            world.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1, 0.8F);
            if (!player.isCreative())
                player.inventory.offerOrDrop(world, new ItemStack(Items.ENDER_EYE));

            return;
        }

        if (ModKeys.isAltPressed(player) && result.getSide() != Direction.UP && result.getSide() != Direction.DOWN) {
            if (!state.with(Properties.HORIZONTAL_FACING, result.getSide()).canPlaceAt(world, pos))
                return;

            world.setBlockState(pos, state.with(Properties.HORIZONTAL_FACING, result.getSide()));
            world.updateNeighbor(pos, block, pos);
            return;
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
