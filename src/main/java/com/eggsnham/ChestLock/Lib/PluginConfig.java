package com.eggsnham.ChestLock.Lib;

import org.bukkit.plugin.Plugin;

import java.io.File;

public class PluginConfig {
    Boolean debug;

    public PluginConfig(Plugin plugin)
    {
        File config = new File(plugin.getDataFolder() + "/config.yml");

        FileConfig fileConfig = new FileConfig(plugin);
        this.debug = Boolean.getBoolean(fileConfig.readFromYml(config, "development.debug"));
    }
}
