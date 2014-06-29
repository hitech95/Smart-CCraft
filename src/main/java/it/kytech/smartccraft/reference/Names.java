package it.kytech.smartccraft.reference;

/**
 * Created by M2K on 27/06/2014.
 */
public class Names {
    public static class Blocks {
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
        public static final String CHARGE_LEVEL = "chargeLevel";
        public static final String MODE = "mode";
        public static final String CRAFTING_GUI_OPEN = "craftingGuiOpen";
        public static final String UUID_MOST_SIG = "UUIDMostSig";
        public static final String UUID_LEAST_SIG = "UUIDLeastSig";
        public static final String DISPLAY = "display";
        public static final String COLOR = "color";
        public static final String STATE = "teState";
        public static final String CUSTOM_NAME = "CustomName";
        public static final String DIRECTION = "teDirection";
        public static final String OWNER = "owner";
    }

    public static class Containers {
        public static final String VANILLA_INVENTORY = "container.inventory";
        public static final String VANILLA_CRAFTING = "container.crafting";
        public static final String CHARGE_STATION = "container.smartccraft:" + Blocks.CHARGE_STATION;
        public static final String CHARGE_STATION_2 = "container.smartccraft:" + Blocks.CHARGE_STATION_2;
        public static final String CHARGE_STATION_3 = "container.smartccraft:" + Blocks.CHARGE_STATION_3;
        public static final String CHARGE_STATION_4 = "container.smartccraft:" + Blocks.CHARGE_STATION_4;
    }

    public static class Keys {
        public static final String CATEGORY = "key.categories.scc";
    }
}
