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
package it.kytech.smartccraft.reference;

/**
 * Created by M2K on 01/07/2014.
 */
public class Settings {
    //GENERAL Category
    public static final String GENERAL_CATEGORY = "general";

    //INTEGRATION Category
    public static final String INTEGRATION_CATEGORY = "integration";
    //public static final String INTEGRATION_IC2 = "integrateIC2";
    public static final String INTEGRATION_BC = "integrateBC";
    public static final String INTEGRATION_TE = "integrateTE";
    //public static final String INTEGRATION_MFR = "integrateMFR";

    //public static boolean integrateIC2 = false;
    public static boolean integrateBC = true;
    public static boolean integrateTE = true;
    //public static boolean integrateMFR = false;

    //ENERGY Category
    public static final String ENERGY_CATEGORY = "energyStorage";
    public static final String ENERGY_PATH_STORAGE_ASSEMBLER = "storageAssembler";
    public static final String ENERGY_PATH_STORAGE_CHARGE_STATION = "storageChargeStation";
    public static final String ENERGY_PATH_RATIO_CHARGE_STATION = "ratioChargeStation";
    public static final String ENERGY_PATH_CONVERSION_RATIO_CHARGE_STATION = "conversionRatioChargeStation";

    //Assembler
    public static int storageAssembler = 50000;

    //Charge Station
    public static int storageChargeStation = 100;
    public static int ratioChargeStation = 80;
    public static int conversionRatioChargeStation = 60;
}
