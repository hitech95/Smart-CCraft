package it.kytech.smartccraft.util;

import it.kytech.smartccraft.reference.Reference;
import net.minecraft.util.ResourceLocation;

/**
 * Created by M2K on 29/06/2014.
 */
public class ResourceLocationHelper {
    public static ResourceLocation getResourceLocation(String modId, String path) {
        return new ResourceLocation(modId, path);
    }

    public static ResourceLocation getResourceLocation(String path) {
        return getResourceLocation(Reference.MOD_ID.toLowerCase(), path);
    }
}
