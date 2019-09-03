package com.zundrel.wrenchable.wrench;

import com.zundrel.wrenchable.WrenchableRegistry;
import net.minecraft.item.Item;

public class WrenchUtilities {
    public static Wrench getWrench(Item item) {
        if (item instanceof Wrench)
            return (Wrench) item;
        else if (WrenchableRegistry.isWrench(item))
            return WrenchableRegistry.getWrench(item);

        return null;
    }

    public static boolean isWrench(Item item) {
        return item instanceof Wrench || WrenchableRegistry.isWrench(item);
    }
}
