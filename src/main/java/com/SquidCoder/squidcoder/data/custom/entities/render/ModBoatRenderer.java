package com.SquidCoder.squidcoder.data.custom.entities.render;

import com.SquidCoder.squidcoder.SquidCoderMod;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.ResourceLocation;

public class ModBoatRenderer extends BoatRenderer {
    private static final ResourceLocation BOAT_TEXTURE =
            new ResourceLocation(SquidCoderMod.MOD_ID, "textures/entity/boat/redwood.png");

    public ModBoatRenderer(EntityRendererManager p_i46179_1_) {
        super(p_i46179_1_);
    }

    @Override
    public ResourceLocation getTextureLocation(BoatEntity entity) {
        return BOAT_TEXTURE;
    }
}
