package archives.tater.dyedvoid.client.mixin;

import archives.tater.dyedvoid.client.DyedVoidClient;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.renderpearl.api.commands.RenderPass;
import com.mojang.renderpearl.api.textures.FilterMode;
import net.minecraft.client.renderer.LevelRenderer;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @WrapOperation(
            method = "lambda$addMainPass$0",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;bindDefaultUniforms(Lcom/mojang/renderpearl/api/commands/RenderPass;)V")
    )
    private void addSkyBuffer(RenderPass renderPass, Operation<Void> original) {
        renderPass.setUniform(DyedVoidClient.SKY_SAMPLER_NAME, DyedVoidClient.SKY_BUFFER.getColorTextureView(), RenderSystem.getSamplerCache().getClampToEdge(FilterMode.NEAREST));
    }
}
