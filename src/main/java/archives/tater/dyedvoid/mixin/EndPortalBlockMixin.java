package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.block.EndVoidBlock;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.portal.TeleportTransition;

@Mixin(EndPortalBlock.class)
public class EndPortalBlockMixin {
    @Inject(
            method = "getPortalDestination",
            at = @At(value = "TAIL")
    )
    private void craftEndVoid(ServerLevel currentLevel, Entity entity, BlockPos portalEntryPos, CallbackInfoReturnable<TeleportTransition> cir) {
        EndVoidBlock.tryCraft(entity);
    }
}
