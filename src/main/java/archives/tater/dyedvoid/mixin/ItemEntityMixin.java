package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.DyedVoidItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

	public ItemEntityMixin(EntityType<?> type, Level world) {
		super(type, world);
	}

	@Inject(
			method = "<init>(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;DDD)V",
			at = @At("TAIL")
	)
	private void checkNoGravityTag(Level world, double x, double y, double z, ItemStack stack, double velocityX, double velocityY, double velocityZ, CallbackInfo ci) {
		if (stack.is(DyedVoidItems.NO_GRAVITY_TAG))
			setNoGravity(true);
	}
}
