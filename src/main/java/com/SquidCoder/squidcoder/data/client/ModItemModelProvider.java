package com.SquidCoder.squidcoder.data.client;

import com.SquidCoder.squidcoder.SquidCoderMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SquidCoderMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("silver_block", modLoc("block/silver_block"));

        withExistingParent("copper_block", modLoc("block/copper_block"));
        //withExistingParent("copper_block", modLoc("block/copper_block"));
        //withExistingParent("copper_block", modLoc("block/copper_block"));
        //withExistingParent("copper_block", modLoc("block/copper_block"));

        withExistingParent("waxed_copper_block", modLoc("block/waxed_copper_block"));

        //withExistingParent("shipwrights_table", modLoc("block/shipwrights_table"));
        //withExistingParent("boat_builder", modLoc("block/shipwrights_table"));
        withExistingParent("silver_ore", modLoc("block/silver_ore"));
        withExistingParent("copper_ore", modLoc("block/copper_ore"));
        withExistingParent("raw_copper_block", modLoc("block/raw_copper_block"));
        withExistingParent("raw_iron_block", modLoc("block/raw_iron_block"));
        withExistingParent("raw_gold_block", modLoc("block/raw_gold_block"));

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        builder(itemGenerated, "schematic");
        builder(itemGenerated, "empty_schematic");
        builder(itemGenerated, "propeller");

        //Ingots
        builder(itemGenerated, "silver_ingot");
        builder(itemGenerated, "copper_ingot");

        //Dusts
        builder(itemGenerated, "silver_dust");

        //Nuggets
        builder(itemGenerated, "silver_nugget");

        //Raw Ores
        builder(itemGenerated, "raw_copper");
        builder(itemGenerated, "raw_iron");
        builder(itemGenerated, "raw_gold");
        
        //Misc
        builder(itemGenerated, "amethyst_shard");
        builder(itemGenerated, "spyglass");
        
    }

    private ItemModelBuilder builder(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
