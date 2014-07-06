package it.kytech.smartccraft.client.gui.configuration.category;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.GuiConfigEntries;
import cpw.mods.fml.client.config.GuiConfigEntries.CategoryEntry;
import cpw.mods.fml.client.config.IConfigElement;
import it.kytech.smartccraft.handler.ConfigurationHandler;
import it.kytech.smartccraft.reference.Reference;
import it.kytech.smartccraft.reference.Settings;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

/**
 * Created by M2K on 04/07/2014.
 */
public class CategoryGeneral extends CategoryEntry {

    public CategoryGeneral(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement prop) {
        super(owningScreen, owningEntryList, prop);
    }

    @Override
    protected GuiScreen buildChildScreen() {
        return new GuiConfig(this.owningScreen,
                new ConfigElement(ConfigurationHandler.configuration.getCategory(Settings.GENERAL_CATEGORY)).getChildElements(),
                Reference.MOD_ID,
                this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
                this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart,
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString())
        );
    }
}
