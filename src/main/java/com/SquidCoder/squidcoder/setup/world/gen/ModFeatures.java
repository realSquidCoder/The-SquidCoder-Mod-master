package com.SquidCoder.squidcoder.setup.world.gen;

import com.SquidCoder.squidcoder.setup.world.gen.features.GeodeFeature;
import com.SquidCoder.squidcoder.setup.Registration;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;

public class ModFeatures {

	public static final RegistryObject<GeodeFeature> GEODE = Registration.FEATURES.register("geode", () -> new GeodeFeature(NoFeatureConfig.CODEC));

	public static void generateFeatures(BiomeLoadingEvent event) {}
    public static void register(){

	}

}
