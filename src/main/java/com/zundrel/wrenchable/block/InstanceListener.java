package com.zundrel.wrenchable.block;

import com.zundrel.wrenchable.wrench.BlockWrenchable;

public abstract class InstanceListener implements BlockWrenchable {
    private Class block;

    public InstanceListener(Class block) {
        this.block = block;
    }

    public Class getBlock() {
        return block;
    }
}
