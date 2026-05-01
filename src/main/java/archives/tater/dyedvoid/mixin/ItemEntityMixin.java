package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.registry.DyedVoidItems;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

	public ItemEntityMixin(EntityType<?> type, Level world) {
		super(type, world);
	}

	@Inject(
			method = "<init>(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;DDD)V",
			at = @At("TAIL")
	)
	private void checkNoGravityTag(Level level, double x, double y, double z, ItemStack itemStack, double deltaX, double deltaY, double deltaZ, CallbackInfo ci) {
		if (itemStack.is(DyedVoidItems.NO_GRAVITY_TAG))
			setNoGravity(true);
	}

	@Inject(
			method = "<init>(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;)V",
			at = @At("TAIL")
	)
	private void checkNoGravityTag(Level level, double x, double y, double z, ItemStack itemStack, CallbackInfo ci) {
		if (itemStack.is(DyedVoidItems.NO_GRAVITY_TAG))
			setNoGravity(true);
	}
}
