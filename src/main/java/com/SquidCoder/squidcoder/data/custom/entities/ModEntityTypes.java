package com.SquidCoder.squidcoder.data.custom.entities;

import com.SquidCoder.squidcoder.SquidCoderMod;
import com.SquidCoder.squidcoder.data.custom.entities.entity.ModBoatEntity;
import com.SquidCoder.squidcoder.setup.Registration;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class ModEntityTypes {
    public static final RegistryObject<EntityType<ModBoatEntity>> REDWOOD_BOAT = Registration.ENTITY_TYPES.register("redwood_boat", () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, EntityClassification.MISC).sized(0.5f, 0.5f).build(new ResourceLocation(SquidCoderMod.MOD_ID, "redwood_boat").toString()));

    public static void register() {
    }
}
