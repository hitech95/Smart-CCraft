package it.kytech.smartccraft.tileentity;

import it.kytech.smartccraft.reference.Names;
import it.kytech.smartccraft.reference.Settings;
import it.kytech.smartccraft.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.api.UniversalClass;
import universalelectricity.api.energy.EnergyStorageHandler;
import universalelectricity.api.energy.IEnergyContainer;
import universalelectricity.api.energy.IEnergyInterface;

/**
 * Created by M2K on 29/06/2014.
 */
@UniversalClass()
public class TileChargeStation extends TileEntitySCC implements IInventory, IEnergyInterface, IEnergyContainer {

    public final int tier;
    protected EnergyStorageHandler energy;

    ItemStack[] inventory;

    public TileChargeStation() {
        this(0);
    }

    public TileChargeStation(int tier) {
        inventory = new ItemStack[1];

        this.tier = tier;
        this.energy = new EnergyStorageHandler(getMaxCharge(tier), Settings.ratioChargeStation * (int) Math.pow(2, tier + 1));
    }

    public static int getMaxCharge(int tier) {
        return Settings.storageChargeStation * (int) Math.pow(10, tier + 1);
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex) {
        return inventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount) {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null) {
            if (itemStack.stackSize <= decrementAmount) {
                setInventorySlotContents(slotIndex, null);
            } else {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex) {
        if (inventory[slotIndex] != null) {
            ItemStack itemStack = inventory[slotIndex];
            inventory[slotIndex] = null;
            return itemStack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
        inventory[slotIndex] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }


        this.markDirty();
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.CHARGE_STATION;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = tag.getTagList("Items", 10);
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slotIndex = tagCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length) {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }

        //Read Energy
        energy.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
            if (inventory[currentIndex] != null) {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        tag.setTag("Items", tagList);

        //Write Energy
        energy.writeToNBT(tag);
    }

    @Override
    public void setEnergy(ForgeDirection forgeDirection, long l) {
        energy.setEnergy(l);
    }

    public void setEnergy(long l) {
        energy.setEnergy(l);
    }

    @Override
    public long getEnergy(ForgeDirection forgeDirection) {
        return energy.getEnergy();
    }

    public long getEnergy() {
        return energy.getEnergy();
    }

    @Override
    public long getEnergyCapacity(ForgeDirection forgeDirection) {
        return energy.getEnergyCapacity();
    }

    public long getEnergyCapacity() {
        return energy.getEnergyCapacity();
    }

    @Override
    public long onReceiveEnergy(ForgeDirection forgeDirection, long l, boolean b) {
        return energy.receiveEnergy(l, b);
    }

    @Override
    public long onExtractEnergy(ForgeDirection forgeDirection, long l, boolean b) {
        return 0;
    }

    @Override
    public boolean canConnect(ForgeDirection forgeDirection, Object o) {
        return true;
    }
}
