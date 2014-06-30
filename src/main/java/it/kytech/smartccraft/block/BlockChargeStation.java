package it.kytech.smartccraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import it.kytech.smartccraft.SmartCCraft;
import it.kytech.smartccraft.reference.GuiIds;
import it.kytech.smartccraft.reference.Names;
import it.kytech.smartccraft.tileentity.TileChargeStation;
import it.kytech.smartccraft.tileentity.TileChargeStationMK2;
import it.kytech.smartccraft.tileentity.TileChargeStationMK3;
import it.kytech.smartccraft.tileentity.TileChargeStationMK4;
import it.kytech.smartccraft.util.LogHelper;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

public class BlockChargeStation extends BlockSCC implements ITileEntityProvider {

    @SideOnly(Side.CLIENT)
    IIcon[] blockIconFront;
    @SideOnly(Side.CLIENT)
    IIcon[] blockIconSide;

    public BlockChargeStation() {
        super(Material.rock);
        this.setHardness(1.0f);
        this.setBlockName(Names.Blocks.CHARGE_STATION);

        blockIconFront = new IIcon[4];
        blockIconSide = new IIcon[4];
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData) {
        if (metaData == 0) {
            return new TileChargeStation();
        } else if (metaData == 1) {
            return new TileChargeStationMK2();
        } else if (metaData == 2) {
            return new TileChargeStationMK3();
        } else if (metaData == 3) {
            return new TileChargeStationMK4();
        }

        return null;
    }

    @Override
    public int damageDropped(int metaData) {
        return metaData;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {

        for (int meta = 0; meta < 4; meta++) {

            LogHelper.info(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())) + "_" + meta);
            LogHelper.info(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())) + "_" + meta + "_front");

            blockIconSide[meta] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())) + "_" + meta);
            blockIconFront[meta] = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName()) + "_" + meta + "_front"));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 3) {
            return blockIconFront[meta];
        } else {
            return blockIconSide[meta];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if ((tileEntity instanceof TileChargeStation)) {
            TileChargeStation tileChargeStation = (TileChargeStation) tileEntity;
            ForgeDirection direction = tileChargeStation.getOrientation();

            return getIcon(SIDE_OFFSETS[direction.ordinal()][side], tileChargeStation.tier);
        }

        return null;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        if (!world.isRemote && world.getTileEntity(x, y, z) instanceof TileChargeStation) {
            player.openGui(SmartCCraft.instance, GuiIds.CHARGE_STATION, world, x, y, z);
        }

        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        for (int meta = 0; meta < 4; meta++) {
            list.add(new ItemStack(item, 1, meta));
        }
    }
}
