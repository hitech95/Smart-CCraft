/**
 * This file is part of SmartCCraft
 * <p/>
 * Copyright (c) 2015 hitech95 <https://github.com/hitech95>
 * Copyright (c) contributors
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.kytech.smartccraft.block;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import it.kytech.smartccraft.SmartCCraft;
import it.kytech.smartccraft.reference.GuiIds;
import it.kytech.smartccraft.reference.Names;
import it.kytech.smartccraft.tileentity.TileChargeStation;
import it.kytech.smartccraft.tileentity.TileChargeStationMK2;
import it.kytech.smartccraft.tileentity.TileChargeStationMK3;
import it.kytech.smartccraft.tileentity.TileChargeStationMK4;
import it.kytech.smartccraft.util.CenterFaceHelper;
import it.kytech.smartccraft.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

public class BlockChargeStation extends BlockTileSCC implements ITileEntityProvider {

    public static final int MAX_TIER = 3;

    @SideOnly(Side.CLIENT)
    private IIcon[] blockIconFront;
    @SideOnly(Side.CLIENT)
    private IIcon[] blockIconSide;

    public BlockChargeStation() {
        super(Material.rock);
        this.setHardness(1.0f);
        this.setBlockName(Names.Blocks.CHARGE_STATION);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metaData) {

        if (metaData > MAX_TIER) {
            return null;
        }

        switch (metaData) {
            case 0:
                return new TileChargeStation();
            case 1:
                return new TileChargeStationMK2();
            case 2:
                return new TileChargeStationMK3();
            case 3:
                return new TileChargeStationMK4();
            default:
                return null;
        }
    }

    @Override
    public int damageDropped(int metaData) {
        return metaData;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {

        blockIconFront = new IIcon[MAX_TIER + 1];
        blockIconSide = new IIcon[MAX_TIER + 1];

        for (int meta = 0; meta < 4; meta++) {
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

            return getIcon(SIDE_OFFSETS[direction.ordinal()][side], tileChargeStation.getTier());
        }

        return null;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {

        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (!(tileEntity instanceof TileChargeStation)) {
            return;
        }

        TileChargeStation tileChargeStation = (TileChargeStation) tileEntity;

        if(!((TileChargeStation) tileEntity).isWorking()){
            return;
        }

        ForgeDirection orientation = tileChargeStation.getOrientation();

        double dX = x + (CenterFaceHelper.getFace(orientation.ordinal()).offsetX);
        double dY = y + (CenterFaceHelper.getFace(orientation.ordinal()).offsetY);
        double dZ = z + (CenterFaceHelper.getFace(orientation.ordinal()).offsetZ);

        world.spawnParticle("reddust", dX, dY, dZ, 0.0D, 0.0D, 0.0D);
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

    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
        super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
        if (!world.isRemote) {
            updateTileEntityState(world, x, y, z);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        super.onNeighborBlockChange(world, x, y, z, block);
        if (!world.isRemote) {
            updateTileEntityState(world, x, y, z);
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        if (!world.isRemote) {
            updateTileEntityState(world, x, y, z);
        }
    }

    public void updateTileEntityState(World world, int x, int y, int z) {
        boolean state = world.isBlockIndirectlyGettingPowered(x, y, z);
        if (world.getTileEntity(x, y, z) instanceof TileChargeStation) {
            ((TileChargeStation) world.getTileEntity(x, y, z)).setRedstoneState(state);
        }
    }
}
