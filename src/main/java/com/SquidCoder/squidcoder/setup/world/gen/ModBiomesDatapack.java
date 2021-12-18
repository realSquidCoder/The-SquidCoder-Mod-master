package com.SquidCoder.squidcoder.setup.world.gen;

import com.SquidCoder.squidcoder.SquidCoderMod;
import com.SquidCoder.squidcoder.setup.Registration;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.common.BiomeManager;

public class ModBiomesDatapack {

    public static RegistryKey<Biome> LUSH_CAVES_BIOME = registerBiome("lush_caves_biome");
    public static RegistryKey<Biome> TAR_PITS_BIOME = registerBiome("tar_pits_biome");

    public static RegistryKey<Biome> registerBiome(String biomeName) {
        Registration.BIOMES.register(biomeName, BiomeMaker::theVoidBiome);
        return RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(SquidCoderMod.MOD_ID, biomeName));
    }

    public static void register() {
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(LUSH_CAVES_BIOME, 10));
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(TAR_PITS_BIOME, 10));
    }
}