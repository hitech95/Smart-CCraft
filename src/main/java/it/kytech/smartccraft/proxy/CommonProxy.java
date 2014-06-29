package it.kytech.smartccraft.proxy;

import cpw.mods.fml.common.registry.GameRegistry;
import it.kytech.smartccraft.reference.Names;
import it.kytech.smartccraft.tileentity.TileChargeStation;
import it.kytech.smartccraft.tileentity.TileChargeStationMK2;
import it.kytech.smartccraft.tileentity.TileChargeStationMK3;
import it.kytech.smartccraft.tileentity.TileChargeStationMK4;

/**
 * Created by M2K on 27/06/2014.
 */
public abstract class CommonProxy implements IProxy {
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileChargeStation.class, "tile." + Names.Blocks.CHARGE_STATION);
        GameRegistry.registerTileEntity(TileChargeStationMK2.class, "tile." + Names.Blocks.CHARGE_STATION_2);
        GameRegistry.registerTileEntity(TileChargeStationMK3.class, "tile." + Names.Blocks.CHARGE_STATION_3);
        GameRegistry.registerTileEntity(TileChargeStationMK4.class, "tile." + Names.Blocks.CHARGE_STATION_4);
    }
}
