package com.zundrel.wrenchable.block;

public abstract class InstanceListener implements BlockWrenchable {
    private Class block;

    public InstanceListener(Class block) {
        this.block = block;
    }

    public Class getBlock() {
        return block;
    }
}
