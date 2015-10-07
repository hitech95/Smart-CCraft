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
package it.kytech.smartccraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import it.kytech.smartccraft.reference.Messages;
import mcp.mobius.waila.api.SpecialChars;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

/**
 * Created by M2K on 29/06/2014.
 */
public class ItemBlockChargeStation extends ItemBlock {

    public ItemBlockChargeStation(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag) {
        // TODO Localize and add more descriptive text and remove dependency from WAILA
        int metaData = itemStack.getItemDamage();

        if (metaData == 0) {
            list.add(SpecialChars.WHITE + StatCollector.translateToLocal(Messages.Tooltips.CHARGE_STATION));
        } else if (metaData == 1) {
            list.add(SpecialChars.RED + StatCollector.translateToLocal(Messages.Tooltips.CHARGE_STATION_2));
        } else if (metaData == 2) {
            list.add(SpecialChars.BLUE + StatCollector.translateToLocal(Messages.Tooltips.CHARGE_STATION_3));
        } else if (metaData == 3) {
            list.add(SpecialChars.GREEN + StatCollector.translateToLocal(Messages.Tooltips.CHARGE_STATION_4));
        }
    }
}
