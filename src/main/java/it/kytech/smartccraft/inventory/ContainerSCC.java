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
