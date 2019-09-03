package com.zundrel.wrenchable.block;

import com.zundrel.wrenchable.wrench.Wrenchable;
import net.minecraft.block.Block;

public abstract class BlockWrenchableListener implements Wrenchable {
    private Block block;

    public BlockWrenchableListener(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }
}
