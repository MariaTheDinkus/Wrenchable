package com.zundrel.wrenchable.block;

import com.zundrel.wrenchable.wrench.BlockWrenchable;
import net.minecraft.block.Block;

public abstract class BlockListener implements BlockWrenchable {
    private Block block;

    public BlockListener(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }
}
