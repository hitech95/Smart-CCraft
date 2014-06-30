package it.kytech.smartccraft.configuration;

import it.kytech.smartccraft.util.LogHelper;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by M2K on 29/06/2014.
 */
public class ConfigurationHandler {
    public static void init(File configurationFile) {
        Configuration configuration = new Configuration(configurationFile);

        try {
            configuration.load();


        } catch (Exception e) {
            LogHelper.error("Error Occoured, Report this to the modder!");
            e.printStackTrace();
        } finally {
            configuration.save();
        }

    }
}
