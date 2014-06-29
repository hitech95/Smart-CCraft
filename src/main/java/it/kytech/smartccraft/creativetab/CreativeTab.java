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

