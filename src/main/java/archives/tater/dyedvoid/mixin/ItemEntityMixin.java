package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.registry.DyedVoidItems;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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
    @Shadow
    public abstract void setItem(ItemStack stack);

    @Shadow
    public abstract ItemStack getItem();

    public ItemEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    private void convert(CallbackInfo ci) {
        var stack = getItem();
        if (stack.is(DyedVoidItems.BLACK_VOID) && getY() > level().getMaxBuildHeight())
            setItem(stack.transmuteCopy(DyedVoidItems.SKY_VOID));
        else if (stack.is(DyedVoidItems.SKY_VOID) && 192 < getY() && getY() < 196)
            setItem(stack.transmuteCopy(DyedVoidItems.CLOUD_VOID));
    }
}
