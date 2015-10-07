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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSCC extends Container {

    public IInventory inventory;

    public ContainerSCC(InventoryPlayer inventoryPlayer, IInventory inventory) {
        this(inventoryPlayer, inventory, 84);
    }

    public ContainerSCC(InventoryPlayer inventoryPlayer, IInventory inventory, int inventoryBaseY) {
        this.inventory = inventory;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(inventoryPlayer, x + y * 9 + 9, 8 + x * 18, inventoryBaseY + y * 18));
            }
        }

        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(inventoryPlayer, x, 8 + x * 18, inventoryBaseY + 58));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return inventory.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int par1) {
        ItemStack var2 = null;
        Slot var3 = (Slot) this.inventorySlots.get(par1);

        if (var3 != null && var3.getHasStack()) {
            ItemStack var4 = var3.getStack();
            var2 = var4.copy();

            if (par1 < 1) {
                if (!this.mergeItemStack(var4, 1, this.inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!this.mergeItemStack(var4, 0, 1, false)) return null;

            if (var4.stackSize == 0) var3.putStack((ItemStack) null);
            else var3.onSlotChanged();
        }

        return var2;
    }
}
