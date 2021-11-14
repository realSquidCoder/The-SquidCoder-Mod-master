package com.SquidCoder.squidcoder.setup;

import com.SquidCoder.squidcoder.SquidCoderMod;
import com.SquidCoder.squidcoder.setup.Registration;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;

public class ModSoundEvents {

    public static final RegistryObject<SoundEvent> COPPER_BREAK =
            registerSoundEvent("block.copper.break");
    public static final RegistryObject<SoundEvent> COPPER_STEP =
            registerSoundEvent("block.copper.step");
    public static final RegistryObject<SoundEvent> COPPER_PLACE =
            registerSoundEvent("block.copper.place");
    public static final RegistryObject<SoundEvent> COPPER_HIT =
            registerSoundEvent("block.copper.hit");
    public static final RegistryObject<SoundEvent> COPPER_FALL =
            registerSoundEvent("block.copper.fall");


    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return Registration.SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(SquidCoderMod.MOD_ID, name)));
    }

    public static void register(){}
}
