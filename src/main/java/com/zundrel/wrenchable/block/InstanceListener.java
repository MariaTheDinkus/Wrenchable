package com.zundrel.wrenchable.block;

import com.zundrel.wrenchable.wrench.Wrenchable;
import net.minecraft.block.Block;

public abstract class InstanceListener implements Wrenchable {
    private Class block;

    public InstanceListener(Class block) {
        this.block = block;
    }

    public Class getBlock() {
        return block;
    }
}
