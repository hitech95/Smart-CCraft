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
package it.kytech.smartccraft.reference;

import it.kytech.smartccraft.util.helper.ResourceLocationHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

/**
 * Created by M2K on 27/06/2014.
 */
public class Textures {

    public static final String RESOURCE_PREFIX = Reference.LOWERCASE_MOD_ID + ":";

    // Model textures
    public static final String MODEL_TEXTURE_LOCATION = "textures/models/";

    // Armor textures
    public static final String ARMOR_SHEET_LOCATION = "textures/armor/";

    // GUI textures
    public static final String GUI_SHEET_LOCATION = "textures/gui/";

    public static final ResourceLocation GUI_CHARGE_STATION = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "chargestation.png");
    public static final ResourceLocation GUI_ASSEMBLER = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "assembler.png");

    // Effect textures
    public static final String EFFECTS_LOCATION = "textures/effects/";

    // Item/Block sprite sheets
    public static final ResourceLocation VANILLA_BLOCK_TEXTURE_SHEET = TextureMap.locationBlocksTexture;
    public static final ResourceLocation VANILLA_ITEM_TEXTURE_SHEET = TextureMap.locationItemsTexture;

}
