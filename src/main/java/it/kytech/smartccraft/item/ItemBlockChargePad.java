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
 * Created by Hitech95 on 14/10/2015.
 */
public class ItemBlockChargePad extends ItemBlock {
    public ItemBlockChargePad(Block block) {
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
        //TODO Custom tootips for chargepad
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
