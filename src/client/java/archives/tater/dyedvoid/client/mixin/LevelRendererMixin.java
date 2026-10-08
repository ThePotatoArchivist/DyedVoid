package archives.tater.dyedvoid.client.mixin;

import archives.tater.dyedvoid.client.DyedVoidClient;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.renderpearl.api.buffers.GpuBufferSlice;
import com.mojang.renderpearl.api.commands.RenderPass;
import com.mojang.renderpearl.api.textures.FilterMode;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.state.level.SkyRenderState;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @Shadow
    @Final
    private LevelTargetBundle targets;

    @WrapOperation(
            method = "lambda$addMainPass$0",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;bindDefaultUniforms(Lcom/mojang/renderpearl/api/commands/RenderPass;)V")
    )
    private void addSkyBuffer(RenderPass renderPass, Operation<Void> original) {
        original.call(renderPass);
        renderPass.setUniform(DyedVoidClient.SKY_SAMPLER_NAME, DyedVoidClient.getSkyBuffer().getColorTextureView(), RenderSystem.getSamplerCache().getClampToEdge(FilterMode.NEAREST));
    }

    @Inject(
            method = "resize",
            at = @At("TAIL")
    )
    private void resize(int width, int height, CallbackInfo ci) {
        DyedVoidClient.getSkyBuffer().resize(width, height);
    }

    @Inject(
            method = "lambda$addSkyPass$0",
            at = @At("TAIL")
    )
    private void copySky(GpuBufferSlice skyFog, SkyRenderState state, CallbackInfo ci) {
        DyedVoidClient.getSkyBuffer().copyColorFrom(targets.main.get());
    }
}
