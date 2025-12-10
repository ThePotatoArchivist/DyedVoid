package archives.tater.dyedvoid.client.mixin;

import archives.tater.dyedvoid.DyedVoidClient;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.item.model.BasicItemModel;
import net.minecraft.item.ItemStack;

@Mixin(BasicItemModel.class)
public class BasicItemModelMixin {
    @Inject(
            method = "method_76557",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void modifyVoidItemLayers(ItemStack stack, CallbackInfoReturnable<RenderLayer> cir) {
        if (DyedVoidClient.isPortalRendered(stack)) {
            cir.setReturnValue(RenderLayers.endPortal());
            return;
        }
        if (DyedVoidClient.isFlatRendered(stack)) cir.setReturnValue(RenderLayers.solid());
    }
}
