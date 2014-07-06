package it.kytech.smartccraft.client.gui.configuration;


import cpw.mods.fml.client.config.DummyConfigElement;
import cpw.mods.fml.client.config.DummyConfigElement.DummyCategoryElement;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import it.kytech.smartccraft.client.gui.configuration.category.CategoryEnergy;
import it.kytech.smartccraft.client.gui.configuration.category.CategoryGeneral;
import it.kytech.smartccraft.client.gui.configuration.category.CategoryIntegration;
import it.kytech.smartccraft.handler.ConfigurationHandler;
import it.kytech.smartccraft.reference.Reference;
import it.kytech.smartccraft.reference.Settings;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.ConfigElement;

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
        elements.add(new DummyConfigElement.DummyCategoryElement(Reference.MOD_ID, "configuration.smartccraft.category.general", CategoryGeneral.class));

        //Integration
        elements.add(new DummyCategoryElement(Reference.MOD_ID, "configuration.smartccraft.category.integration", CategoryIntegration.class));

        //Energy
        elements.add(new DummyCategoryElement(Reference.MOD_ID, "configuration.smartccraft.category.energyStorage", CategoryEnergy.class));

        return elements;

    }
}
