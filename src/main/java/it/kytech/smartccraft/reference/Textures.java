package it.kytech.smartccraft.reference;

import it.kytech.smartccraft.util.ResourceLocationHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

/**
 * Created by M2K on 27/06/2014.
 */
public class Textures {
    // Base file paths
    public static final String RESOURCE_PREFIX = Reference.MOD_ID.toLowerCase() + ":";


    // Model textures
    public static final String MODEL_TEXTURE_LOCATION = "textures/models/";

    // Armor textures
    public static final String ARMOR_SHEET_LOCATION = "textures/armor/";

    // GUI textures
    public static final String GUI_SHEET_LOCATION = "textures/gui/";

    public static final ResourceLocation GUI_CHARGE_STATION = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "chargestation.png");

    // Effect textures
    public static final String EFFECTS_LOCATION = "textures/effects/";

    // Item/Block sprite sheets
    public static final ResourceLocation VANILLA_BLOCK_TEXTURE_SHEET = TextureMap.locationBlocksTexture;
    public static final ResourceLocation VANILLA_ITEM_TEXTURE_SHEET = TextureMap.locationItemsTexture;

}
