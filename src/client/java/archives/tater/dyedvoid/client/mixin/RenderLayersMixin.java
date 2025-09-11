package archives.tater.dyedvoid.client.mixin;

import archives.tater.dyedvoid.DyedVoidClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderLayers.class)
public class RenderLayersMixin {
    @Inject(
            method = "getItemLayer",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void modifyVoidItemLayers(ItemStack stack, CallbackInfoReturnable<RenderLayer> cir) {
        if (DyedVoidClient.isPortalRendered(stack)) {
            cir.setReturnValue(RenderLayer.getEndPortal());
            return;
        }
        if (DyedVoidClient.isFlatRendered(stack)) cir.setReturnValue(RenderLayer.getSolid());
    }
}
