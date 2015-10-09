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

import it.kytech.smartccraft.tileentity.TileChargeStation;
import it.kytech.smartccraft.tileentity.TileChargeStationMK2;
import it.kytech.smartccraft.tileentity.TileChargeStationMK3;
import it.kytech.smartccraft.tileentity.TileChargeStationMK4;
import it.kytech.smartccraft.util.IWailaDataDisplay;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Hitech95 on 02/10/2015.
 */
public class WailaHandler implements IWailaDataProvider {

    public static void callbackRegister(IWailaRegistrar registrar) {
        registrar.registerHeadProvider(new WailaHandler(), TileChargeStation.class);
        registrar.registerHeadProvider(new WailaHandler(), TileChargeStationMK2.class);
        registrar.registerHeadProvider(new WailaHandler(), TileChargeStationMK3.class);
        registrar.registerHeadProvider(new WailaHandler(), TileChargeStationMK4.class);


    }

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getTileEntity() instanceof IWailaDataDisplay) {
            IWailaDataDisplay tileEntity = (IWailaDataDisplay) accessor.getTileEntity();
            tileEntity.attachWailaHead(currenttip);
        }

        return currenttip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getTileEntity() instanceof IWailaDataDisplay) {
            IWailaDataDisplay tileEntity = (IWailaDataDisplay) accessor.getTileEntity();
            tileEntity.attachWailaBody(currenttip);
        }

        return currenttip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getTileEntity() instanceof IWailaDataDisplay) {
            IWailaDataDisplay tileEntity = (IWailaDataDisplay) accessor.getTileEntity();
            tileEntity.attachWailaTail(currenttip);
        }

        return currenttip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
        return null;
    }
}
