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
 * Created by M2K on 27/06/2014.
 */
public class Names {
    public static class Blocks {
        public static final String FRAME = "frameBlock";

        public static final String ASSEMBLER = "assemblerBlock";

        public static final String CHARGE_STATION = "chargeStation";
        public static final String CHARGE_STATION_2 = "chargeStationMK2";
        public static final String CHARGE_STATION_3 = "chargeStationMK3";
        public static final String CHARGE_STATION_4 = "chargeStationMK4";
    }

    public static class Items {
        public static final String RAW_UPGRADE = "rawUpgrade";
        public static final String SINGLE_UPGRADE = "singleUpgrade";
        public static final String FINAL_UPGRADE = "finalUpgrade";
    }

    public static class NBT {
        public static final String ITEMS = "Items";
        public static final String MODE = "mode";
        public static final String CRAFTING_GUI_OPEN = "craftingGuiOpen";
        public static final String ALCHEMICAL_BAG_GUI_OPEN = "alchemicalBagGuiOpen";
        public static final String UUID_MOST_SIG = "UUIDMostSig";
        public static final String UUID_LEAST_SIG = "UUIDLeastSig";
        public static final String DISPLAY = "display";
        public static final String COLOR = "color";
        public static final String STATE = "teState"; //State or metadata
        public static final String STATUS = "teStatus"; //State if metadata is used
        public static final String CUSTOM_NAME = "CustomName";
        public static final String DIRECTION = "teDirection";
        public static final String OWNER = "owner";
        public static final String OWNER_UUID_MOST_SIG = "ownerUUIDMostSig";
        public static final String OWNER_UUID_LEAST_SIG = "ownerUUIDLeastSig";
    }

    public static class Containers {
        public static final String VANILLA_INVENTORY = "container.inventory";
        public static final String VANILLA_CRAFTING = "container.crafting";
        public static final String ASSEMBLER = "container.smartccraft:" + Blocks.ASSEMBLER;
        public static final String CHARGE_STATION = "container.smartccraft:" + Blocks.CHARGE_STATION;
        public static final String CHARGE_STATION_2 = "container.smartccraft:" + Blocks.CHARGE_STATION_2;
        public static final String CHARGE_STATION_3 = "container.smartccraft:" + Blocks.CHARGE_STATION_3;
        public static final String CHARGE_STATION_4 = "container.smartccraft:" + Blocks.CHARGE_STATION_4;
    }

    public static class Keys {
        public static final String CATEGORY = "key.categories.scc";
    }
}
