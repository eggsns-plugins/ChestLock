package com.eggsnham.ChestLock.Lib;

import org.bukkit.plugin.Plugin;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class System {

    FileConfig fileConfig;
    Plugin plugin;
    public System(Plugin plugin, FileConfig fileConfig) {
        this.fileConfig = fileConfig;
        this.plugin = plugin;
    }

    public String getHostname()
    {
        String hostname = "Unknown";
        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
            return hostname;
        } catch(UnknownHostException ex) {
            fileConfig.writeToFile(new File(plugin.getDataFolder() + "/debug.log"), "============ Java Exception ============"
                    + String.valueOf(ex));
            return hostname;
        }
    }
}
