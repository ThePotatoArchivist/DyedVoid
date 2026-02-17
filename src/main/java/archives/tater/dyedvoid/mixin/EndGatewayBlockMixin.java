package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.EndVoidBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.EndGatewayBlock;
import net.minecraft.world.level.portal.TeleportTransition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndGatewayBlock.class)
public class EndGatewayBlockMixin {
    @Inject(
            method = "getPortalDestination",
            at = @At("RETURN")
    )
    private void craftEndVoid(ServerLevel world, Entity entity, BlockPos pos, CallbackInfoReturnable<TeleportTransition> cir) {
        if (cir.getReturnValue() != null)
            EndVoidBlock.tryCraft(entity);
    }
}
