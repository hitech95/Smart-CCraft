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
package it.kytech.smartccraft.inventory;

import it.kytech.smartccraft.tileentity.TileChargeStation;
import it.kytech.smartccraft.util.LogHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

import java.util.List;

public class ContainerChargeStation extends ContainerSCC {
    public TileChargeStation tileChargeStation;
    private int lastEnergy;

    public ContainerChargeStation(InventoryPlayer inventoryPlayer, TileChargeStation tileChargeStation) {
        super(inventoryPlayer, tileChargeStation);

        this.tileChargeStation = tileChargeStation;

        addSlotToContainer(new Slot(tileChargeStation, 0, 80, 41));
    }

    @Override
    public void addCraftingToCrafters(ICrafting var1) {
        super.addCraftingToCrafters(var1);
        var1.sendProgressBarUpdate(this, 0, tileChargeStation.getEnergy());
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (ICrafting crafter : (List<ICrafting>) crafters) {
            if (lastEnergy != tileChargeStation.getEnergy()) {
                crafter.sendProgressBarUpdate(this, 0, tileChargeStation.getEnergy());
                LogHelper.error("BEFORE PACKET:" + tileChargeStation.getEnergy());
            }
        }

        lastEnergy = tileChargeStation.getEnergy();
    }

    @Override
    public void updateProgressBar(int index, int value) {
        switch (index) {
            case 0:
                tileChargeStation.setEnergy(value);
                LogHelper.error("AFTER PACKET:" + value);
                break;
            default:
                break;
        }
    }
}
