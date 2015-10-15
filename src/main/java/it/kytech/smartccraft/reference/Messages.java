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
package it.kytech.smartccraft.reference;

/**
 * Created by Hitech95 on 02/10/2015.
 */
public class Messages {
    public static class Tooltips {
        private static final String TOOLTIP_PREFIX = "tooltip." + Reference.MOD_ID.toLowerCase() + ":";

        public static final String ENERGY = TOOLTIP_PREFIX + "energy";
        public static final String STATUS = TOOLTIP_PREFIX + "state";
        public static final String WORKING = TOOLTIP_PREFIX + "working";
        public static final String NOT_WORKING = TOOLTIP_PREFIX + "notWorking";

        public static final String FRAME = TOOLTIP_PREFIX + "frameBlock";
        public static final String ASSEMBLER = TOOLTIP_PREFIX + "assemblerBlock";

        public static final String CHARGE_STATION = TOOLTIP_PREFIX + "chargeStation";
        public static final String CHARGE_STATION_2 = TOOLTIP_PREFIX + "chargeStationMK2";
        public static final String CHARGE_STATION_3 = TOOLTIP_PREFIX + "chargeStationMK3";
        public static final String CHARGE_STATION_4 = TOOLTIP_PREFIX + "chargeStationMK4";
    }

    public static class Configuration {
        public static class Category {
            public static final String GENERAL = "General Options";
            public static final String INTEGRATION = "Mod integration Options";
            public static final String ENERGY = "Energy Balance Options";
        }
    }
}
