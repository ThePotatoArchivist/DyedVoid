package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.EndVoidBlock;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.EndGatewayBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TeleportTarget;

@Mixin(EndGatewayBlock.class)
public class EndGatewayBlockMixin {
    @Inject(
            method = "createTeleportTarget",
            at = @At("RETURN")
    )
    private void craftEndVoid(ServerWorld world, Entity entity, BlockPos pos, CallbackInfoReturnable<TeleportTarget> cir) {
        if (cir.getReturnValue() != null)
            EndVoidBlock.tryCraft(entity);
    }
}
