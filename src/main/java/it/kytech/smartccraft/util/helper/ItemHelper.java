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
package it.kytech.smartccraft.util.helper;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Comparator;

public class ItemHelper {
    public static ItemStack cloneItemStack(ItemStack itemStack, int stackSize) {
        ItemStack clonedItemStack = itemStack.copy();
        clonedItemStack.stackSize = stackSize;
        return clonedItemStack;
    }

    public static int findBestSlotForSidedInventory(ISidedInventory inv, ItemStack stack, ForgeDirection side) {
        int result = -1;

        // First search for a matching slot
        result = findBestMatchingSlotForSidedInventory(inv, stack, side);
        if (result != -1) {
            return result;
        }

        // Then check for an empty slot
        result = findFirstEmptySlotInSidedInventory(inv, stack, side);
        if (result != -1) {
            return result;
        }

        // No slot found :(
        return result;

    }

    public static int findBestSlotForInventory(IInventory inv, ItemStack stack) {
        int result = -1;

        // First search for a matching slot
        result = findBestMatchingSlotForInventory(inv, stack);
        if (result != -1) {
            return result;
        }

        // Then check for an empty slot
        result = findFirstEmptySlot(inv, stack);
        if (result != -1) {
            return result;
        }

        // No slot found :(
        return result;
    }

    private static int findBestMatchingSlotForSidedInventory(ISidedInventory inv, ItemStack stack, ForgeDirection side) {
        int[] accessibleSlotsFromSide = inv.getAccessibleSlotsFromSide(side.ordinal());

        for (int slot : accessibleSlotsFromSide) {
            ItemStack target = inv.getStackInSlot(slot);
            if (target != null && target.getItem() == stack.getItem() && target.isStackable() && inv.isItemValidForSlot(slot, stack)
                    && target.stackSize < target.getMaxStackSize() && target.stackSize < inv.getInventoryStackLimit()
                    && inv.canInsertItem(slot, stack, side.ordinal()) && (!target.getHasSubtypes() || target.getItemDamage() == stack.getItemDamage())
                    && ItemStack.areItemStackTagsEqual(target, stack)) {
                return slot;
            }
        }

        return -1;
    }

    private static int findFirstEmptySlotInSidedInventory(ISidedInventory inv, ItemStack stack, ForgeDirection side) {
        int[] accessibleSlotsFromSide = inv.getAccessibleSlotsFromSide(side.ordinal());

        for (int slot : accessibleSlotsFromSide) {
            ItemStack target = inv.getStackInSlot(slot);
            if (target == null && inv.isItemValidForSlot(slot, stack) && inv.canInsertItem(slot, stack, side.ordinal())) {
                return slot;
            }
        }

        return -1;
    }

    private static int findBestMatchingSlotForInventory(IInventory inv, ItemStack stack) {
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack target = inv.getStackInSlot(i);
            if (target != null && target.getItem() == stack.getItem() && target.isStackable() && inv.isItemValidForSlot(i, stack)
                    && target.stackSize < target.getMaxStackSize() && target.stackSize < inv.getInventoryStackLimit()
                    && (!target.getHasSubtypes() || target.getItemDamage() == stack.getItemDamage()) && ItemStack.areItemStackTagsEqual(target, stack)) {
                return i;
            }
        }

