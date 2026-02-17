package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.DyedVoidItems;
import archives.tater.dyedvoid.DyedVoidSounds;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BottleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BottleItem.class)
public class GlassBottleItemMixin extends Item {
    public GlassBottleItemMixin(Properties settings) {
        super(settings);
    }

    @Inject(
            method = "use",
            at = @At(value = "FIELD", target = "Lnet/minecraft/world/InteractionResult;PASS:Lnet/minecraft/world/InteractionResult$Pass;", ordinal = 0),
            cancellable = true
    )
    private void getVoidBottle(Level world, Player user, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir, @Local BlockHitResult blockHitResult) {
        if (blockHitResult.getType() == HitResult.Type.MISS && blockHitResult.getBlockPos().getY() < world.getMinY()) {
            var stack = user.getItemInHand(hand);
            if (stack.getCount() > 1 || user.isCreative()) {
                if (!user.isCreative()) {
                    stack.shrink(1);
                }
                user.addItem(new ItemStack(DyedVoidItems.VOID_BOTTLE_ITEM));
            } else {
                user.setItemInHand(hand, new ItemStack(DyedVoidItems.VOID_BOTTLE_ITEM));
            }
            world.playSound(null, user.getX(), user.getY(), user.getZ(), DyedVoidSounds.FILL_VOID_BOTTLE, SoundSource.NEUTRAL, 1.0F, 1.0F);
            cir.setReturnValue(InteractionResult.SUCCESS.heldItemTransformedTo(stack));
        }
    }
}
