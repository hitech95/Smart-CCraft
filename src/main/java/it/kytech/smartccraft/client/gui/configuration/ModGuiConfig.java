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
package it.kytech.smartccraft.client.gui.configuration;


import cpw.mods.fml.client.config.DummyConfigElement.DummyCategoryElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import it.kytech.smartccraft.client.gui.configuration.category.CategoryEnergy;
import it.kytech.smartccraft.client.gui.configuration.category.CategoryGeneral;
import it.kytech.smartccraft.client.gui.configuration.category.CategoryIntegration;
import it.kytech.smartccraft.handler.ConfigurationHandler;
import it.kytech.smartccraft.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M2K on 04/07/2014.
 */
public class ModGuiConfig extends GuiConfig {

    public ModGuiConfig(GuiScreen guiScreen) {
        super(
                guiScreen,
                getConfigElements(),
                Reference.MOD_ID,
                null,
                false,
                false,
                StatCollector.translateToLocal("configuration.smartccraft.title"),
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> elements = new ArrayList<IConfigElement>();
        //General
        elements.add(new DummyCategoryElement(Reference.MOD_ID, "configuration.smartccraft.category.general", CategoryGeneral.class));

        //Integration
        elements.add(new DummyCategoryElement(Reference.MOD_ID, "configuration.smartccraft.category.integration", CategoryIntegration.class));

        //Energy
        elements.add(new DummyCategoryElement(Reference.MOD_ID, "configuration.smartccraft.category.energy", CategoryEnergy.class));

        return elements;

    }
}