        return -1;
    }

    private static int findFirstEmptySlot(IInventory inv, ItemStack stack) {
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack target = inv.getStackInSlot(i);
            if (target == null && inv.isItemValidForSlot(i, stack)) {
                return i;
            }
        }

        return -1;
    }

    public static Comparator<ItemStack> comparator = new Comparator<ItemStack>() {
        @Override
        public int compare(ItemStack itemStack1, ItemStack itemStack2) {
            if (itemStack1 != null && itemStack2 != null) {
                // Sort on itemID
                if (Item.getIdFromItem(itemStack1.getItem()) - Item.getIdFromItem(itemStack2.getItem()) == 0) {
                    // Sort on item
                    if (itemStack1.getItem() == itemStack2.getItem()) {
                        // Then sort on meta
                        if (itemStack1.getItemDamage() == itemStack2.getItemDamage()) {
                            // Then sort on NBT
                            if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound()) {
                                // Then sort on stack size
                                if (ItemStack.areItemStackTagsEqual(itemStack1, itemStack2)) {
                                    return (itemStack1.stackSize - itemStack2.stackSize);
                                } else {
                                    return (itemStack1.getTagCompound().hashCode() - itemStack2.getTagCompound().hashCode());
                                }
                            } else if (!(itemStack1.hasTagCompound()) && itemStack2.hasTagCompound()) {
                                return -1;
                            } else if (itemStack1.hasTagCompound() && !(itemStack2.hasTagCompound())) {
                                return 1;
                            } else {
                                return (itemStack1.stackSize - itemStack2.stackSize);
                            }
                        } else {
                            return (itemStack1.getItemDamage() - itemStack2.getItemDamage());
                        }
                    } else {
                        return itemStack1.getItem().getUnlocalizedName(itemStack1).compareToIgnoreCase(itemStack2.getItem().getUnlocalizedName(itemStack2));
                    }
                } else {
                    return Item.getIdFromItem(itemStack1.getItem()) - Item.getIdFromItem(itemStack2.getItem());
                }
            } else if (itemStack1 != null) {
                return -1;
            } else if (itemStack2 != null) {
                return 1;
            } else {
                return 0;
            }
        }
    };

    /**
     * Compares two ItemStacks for equality, testing itemID, metaData,
     * stackSize, and their NBTTagCompounds (if they are
     * present)
     *
     * @param first
     *            The first ItemStack being tested for equality
     * @param second
     *            The second ItemStack being tested for equality
     * @return true if the two ItemStacks are equivalent, false otherwise
     */
    public static boolean equals(ItemStack first, ItemStack second) {
        return (comparator.compare(first, second) == 0);
    }

    public static boolean equalsIgnoreStackSize(ItemStack itemStack1, ItemStack itemStack2) {
        if (itemStack1 != null && itemStack2 != null) {
            // Sort on itemID
            if (Item.getIdFromItem(itemStack1.getItem()) - Item.getIdFromItem(itemStack2.getItem()) == 0) {
                // Sort on item
                if (itemStack1.getItem() == itemStack2.getItem()) {
                    // Then sort on meta
                    if (itemStack1.getItemDamage() == itemStack2.getItemDamage()) {
                        // Then sort on NBT
                        if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound()) {
                            // Then sort on stack size
                            if (ItemStack.areItemStackTagsEqual(itemStack1, itemStack2)) {
                                return true;
                            }
                        } else {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static int compare(ItemStack itemStack1, ItemStack itemStack2) {
        return comparator.compare(itemStack1, itemStack2);
    }

    public static String toString(ItemStack itemStack) {
        if (itemStack != null) {
            return String.format("%sxitemStack[%s@%s]", itemStack.stackSize, itemStack.getUnlocalizedName(), itemStack.getItemDamage());
        }

        return "null";
    }

    // Taken from CodeChickenLib. All credits to Chicken-Bones
    // TODO: Add to credits.

    /**
     * NBT item loading function with support for stack sizes > 32K
     */
    public static void readItemStacksFromTag(ItemStack[] items, NBTTagList tagList) {
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            int b = tag.getShort("Slot");
            items[b] = ItemStack.loadItemStackFromNBT(tag);
            if (tag.hasKey("Quantity")) {
                items[b].stackSize = ((NBTBase.NBTPrimitive) tag.getTag("Quantity")).func_150287_d();
            }
        }
    }

    /**
     * NBT item saving function
     */
    public static NBTTagList writeItemStacksToTag(ItemStack[] items) {
        return writeItemStacksToTag(items, 64);
    }

    /**
     * NBT item saving function with support for stack sizes > 32K
     */
    public static NBTTagList writeItemStacksToTag(ItemStack[] items, int maxQuantity) {
        NBTTagList tagList = new NBTTagList();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setShort("Slot", (short) i);
                items[i].writeToNBT(tag);

                if (maxQuantity > Short.MAX_VALUE) {
                    tag.setInteger("Quantity", items[i].stackSize);
                } else if (maxQuantity > Byte.MAX_VALUE) {
                    tag.setShort("Quantity", (short) items[i].stackSize);
                }

                tagList.appendTag(tag);
            }
        }
        return tagList;
    }

    /**
     * Static default implementation for IInventory method
     */
    public static ItemStack decrStackSize(IInventory inv, int slot, int size) {
        ItemStack item = inv.getStackInSlot(slot);

        if (item != null) {
            if (item.stackSize <= size) {
                inv.setInventorySlotContents(slot, null);
                inv.markDirty();
                return item;
            }
            ItemStack itemstack1 = item.splitStack(size);
            if (item.stackSize == 0) {
                inv.setInventorySlotContents(slot, null);
            }

            inv.markDirty();
            return itemstack1;
        }
        return null;
    }

    /**
     * Consumes one item from slot in inv with support for containers.
     */
    public static void consumeItem(IInventory inv, int slot) {
        ItemStack stack = inv.getStackInSlot(slot);
        Item item = stack.getItem();
        if (item.hasContainerItem(stack)) {
            ItemStack container = item.getContainerItem(stack);
            inv.setInventorySlotContents(slot, container);
        } else {
            inv.decrStackSize(slot, 1);
        }
    }
}