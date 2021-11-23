package com.SquidCoder.squidcoder.setup.world;

import com.SquidCoder.squidcoder.SquidCoderMod;
import com.SquidCoder.squidcoder.setup.world.gen.ModFeatures;
import com.SquidCoder.squidcoder.setup.world.gen.ModTreeGeneration;
import com.SquidCoder.squidcoder.setup.world.gen.ModOreGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SquidCoderMod.MOD_ID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModOreGeneration.generateOres(event);
        ModFeatures.generateFeatures(event);
        ModTreeGeneration.generateTrees(event);
        /*
        RAW_GENERATION,
        LAKES,
        LOCAL_MODIFICATIONS,
        UNDERGROUND_STRUCTURES,
        SURFACE_STRUCTURES,
        STRONGHOLDS,
        UNDERGROUND_ORES,
        UNDERGROUND_DECORATION,
        VEGETAL_DECORATION,
        TOP_LAYER_MODIFICATION;
        */
    }
}