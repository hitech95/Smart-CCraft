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
package it.kytech.smartccraft.inventory;

import it.kytech.smartccraft.inventory.slot.ElectricSlot;
import it.kytech.smartccraft.network.PacketHandler;
import it.kytech.smartccraft.network.message.MessageTileEnergy;
import it.kytech.smartccraft.tileentity.TileChargeStation;
import it.kytech.smartccraft.util.LogHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

import java.util.List;

public class ContainerChargeStation extends ContainerSCC {
    public TileChargeStation tileChargeStation;
    private int lastEnergy;

    public ContainerChargeStation(InventoryPlayer inventoryPlayer, TileChargeStation tileChargeStation) {
        super(inventoryPlayer, tileChargeStation);

        this.tileChargeStation = tileChargeStation;

        addSlotToContainer(new ElectricSlot(tileChargeStation, 0, 80, 41));
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting) {
        super.addCraftingToCrafters(iCrafting);
        //var1.sendProgressBarUpdate(this, 0, tileChargeStation.getEnergyStored()); //is short not int
        //splitting an int to 2 shorts: (short) value & 0xFFFF and (short) (value >> 16) & 0xFFFF

        if(iCrafting instanceof EntityPlayerMP){
            PacketHandler.INSTANCE.sendTo(new MessageTileEnergy(tileChargeStation), (EntityPlayerMP) iCrafting);
            LogHelper.error("FIRST PACKET:" + tileChargeStation.getEnergyStored()); //TODO: remove debug
        }else{
            short low = (short) (tileChargeStation.getEnergyStored() & 0xFFFF);
            short hi = (short) ((tileChargeStation.getEnergyStored() >> 16) & 0xFFFF);
            iCrafting.sendProgressBarUpdate(this, 0, low);
            iCrafting.sendProgressBarUpdate(this, 1, hi);
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (ICrafting crafter : (List<ICrafting>) crafters) {
            if (lastEnergy != tileChargeStation.getEnergyStored()) {

                if(crafter instanceof EntityPlayerMP){
                    PacketHandler.INSTANCE.sendTo(new MessageTileEnergy(tileChargeStation), (EntityPlayerMP) crafter);
                    LogHelper.error("BEFORE PACKET:" + tileChargeStation.getEnergyStored()); //TODO: remove debug
                }else{
                    short low = (short) (tileChargeStation.getEnergyStored() & 0xFFFF);
                    short hi = (short) ((tileChargeStation.getEnergyStored() >> 16) & 0xFFFF);
                    crafter.sendProgressBarUpdate(this, 0, low);
                    crafter.sendProgressBarUpdate(this, 1, hi);
                }
            }
        }

        lastEnergy = tileChargeStation.getEnergyStored();
    }

    @Override
    public void updateProgressBar(int index, int value) {
        switch (index) {
            case 0:
                //tileChargeStation.setEnergyStored(value); //is short not int
                //LogHelper.error("AFTER PACKET:" + value);
                break;
            default:
                break;
        }
    }
}
