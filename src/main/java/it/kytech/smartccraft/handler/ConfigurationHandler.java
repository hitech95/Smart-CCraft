package it.kytech.smartccraft.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import it.kytech.smartccraft.reference.Energy;
import it.kytech.smartccraft.reference.Integration;
import it.kytech.smartccraft.reference.Reference;
import it.kytech.smartccraft.reference.Settings;
import it.kytech.smartccraft.util.LogHelper;
import net.minecraftforge.common.config.Configuration;
import sun.rmi.runtime.Log;

import java.io.File;

/**
 * Created by M2K on 29/06/2014.
 */
public class ConfigurationHandler {
    public static Configuration configuration;

    public static void init(File configurationFile) {
        if (configuration == null) {
            configuration = new Configuration(configurationFile);
            configuration.load();

            loadConfiguration();
        }
    }

    public static void loadConfiguration() {
        try {
            //General
            configuration.setCategoryComment(Settings.GENERAL_CATEGORY, "General options");
            configuration.setCategoryRequiresMcRestart(Settings.GENERAL_CATEGORY, false);
            configuration.setCategoryRequiresWorldRestart(Settings.GENERAL_CATEGORY, true);

            //Integration
            configuration.setCategoryComment(Settings.INTEGRATION_CATEGORY, "Integration Options, Require MC restart");
            configuration.setCategoryRequiresMcRestart(Settings.INTEGRATION_CATEGORY, true);
            configuration.setCategoryRequiresWorldRestart(Settings.INTEGRATION_CATEGORY, true);

            Settings.integrateIC2 = configuration.get(Settings.INTEGRATION_CATEGORY, Settings.INTEGRATION_IC2, Integration.DEFAULT_INTEGRATION_IC2, "Integrate Industrialcraft Machine Block").getBoolean();
            Settings.integrateBC = configuration.get(Settings.INTEGRATION_CATEGORY, Settings.INTEGRATION_BC, Integration.DEFAULT_INTEGRATION_BC, "Integrate Buildcraft Iron Gear").getBoolean();
            Settings.integrateTE = configuration.get(Settings.INTEGRATION_CATEGORY, Settings.INTEGRATION_TE, Integration.DEFAULT_INTEGRATION_TE, "Integrate Thermal Expansion Machine Frame").getBoolean();
            Settings.integrateMFR = configuration.get(Settings.INTEGRATION_CATEGORY, Settings.INTEGRATION_MFR, Integration.DEFAULT_INTEGRATION_MFR, "Add a Advanced Factory Machine Block, craft with iron instead of stone").getBoolean();

            //Energy
            configuration.setCategoryComment(Settings.ENERGY_CATEGORY, "Energy Options, Require World restart");
            configuration.setCategoryRequiresMcRestart(Settings.ENERGY_CATEGORY, false);
            configuration.setCategoryRequiresWorldRestart(Settings.ENERGY_CATEGORY, true);

            //Assembler
            Settings.storageAssembler = configuration.get(Settings.ENERGY_CATEGORY, Settings.ENERGY_PATH_STORAGE_ASSEMBLER, Energy.DEFAULT_STORAGE_ASSEMBLER, "Energy Stored on Assembler").getInt();

            //Charge Station
            Settings.storageChargeStation = configuration.get(Settings.ENERGY_CATEGORY, Settings.ENERGY_PATH_STORAGE_CHARGE_STATION, Energy.DEFAULT_STORAGE_CHARGE_STATION, "Energy Stored on Charge Station MK1").getInt();
            Settings.ratioChargeStation = configuration.get(Settings.ENERGY_CATEGORY, Settings.ENERGY_PATH_RATIO_CHARGE_STATION, Energy.DEFAULT_RATIO_CHARGE_STATION, "Energy ratio on Charge Station MK1").getInt();

        } catch (Exception e) {
            LogHelper.error("Config File not found...");
        } finally {
            if (configuration.hasChanged()) {
                configuration.save();
            }
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            loadConfiguration();
        }
    }
}
