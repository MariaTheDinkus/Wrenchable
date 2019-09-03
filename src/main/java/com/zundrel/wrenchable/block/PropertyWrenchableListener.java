package com.zundrel.wrenchable.block;

import com.zundrel.wrenchable.wrench.Wrenchable;
import net.minecraft.state.property.Property;

public abstract class PropertyWrenchableListener implements Wrenchable {
    private Property property;

    public PropertyWrenchableListener(Property property) {
        this.property = property;
    }

    public Property getProperty() {
        return property;
    }
}
