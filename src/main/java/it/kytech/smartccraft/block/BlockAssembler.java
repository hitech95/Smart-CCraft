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

public class BlockAssembler extends BlockTileSCC implements ITileEntityProvider {

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
