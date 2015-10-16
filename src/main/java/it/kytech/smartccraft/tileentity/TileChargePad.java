package it.kytech.smartccraft.tileentity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import it.kytech.smartccraft.network.PacketHandler;
import it.kytech.smartccraft.network.message.MessageTileChargePad;
import it.kytech.smartccraft.reference.Messages;
import it.kytech.smartccraft.reference.Names;
import it.kytech.smartccraft.reference.Reference;
import it.kytech.smartccraft.reference.config.Configuration;
import it.kytech.smartccraft.util.IWailaDataDisplay;
import it.kytech.smartccraft.util.helper.LogHelper;
import mcp.mobius.waila.api.SpecialChars;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

/**
 * Created by Hitech95 on 14/10/2015.
 */
public class TileChargePad extends TileEnergyHandler implements ISidedInventory, IWailaDataDisplay {

    private int tier;
    private STATES status;
    private EnergyStorage energyStorage;
    private ItemStack[] inventory;

    public TileChargePad() {
        this(0);
    }

    public TileChargePad(int mTier) {
        inventory = new ItemStack[1];
        status = STATES.NO_ENTITY;
        tier = mTier;

        energyStorage = new EnergyStorage(getMaxCharge(tier), getMaxTransfer(tier));

        orientation = ForgeDirection.UP;
        state = (byte) this.tier;
    }

    public static int getMaxCharge(int tier) {
        return Configuration.storageChargePad * (int) Math.pow(10, tier);
    }

    public static int getMaxTransfer(int tier) {
        return Configuration.ratioChargePad * (int) Math.pow(2, tier);
    }

    public int getTier() {
        return tier;
    }

    public boolean isWorking() {
        return status == STATES.RUNNING;
    }

    public STATES getStatus() {
        return status;
    }

    public void setStatus(STATES newStatus) {
        if (status != newStatus) {
            status = newStatus;

            this.markDirty();
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, status.ordinal());
            this.worldObj.notifyBlockChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
        }
    }

    public boolean isDisabled() {
        return status == STATES.NO_ENTITY;
    }

    public void setEntity(boolean hasEntity) {
        setStatus((hasEntity) ? STATES.IDLE : STATES.NO_ENTITY);

        LogHelper.info("TileChargePad, hasEntity: " + hasEntity);
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
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.CHARGE_PAD;
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
    public void updateEntity() {
        if (!getWorldObj().isRemote) {

            ItemStack battery = getStackInSlot(0);
            if (battery != null && battery.getItem() instanceof IEnergyContainerItem) {
                IEnergyContainerItem energyContainerItem = (IEnergyContainerItem) battery.getItem();
                if (energyContainerItem.getEnergyStored(battery) > 0) {
                    int charge = Math.min(energyStorage.getMaxEnergyStored() - energyStorage.getEnergyStored(),
                            energyContainerItem.getEnergyStored(battery));
                    energyContainerItem.extractEnergy(battery, energyStorage.receiveEnergy(charge, false), false);
                }
            }

            //LogHelper.info("Status:" + status.name());

            if (status == STATES.IDLE) {
                setStatus(STATES.RUNNING);
            }
        }
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
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileChargePad(this));
    }

    @Override
    public boolean receiveClientEvent(int eventId, int eventData) {

        if (eventId == 1) {
            this.status = STATES.values()[eventData];
            return true;
        } else {
            return super.receiveClientEvent(eventId, eventData);
        }
    }

    @Override
    public int getEnergyStored() {
        return energyStorage.getEnergyStored();
    }

    @Override
    public void setEnergyStored(int l) {
        energyStorage.setEnergyStored(l);
    }

    public int getEnergyCapacity() {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        return (from != getOrientation()) ? energyStorage.receiveEnergy(maxReceive, simulate) : 0;
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return (from != ForgeDirection.UP) ? energyStorage.getEnergyStored() : 0;
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        return (from != ForgeDirection.UP) ? energyStorage.getMaxEnergyStored() : 0;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return (from != getOrientation());
    }

    @Override
    public List<String> attachWailaHead(List<String> currenttip) {
        currenttip.clear(); //Try to clean before attach

        String tooltip;
        switch (tier) {
            case 0:
                tooltip = SpecialChars.GRAY + StatCollector.translateToLocal(Messages.Tooltips.CHARGE_STATION);
                break;
            case 1:
                tooltip = SpecialChars.DRED + StatCollector.translateToLocal(Messages.Tooltips.CHARGE_STATION_2);
                break;
            case 2:
                tooltip = SpecialChars.DBLUE + StatCollector.translateToLocal(Messages.Tooltips.CHARGE_STATION_3);
                break;
            case 3:
                tooltip = SpecialChars.DGREEN + StatCollector.translateToLocal(Messages.Tooltips.CHARGE_STATION_4);
                break;
            default:
                tooltip = SpecialChars.WHITE + StatCollector.translateToLocal(Messages.Tooltips.CHARGE_STATION);
                break;
        }

        String workingStatus = SpecialChars.DRED + StatCollector.translateToLocal(Messages.Tooltips.NOT_WORKING);
        if (isWorking()) {
            workingStatus = SpecialChars.DGREEN + StatCollector.translateToLocal(Messages.Tooltips.WORKING);
        }

        currenttip.add(
                StatCollector.translateToLocal("tile" + "." + Reference.MOD_ID.toLowerCase() + ":" + Names.Blocks.CHARGE_PAD + ".name")
                        + " - " + tooltip);
        currenttip.add(String.format(StatCollector.translateToLocal(Messages.Tooltips.STATUS), workingStatus));


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

    public enum STATES {
        RUNNING,
        IDLE,
        NO_ENTITY
    }
}
