package it.kytech.smartccraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import it.kytech.smartccraft.reference.Names;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockAssembler extends BlockSCC {

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
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit, float hitX, float hitY, float hitZ, int metaData) {
        return sideHit;
    }
}
