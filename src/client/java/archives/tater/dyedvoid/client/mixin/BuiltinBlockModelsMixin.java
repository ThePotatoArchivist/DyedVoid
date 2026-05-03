package archives.tater.dyedvoid.client.mixin;

import archives.tater.dyedvoid.DyedVoidClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.renderer.block.BuiltInBlockModels;

@Mixin(BuiltInBlockModels.class)
public class BuiltinBlockModelsMixin {
    @Inject(
            method = "addDefaults",
            at = @At("TAIL")
    )
    private static void addBuiltinModels(BuiltInBlockModels.Builder builder, CallbackInfo ci) {
        DyedVoidClient.initBuiltinBlockModels(builder);
    }
}
