package com.zundrel.wrenchable.wrench;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WrenchListener implements Wrench {
    private Item item;

    public WrenchListener(Item item) {
        this.item = item;
    }

    public WrenchListener(Identifier identifier) {
        this(Registry.ITEM.get(identifier));
    }

    public Item getItem() {
        return item;
    }
}
