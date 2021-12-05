package com.SquidCoder.squidcoder.data.custom.screens;

import com.SquidCoder.squidcoder.SquidCoderMod;
import com.SquidCoder.squidcoder.data.custom.containers.ShipwrightsTableContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ShipwrightsTableScreen extends ContainerScreen<ShipwrightsTableContainer> {
    private final ResourceLocation GUI = new ResourceLocation(SquidCoderMod.MOD_ID,
            "textures/gui/shipwrights_table.png");

    public ShipwrightsTableScreen(ShipwrightsTableContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderLabels(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bind(GUI);
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);


        /*
        //How to render things like the progress arrow and such:
        if(booleanStatement) {
            this.blit(matrixStack, i + 82, j + 9, 176, 0, 13, 17);
        }
        */
    }
}