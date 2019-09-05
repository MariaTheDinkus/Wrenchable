package com.zundrel.wrenchable.config;

import com.zundrel.wrenchable.WrenchableMain;
import io.github.indicode.fabric.tinyconfig.ModConfig;

public class WrenchableConfig {
    public static ModConfig wrenchableConfig = new ModConfig(WrenchableMain.MODID);
    public static boolean pistonSlime = true;

    public static void init() {
        wrenchableConfig.configure(config -> {
            pistonSlime = config.getBool("pistonSlime", pistonSlime, "This controls whether or not slime can be placed on pistons to turn them into Sticky Pistons.");
        });
    }
}
