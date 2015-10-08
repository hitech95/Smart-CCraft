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
package it.kytech.smartccraft.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import it.kytech.smartccraft.init.BlocksLoader;
import it.kytech.smartccraft.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;

public class CreativeTab {
    public static final CreativeTabs SCC_TAB = new CreativeTabs(Reference.MOD_ID) {

        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(BlocksLoader.assemblerBlock);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public String getTranslatedTabLabel() {
            return StatCollector.translateToLocal("key.categories.scc");
        }
    };
}

