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
package it.kytech.smartccraft.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyReceiver;
import dan200.computercraft.api.turtle.ITurtleAccess;
import it.kytech.smartccraft.network.PacketHandler;
import it.kytech.smartccraft.network.message.MessageTileEntityChargeStation;
import it.kytech.smartccraft.network.message.MessageTileEntitySCC;
import it.kytech.smartccraft.reference.Messages;
import it.kytech.smartccraft.reference.Names;
import it.kytech.smartccraft.reference.Reference;
import it.kytech.smartccraft.reference.Settings;
import it.kytech.smartccraft.util.CCHelper;
import it.kytech.smartccraft.util.IWailaDataDisplay;
import mcp.mobius.waila.api.SpecialChars;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

/**
 * Created by M2K on 29/06/2014.
 */
public class TileChargeStation extends TileEntitySCC implements ISidedInventory, IEnergyReceiver, IWailaDataDisplay {

    public enum STATES {
        REDSTONE_DISABLED,
        RUNNING,
        IDLE
    }

    public static int getMaxCharge(int tier) {
        return Settings.storageChargeStation * (int) Math.pow(8, tier + 1);
    }

    private int tier;
    private STATES status;
    private EnergyStorage energyStorage;

    private ItemStack[] inventory;

    public TileChargeStation() {
        this(0);
    }

    public TileChargeStation(int tier) {
        inventory = new ItemStack[1];

        this.tier = tier;
        this.state = (byte) this.tier;
        this.energyStorage = new EnergyStorage(getMaxCharge(tier), Settings.ratioChargeStation * (int) Math.pow(2, tier + 1));
    }

    public int getTier() {
        return tier;
    }

    public void setRedstoneState(boolean state) {
        if (state) {
            status = STATES.REDSTONE_DISABLED;
        }
    }

    public boolean getRedstoneState() {
        return status == STATES.REDSTONE_DISABLED;
    }

    public boolean isWorking() {
        return status == STATES.RUNNING;
    }

    public STATES getStatus(){
        return status;
    }

    public void setStatus(STATES newStatus){
        status = newStatus;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[0]; //TODO
    }

    @Override
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return false; //TODO
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return false; //TODO
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
        return 1; //Only one element at time
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entity) {
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
        return (itemStack.getItem() instanceof IEnergyContainerItem);
    }

    @Override
    public void updateEntity() { //TODO: discharge the internal IEnergyContainerItem before charging the turtle
        if (!getWorldObj().isRemote) {
            int x = this.xCoord + getOrientation().offsetX;
            int y = this.yCoord + getOrientation().offsetY;
            int z = this.zCoord + getOrientation().offsetZ;

            TileEntity te = getWorldObj().getTileEntity(x, y, z);
            ITurtleAccess turtle;

            if (te == null) {
                status = STATES.IDLE;
                return;
            }

            try {
                turtle = CCHelper.getTurtle(te);
            } catch (Exception e) {
                turtle = null;
                e.printStackTrace();
            }

            if (turtle == null) {
                status = STATES.IDLE;
                return;
            }

            int rate = (int) Math.pow((double) tier + 1, (double) 4);
            if (energyStorage.getEnergyStored() >= rate) {
                status = STATES.RUNNING;
                energyStorage.extractEnergy(addFuel(turtle, rate) * Settings.conversionRatioChargeStation, false);
            }
        }
    }


    private int addFuel(ITurtleAccess turtle, int rate) {
        if (turtle.getFuelLimit() > turtle.getFuelLevel()) {
            turtle.setFuelLevel(rate + turtle.getFuelLevel()); //Bad fix for a crash with turtle.addFuel()
            return rate;
        }
        return 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        //Read status
        status = STATES.values()[tag.getByte(Names.NBT.STATUS)];

        //Read Energy
        energyStorage.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        //Write status
        tag.setByte(Names.NBT.STATUS, (byte) status.ordinal());

        //Write Energy
        energyStorage.writeToNBT(tag);
    }

    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityChargeStation(this));
    }

    public double getEnergy() {
        return energyStorage.getEnergyStored();
    }

    public void setEnergy(int l) {
        energyStorage.setEnergyStored(l);
    }

    public double getEnergyCapacity() {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return (from != getOrientation()) ? energyStorage.receiveEnergy(maxReceive, simulate) : 0; //TODO: check if direction (ForgeDirection) is the same of orientation
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return (from != getOrientation()) ? energyStorage.getEnergyStored() : 0; //TODO: check if direction (ForgeDirection) is the same of orientation
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return (from != getOrientation()) ? energyStorage.getMaxEnergyStored() : 0; //TODO: check if direction (ForgeDirection) is the same of orientation
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return (from != getOrientation());
    }

    @Override
    public List<String> attachWailaHead(List<String> currenttip) {
        String tooltip;
        switch (tier) {
            case 0:
                tooltip = SpecialChars.GRAY + " " + StatCollector.translateToLocal(Messages.Tooltips.CHARGE_STATION);
                break;
            case 1:
                tooltip = SpecialChars.DRED + " " + StatCollector.translateToLocal(Messages.Tooltips.CHARGE_STATION_2);
                break;
            case 2:
                tooltip = SpecialChars.DBLUE + " " + StatCollector.translateToLocal(Messages.Tooltips.CHARGE_STATION_3);
                break;
            case 3:
                tooltip = SpecialChars.DGREEN + " " + StatCollector.translateToLocal(Messages.Tooltips.CHARGE_STATION_4);
                break;
            default:
                tooltip = SpecialChars.WHITE + " " + StatCollector.translateToLocal(Messages.Tooltips.CHARGE_STATION);
                break;
        }

        currenttip.set(0, StatCollector.translateToLocal("tile" + "." + Reference.MOD_ID.toLowerCase() + ":" + Names.Blocks.CHARGE_STATION + ".name")
                + " - " + tooltip);
        return currenttip;
    }

    @Override
    public List<String> attachWailaBody(List<String> currenttip) {
        return currenttip;
    }

    @Override
    public List<String> attachWailaTail(List<String> currenttip) {
        return currenttip;
    }
}
