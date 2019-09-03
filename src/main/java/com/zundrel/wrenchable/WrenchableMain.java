package com.zundrel.wrenchable;

import com.zundrel.wrenchable.block.PropertyWrenchableListener;
import grondag.fermion.modkeys.api.ModKeys;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.enums.ChestType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class WrenchableMain implements ModInitializer {
    public static String MODID = "wrenchable";

    public static PropertyWrenchableListener FACING;
    public static PropertyWrenchableListener HORIZONTAL_FACING;

	@Override
	public void onInitialize() {
        FACING = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "facing"), new PropertyWrenchableListener(Properties.FACING) {
            @Override
            public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
                BlockPos pos = result.getBlockPos();
                BlockState state = world.getBlockState(pos);
                Direction direction = state.get(Properties.FACING);
                Block block = state.getBlock();

                if (ModKeys.isAltPressed(player)) {
                    world.setBlockState(pos, state.with(Properties.FACING, result.getSide()));
                    world.updateNeighbor(pos, block, pos);
                    return;
                }

                if (player.isSneaking()) {
                    if (direction != Direction.UP && direction != Direction.DOWN) {
                        if (!state.rotate(BlockRotation.COUNTERCLOCKWISE_90).canPlaceAt(world, pos))
                            return;

                        world.setBlockState(pos, state.rotate(BlockRotation.COUNTERCLOCKWISE_90));
                        world.updateNeighbor(pos, block, pos);
                    } else {
                        world.setBlockState(pos, state.with(Properties.FACING, direction.getOpposite()));
                        world.updateNeighbor(pos, block, pos);
                    }
                } else {
                    if (direction != Direction.UP && direction != Direction.DOWN) {
                        if (!state.rotate(BlockRotation.CLOCKWISE_90).canPlaceAt(world, pos))
                            return;

                        world.setBlockState(pos, state.rotate(BlockRotation.CLOCKWISE_90));
                        world.updateNeighbor(pos, block, pos);
                    } else {
                        world.setBlockState(pos, state.with(Properties.FACING, direction.getOpposite()));
                        world.updateNeighbor(pos, block, pos);
                    }
                }
            }
        });

        HORIZONTAL_FACING = Registry.register(WrenchableRegistry.PROPERTY_LISTENERS, new Identifier(MODID, "horizontal_facing"), new PropertyWrenchableListener(Properties.HORIZONTAL_FACING) {
            @Override
            public void onWrenched(World world, PlayerEntity player, BlockHitResult result) {
                BlockPos pos = result.getBlockPos();
                BlockState state = world.getBlockState(pos);
                Block block = state.getBlock();

                if (block instanceof ChestBlock && state.get(Properties.CHEST_TYPE) != ChestType.SINGLE)
                    return;

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
        });

        WrenchableEvents.init();
	}
}
