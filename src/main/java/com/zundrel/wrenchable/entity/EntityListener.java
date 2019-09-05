package com.zundrel.wrenchable.entity;

import net.minecraft.entity.EntityType;

public abstract class EntityListener implements EntityWrenchable {
    private EntityType type;

    public EntityListener(EntityType type) {
        this.type = type;
    }

    public EntityType getType() {
        return type;
    }
}
