package archives.tater.dyedvoid.client.mixin;

import archives.tater.dyedvoid.DyedVoidClient;
import net.minecraft.client.renderer.item.BlockModelWrapper;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockModelWrapper.class)
public class BasicItemModelMixin {
    @Inject(
            method = "method_76557",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void modifyVoidItemLayers(ItemStack stack, CallbackInfoReturnable<RenderType> cir) {
        if (DyedVoidClient.isPortalRendered(stack)) {
            cir.setReturnValue(RenderTypes.endPortal());
            return;
        }
        if (DyedVoidClient.isFlatRendered(stack)) cir.setReturnValue(RenderTypes.solidMovingBlock());
    }
}
