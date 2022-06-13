package com.eggsnham.ChestLock;

import com.eggsnham.ChestLock.Lib.FileConfig;
import com.eggsnham.ChestLock.Lib.System;
import com.eggsnham.DebugLevel;
import com.eggsnham.DebugLogger;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class Main extends JavaPlugin
{
    FileConfig fileConfig = new FileConfig(this);
    System sys = new System(this, fileConfig);
    DebugLogger logger = new DebugLogger(new File(getDataFolder() + "/debug.log"));
    public void onEnable()
    {
        Server server = Bukkit.getServer();

        try {
            configureFiles();
        } catch(IOException ex) {
            getLogger().log(Level.SEVERE, String.valueOf(ex));
        }

        if(sys.getHostname().equals("MS-7693")) logger.log("Plugin started!", DebugLevel.INFO);

        this.getCommand("create-locked").setExecutor(new CreateLocked());
        this.getCommand("create-locked").setTabCompleter(new CreateLockedTab());

        server.getPluginManager().registerEvents(new onChestOpen(this), this);
        server.getPluginManager().registerEvents(new onChestPlace(this), this);
        server.getPluginManager().registerEvents(new onPlayerJoin(this), this);
    }

    public void configureFiles() throws IOException
    {
        File file = new File(this.getDataFolder() + "/config.yml");
        File debugFile = new File(this.getDataFolder() + "/debug.log");
        File folder = new File(this.getDataFolder() + "/");

        if(!folder.exists()) {
            folder.mkdir();
        }

        if(!file.exists()) {
            file.createNewFile();
        }

        if(!debugFile.exists()) {
            debugFile.createNewFile();

            fileConfig.writeToFile(debugFile, "==============System Info==============\n    Hostname: "
                    + sys.getHostname());
            fileConfig.writeToFile(debugFile, "==================End==================");
        }

        if(sys.getHostname().equals("MS-7693")) {
            fileConfig.writeToYml(file, "development.debug", true);
        } else {
            fileConfig.writeToYml(file, "development.debug", false);
        }
    }
}
