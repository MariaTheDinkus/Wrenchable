package com.zundrel.wrenchable.block;

import net.minecraft.state.property.Property;

public abstract class PropertyListener implements BlockWrenchable {
    private Property property;

    public PropertyListener(Property property) {
        this.property = property;
    }

    public Property getProperty() {
        return property;
    }
}
