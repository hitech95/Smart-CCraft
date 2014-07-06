package it.kytech.smartccraft.reference;

/**
 * Created by M2K on 01/07/2014.
 */
public class Settings {
    //GENERAL Category
    public static final String GENERAL_CATEGORY = "general";

    //INTEGRATION Category
    public static final String INTEGRATION_CATEGORY = "integration";
    public static final String INTEGRATION_IC2 = "integrateIC2";
    public static final String INTEGRATION_BC = "integrateBC";
    public static final String INTEGRATION_TE = "integrateTE";
    public static final String INTEGRATION_MFR = "integrateMFR";

    public static boolean integrateIC2 = false;
    public static boolean integrateBC = false;
    public static boolean integrateTE = false;
    public static boolean integrateMFR = false;

    //ENERGY Category
    public static final String ENERGY_CATEGORY = "energyStorage";
    public static final String ENERGY_PATH_STORAGE_ASSEMBLER = "storageAssembler";
    public static final String ENERGY_PATH_STORAGE_CHARGE_STATION = "storageChargeStation";
    public static final String ENERGY_PATH_RATIO_CHARGE_STATION = "ratioChargeStation";

    //Assembler
    public static int storageAssembler = 50000;

    //Charge Station
    public static int storageChargeStation = 50;
    public static int ratioChargeStation = 50;
}
