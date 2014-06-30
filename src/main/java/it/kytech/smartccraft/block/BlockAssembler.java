package it.kytech.smartccraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import it.kytech.smartccraft.SmartCCraft;
import it.kytech.smartccraft.reference.GuiIds;
import it.kytech.smartccraft.reference.Names;
import it.kytech.smartccraft.tileentity.TileAssembler;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockAssembler extends BlockSCC implements ITileEntityProvider {

    @SideOnly(Side.CLIENT)
    IIcon blockIconTop;

    @SideOnly(Side.CLIENT)
    IIcon blockIconBottom;

    public BlockAssembler() {
        super(Material.rock);
        this.setHardness(1.0f);
        this.setBlockName(Names.Blocks.ASSEMBLER);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData) {
        return new TileAssembler();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
        blockIconTop = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_top"));
        blockIconBottom = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_bottom"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 0) {
            return blockIconBottom;
        } else if (side == 1) {
            return blockIconTop;
        } else {
            return blockIcon;
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileAssembler) {
            player.openGui(SmartCCraft.instance, GuiIds.ASSEMBLER, world, x, y, z);
        }

        return true;
    }
}
