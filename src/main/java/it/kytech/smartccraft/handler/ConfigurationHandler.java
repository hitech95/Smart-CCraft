/**
 * This file is part of SmartCCraft
 *
 * Copyright (c) 2015 hitech95 <https://github.com/hitech95>
 * Copyright (c) contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.kytech.smartccraft.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import it.kytech.smartccraft.reference.Energy;
import it.kytech.smartccraft.reference.Integration;
import it.kytech.smartccraft.reference.Reference;
import it.kytech.smartccraft.reference.Settings;
import it.kytech.smartccraft.util.LogHelper;
import net.minecraftforge.common.config.Configuration;

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

            Settings.integrateBC = configuration.get(Settings.INTEGRATION_CATEGORY, Settings.INTEGRATION_BC, Integration.DEFAULT_INTEGRATION_BC, "Integrate Buildcraft Iron Gear").getBoolean();
            Settings.integrateTE = configuration.get(Settings.INTEGRATION_CATEGORY, Settings.INTEGRATION_TE, Integration.DEFAULT_INTEGRATION_TE, "Integrate Thermal Expansion Machine Frame").getBoolean();
            //Settings.integrateMFR = configuration.get(Settings.INTEGRATION_CATEGORY, Settings.INTEGRATION_MFR, Integration.DEFAULT_INTEGRATION_MFR, "Add a Advanced Factory Machine Block, craft with iron instead of stone").getBoolean();

            //Energy
            configuration.setCategoryComment(Settings.ENERGY_CATEGORY, "Energy Options, Require World restart");
            configuration.setCategoryRequiresMcRestart(Settings.ENERGY_CATEGORY, false);
            configuration.setCategoryRequiresWorldRestart(Settings.ENERGY_CATEGORY, true);

            //Assembler
            Settings.storageAssembler = configuration.get(Settings.ENERGY_CATEGORY, Settings.ENERGY_PATH_STORAGE_ASSEMBLER, Energy.DEFAULT_STORAGE_ASSEMBLER, "Energy Stored on Assembler").getInt();

            //Charge Station
            Settings.storageChargeStation = configuration.get(Settings.ENERGY_CATEGORY, Settings.ENERGY_PATH_STORAGE_CHARGE_STATION, Energy.DEFAULT_STORAGE_CHARGE_STATION, "Energy Stored on Charge Station MK1").getInt();
            Settings.ratioChargeStation = configuration.get(Settings.ENERGY_CATEGORY, Settings.ENERGY_PATH_RATIO_CHARGE_STATION, Energy.DEFAULT_RATIO_CHARGE_STATION, "Energy ratio on Charge Station MK1").getInt();
            Settings.conversionRatioChargeStation = configuration.get(Settings.ENERGY_CATEGORY, Settings.ENERGY_PATH_CONVERSION_RATIO_CHARGE_STATION, Energy.DEFAULT_CONVERSION_RATIO_CHARGE_STATION, "Amount of RF per turtle fuel value").getInt();

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
