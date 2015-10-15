/**
 * This file is part of SmartCCraft
 * <p/>
 * Copyright (c) 2015 hitech95 <https://github.com/hitech95>
 * Copyright (c) contributors
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.kytech.smartccraft.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import it.kytech.smartccraft.reference.Messages;
import it.kytech.smartccraft.reference.Reference;
import it.kytech.smartccraft.reference.config.Configuration;
import it.kytech.smartccraft.reference.config.original.Energy;
import it.kytech.smartccraft.reference.config.original.Integration;
import it.kytech.smartccraft.util.helper.LogHelper;

import java.io.File;

/**
 * Created by M2K on 29/06/2014.
 */
public class ConfigurationHandler {
    public static net.minecraftforge.common.config.Configuration configuration;

    public static void init(File configurationFile) {
        if (configuration == null) {
            configuration = new net.minecraftforge.common.config.Configuration(configurationFile);
            configuration.load();

            loadConfiguration();
        }
    }

    public static void loadConfiguration() {
        LogHelper.info("Loading Config");

        try {
            //Setup Config Category
            //General
            configuration.setCategoryComment(Configuration.Category.GENERAL.name, Messages.Configuration.Category.GENERAL);
            configuration.setCategoryRequiresMcRestart(Configuration.Category.GENERAL.name, false);
            configuration.setCategoryRequiresWorldRestart(Configuration.Category.GENERAL.name, true);

            //Integration Category
            configuration.setCategoryComment(Configuration.Category.INTEGRATION.name, Messages.Configuration.Category.INTEGRATION);
            configuration.setCategoryRequiresMcRestart(Configuration.Category.INTEGRATION.name, true);
            configuration.setCategoryRequiresWorldRestart(Configuration.Category.INTEGRATION.name, true);

            //Energy Category
            configuration.setCategoryComment(Configuration.Category.ENERGY.name, Messages.Configuration.Category.ENERGY);
            configuration.setCategoryRequiresMcRestart(Configuration.Category.ENERGY.name, false);
            configuration.setCategoryRequiresWorldRestart(Configuration.Category.ENERGY.name, true);


            //Read config
            //Integration Settings
            Configuration.integrateBC = configuration.get(
                    Configuration.Category.INTEGRATION.name,
                    Configuration.Path.INTEGRATION_BC,
                    Integration.DEFAULT_INTEGRATION_BC,
                    "Integrate Buildcraft Iron Gear"
            ).getBoolean();

            Configuration.integrateTE = configuration.get(
                    Configuration.Category.INTEGRATION.name,
                    Configuration.Path.INTEGRATION_TE,
                    Integration.DEFAULT_INTEGRATION_TE,
                    "Integrate Thermal Expansion Machine Frame"
            ).getBoolean();

            Configuration.integrateMFR = configuration.get(
                    Configuration.Category.INTEGRATION.name,
                    Configuration.Path.INTEGRATION_MFR,
                    Integration.DEFAULT_INTEGRATION_MFR,
                    "Add a Advanced Factory Machine Block, craft with iron instead of stone"
            ).getBoolean();


            //Assembler
            Configuration.storageAssembler = configuration.get(
                    Configuration.Category.ENERGY.name,
                    Configuration.Path.ENERGY_STORAGE_ASSEMBLER,
                    Energy.DEFAULT_STORAGE_ASSEMBLER,
                    "Energy Stored on Assembler"
            ).getInt();

            //Charge Station
            Configuration.storageChargeStation = configuration.get(
                    Configuration.Category.ENERGY.name,
                    Configuration.Path.ENERGY_STORAGE_CHARGE_STATION,
                    Energy.DEFAULT_STORAGE_CHARGE_STATION,
                    "Energy Stored on Charge Station MK1"
            ).getInt();
            Configuration.ratioChargeStation = configuration.get(
                    Configuration.Category.ENERGY.name,
                    Configuration.Path.ENERGY_RATIO_CHARGE_STATION,
                    Energy.DEFAULT_RATIO_CHARGE_STATION
                    , "Energy ratio on Charge Station MK1"
            ).getInt();
            Configuration.conversionRatioChargeStation = configuration.get(
                    Configuration.Category.ENERGY.name,
                    Configuration.Path.ENERGY_CONVERSION_RATIO_CHARGE_STATION,
                    Energy.DEFAULT_CONVERSION_RATIO_CHARGE_STATION,
                    "Amount of RF per turtle fuel value"
            ).getInt();

            //Charge Pad
            Configuration.storageChargePad = configuration.get(
                    Configuration.Category.ENERGY.name,
                    Configuration.Path.ENERGY_STORAGE_CHARGE_PAD,
                    Energy.DEFAULT_STORAGE_CHARGE_PAD,
                    "Energy Stored on Charge Pad MK1"
            ).getInt();
            Configuration.ratioChargePad = configuration.get(
                    Configuration.Category.ENERGY.name,
                    Configuration.Path.ENERGY_RATIO_CHARGE_PAD,
                    Energy.DEFAULT_RATIO_CHARGE_PAD,
                    "Energy ratio on Charge Pad MK1"
            ).getInt();
            Configuration.conversionRatioChargePad = configuration.get(
                    Configuration.Category.ENERGY.name,
                    Configuration.Path.ENERGY_CONVERSION_RATIO_CHARGE_PAD,
                    Energy.DEFAULT_CONVERSION_RATIO_CHARGE_PAD,
                    "Amount of RF to RF (Wireless Efficency)"
            ).getInt();

        } catch (Exception e) {
            LogHelper.warn("Config File not found...");
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
