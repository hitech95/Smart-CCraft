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
package it.kytech.smartccraft.proxy;

import cpw.mods.fml.common.registry.GameRegistry;
import it.kytech.smartccraft.reference.Names;
import it.kytech.smartccraft.tileentity.*;

/**
 * Created by M2K on 27/06/2014.
 */
public abstract class CommonProxy implements IProxy {
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileAssembler.class, "tile." + Names.Blocks.ASSEMBLER);

        GameRegistry.registerTileEntity(TileChargeStation.class, "tile." + Names.Blocks.CHARGE_STATION);
        GameRegistry.registerTileEntity(TileChargeStationMK2.class, "tile." + Names.Blocks.CHARGE_STATION_2);
        GameRegistry.registerTileEntity(TileChargeStationMK3.class, "tile." + Names.Blocks.CHARGE_STATION_3);
        GameRegistry.registerTileEntity(TileChargeStationMK4.class, "tile." + Names.Blocks.CHARGE_STATION_4);
    }
}
