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
package it.kytech.smartccraft.reference.config;

import it.kytech.smartccraft.reference.config.original.Energy;
import it.kytech.smartccraft.reference.config.original.Integration;

/**
 * Created by M2K on 01/07/2014.
 */
public class Configuration {

    public enum Category {
        GENERAL("general"),
        INTEGRATION("integration"),
        ENERGY("energy");

        public final String name;

        private Category(String slug) {
            name = slug;
        }
    }

    public final class Path {
        public static final String INTEGRATION_BC = "integrateBC";
        public static final String INTEGRATION_TE = "integrateTE";
        public static final String INTEGRATION_MFR = "integrateMFR";

        public static final String ENERGY_STORAGE_ASSEMBLER = "storageAssembler";

        public static final String ENERGY_STORAGE_CHARGE_STATION = "storageChargeStation";
        public static final String ENERGY_RATIO_CHARGE_STATION = "ratioChargeStation";
        public static final String ENERGY_CONVERSION_RATIO_CHARGE_STATION = "conversionRatioChargeStation";

        public static final String ENERGY_STORAGE_CHARGE_PAD = "storageChargePad";
        public static final String ENERGY_RATIO_CHARGE_PAD = "ratioChargePad";
        public static final String ENERGY_CONVERSION_RATIO_CHARGE_PAD = "conversionRatioChargePad";

    }

    //Integration
    public static boolean integrateBC = Integration.DEFAULT_INTEGRATION_BC;
    public static boolean integrateTE = Integration.DEFAULT_INTEGRATION_TE;
    public static boolean integrateMFR = Integration.DEFAULT_INTEGRATION_MFR;

    //Block Specific Configuration
    //Assembler
    public static int storageAssembler = Energy.DEFAULT_STORAGE_ASSEMBLER;

    //Charge Station
    public static int storageChargeStation = Energy.DEFAULT_STORAGE_CHARGE_STATION;
    public static int ratioChargeStation = Energy.DEFAULT_RATIO_CHARGE_STATION;
    public static int conversionRatioChargeStation = Energy.DEFAULT_CONVERSION_RATIO_CHARGE_STATION;

    //Charge Pad
    public static int storageChargePad = Energy.DEFAULT_STORAGE_CHARGE_PAD;
    public static int ratioChargePad = Energy.DEFAULT_RATIO_CHARGE_PAD;
    public static int conversionRatioChargePad = Energy.DEFAULT_CONVERSION_RATIO_CHARGE_PAD;
}
