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
package it.kytech.smartccraft.init;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import it.kytech.smartccraft.reference.Settings;

/**
 * Created by M2K on 27/06/2014.
 */
public class RecipesLoader {

    public static void init() {
        //TODO: Add recipes...
        if (Settings.integrateTE && Loader.isModLoaded("ThermalExpansion")) {
            //GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.rfCharger), "rlr", "lcl", "rer", 'r', "dustRedstone", 'l', "ingotLead", 'c', new ItemStack(GameRegistry.findItem("ThermalExpansion", "capacitor"), 1, 2), 'e', new ItemStack(GameRegistry.findItem("ThermalExpansion", "material"), 1, 3)));
        } else if (Settings.integrateBC && Loader.isModLoaded("BuildCraft|Core")) {
            //GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.rfCharger), "rlr", "lcl", "rer", 'r', "dustRedstone", 'l', "gearIron", 'c', "gearGold", 'e', new ItemStack(GameRegistry.findItem("BuildCraft|Transport", "item.buildcraftPipe.pipepowergold"))));
        }else {

        }
    }
}
