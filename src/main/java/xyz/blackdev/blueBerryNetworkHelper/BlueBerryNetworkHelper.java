package xyz.blackdev.blueBerryNetworkHelper;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.blackdev.blueBerryNetworkHelper.utils.color.GradientLogger;

public final class BlueBerryNetworkHelper extends JavaPlugin {

    public static GradientLogger logger = new GradientLogger.Builder()
            .setPrefix("[BBNH] ")
            .setGradientPrefix(true)
            .build();
    @Override
    public void onEnable() {
        logger.log("Starting BlueBerryNetworkHelper...", "blue", "purple");
    }

    @Override
    public void onDisable() {
        logger.log("Strarting BlueBerryNetworkHelper...", "purple", "blue");
    }

}
