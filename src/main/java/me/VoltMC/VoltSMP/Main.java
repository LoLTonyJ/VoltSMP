package me.VoltMC.VoltSMP;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    static Main instance;

    @Override
    public void onEnable() {
        String version = this.getConfig().getString("Plugin Version");
        instance = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        System.out.println("VOLTSMP Main Plugin has been started. " + version);

    }

    @Override
    public void onDisable() {
        System.out.println("VOLTSMP has been disabled!");
    }
}
