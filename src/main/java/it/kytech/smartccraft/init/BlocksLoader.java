package it.kytech.smartccraft.init;

import cpw.mods.fml.common.registry.GameRegistry;
import it.kytech.smartccraft.block.BlockAssembler;
import it.kytech.smartccraft.block.BlockChargeStation;
import it.kytech.smartccraft.block.BlockSCC;
import it.kytech.smartccraft.item.ItemBlockChargeStation;
import it.kytech.smartccraft.reference.Names;
import it.kytech.smartccraft.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class BlocksLoader {

    public static final BlockSCC assemblerBlock = new BlockAssembler();
    public static final BlockSCC stationBlock = new BlockChargeStation();

    public static void init() {
        GameRegistry.registerBlock(assemblerBlock, Names.Blocks.ASSEMBLER);
        GameRegistry.registerBlock(stationBlock, ItemBlockChargeStation.class, Names.Blocks.CHARGE_STATION);
    }
}
