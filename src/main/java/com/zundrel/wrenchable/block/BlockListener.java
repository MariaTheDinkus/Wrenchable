package com.zundrel.wrenchable.block;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public abstract class BlockListener implements BlockWrenchable {
    private Block block;

    public BlockListener(Block block) {
        this.block = block;
    }

    public BlockListener(Identifier identifier) {
        this(Registry.BLOCK.get(identifier));
    }

    public Block getBlock() {
        return block;
    }
}
