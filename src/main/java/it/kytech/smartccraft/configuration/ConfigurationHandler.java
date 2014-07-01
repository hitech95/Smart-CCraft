package it.kytech.smartccraft.configuration;

import it.kytech.smartccraft.reference.Energy;
import it.kytech.smartccraft.reference.Settings;
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

            //Assembler
            Settings.storageAssembler = configuration.get(Settings.ENERGY_CATEGORY, Settings.ENERGY_PATH_STORAGE_ASSEMBLER, Energy.DEFAULT_STORAGE_ASSEMBLER, "Energy Stored on Assembler").getInt();

            //Charge Station
            Settings.storageChargeStation = configuration.get(Settings.ENERGY_CATEGORY, Settings.ENERGY_PATH_STORAGE_CHARGE_STATION, Energy.DEFAULT_STORAGE_CHARGE_STATION, "Energy Stored on Charge Station MK1").getInt();
            Settings.ratioChargeStation = configuration.get(Settings.ENERGY_CATEGORY, Settings.ENERGY_PATH_RATIO_CHARGE_STATION, Energy.DEFAULT_RATIO_CHARGE_STATION, "Energy ration on Charge Station MK1").getInt();


        } catch (Exception e) {
            LogHelper.error("Error Occoured, Report this to the modder!");
            e.printStackTrace();
        } finally {
            configuration.save();
        }

    }
}
