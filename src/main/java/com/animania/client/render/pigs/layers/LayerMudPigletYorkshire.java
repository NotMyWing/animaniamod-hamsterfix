package com.animania.client.render.pigs.layers;

import org.lwjgl.opengl.GL11;

import com.animania.client.models.ModelPiglet;
import com.animania.client.render.pigs.RenderPigletYorkshire;
import com.animania.common.entities.pigs.PigYorkshire.EntityPigletYorkshire;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerMudPigletYorkshire implements LayerRenderer<EntityPigletYorkshire>
{
    private static final ResourceLocation TEXTURE  = new ResourceLocation("animania:textures/entity/pigs/piglet_muddy.png");
    private final RenderPigletYorkshire   pigRenderer;
    private final ModelPiglet             pigModel = new ModelPiglet(0.5F);

    public LayerMudPigletYorkshire(RenderPigletYorkshire pigRendererIn) {
        this.pigRenderer = pigRendererIn;
    }

    @Override
    public void doRenderLayer(EntityPigletYorkshire entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
            float netHeadYaw, float headPitch, float scale) {

        if (entitylivingbaseIn.getMuddy()) {

            float splashTimer = entitylivingbaseIn.getSplashTimer();

            if (splashTimer <= 0) {
                GL11.glScalef(1.01F, 1.01F, 1.01F);
                this.pigRenderer.bindTexture(LayerMudPigletYorkshire.TEXTURE);
                GlStateManager.enableBlend();
                this.pigRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
                GlStateManager.depthMask(true);
                GlStateManager.disableBlend();
            }
        }
        else if (entitylivingbaseIn.getMudTimer() > 0) {

            float mudTimer = entitylivingbaseIn.getMudTimer();
            GL11.glScalef(1.01F, 1.01F, 1.01F);
            this.pigRenderer.bindTexture(LayerMudPigletYorkshire.TEXTURE);
            GlStateManager.enableBlend();
            GlStateManager.color(1.0F, 1.0F, 1.0F, mudTimer);
            this.pigRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.disableBlend();

        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}