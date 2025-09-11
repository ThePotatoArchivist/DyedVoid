package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.DyedVoidItems;
import archives.tater.dyedvoid.DyedVoidSounds;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlassBottleItem.class)
public class GlassBottleItemMixin extends Item {
    public GlassBottleItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(
            method = "use",
            at = @At(value = "FIELD", target = "Lnet/minecraft/util/ActionResult;PASS:Lnet/minecraft/util/ActionResult$Pass;", ordinal = 0),
            cancellable = true
    )
    private void getVoidBottle(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> cir, @Local BlockHitResult blockHitResult) {
        if (blockHitResult.getType() == HitResult.Type.MISS && blockHitResult.getBlockPos().getY() < world.getBottomY()) {
            var stack = user.getStackInHand(hand);
            if (stack.getCount() > 1 || user.isCreative()) {
                if (!user.isCreative()) {
                    stack.decrement(1);
                }
                user.giveItemStack(new ItemStack(DyedVoidItems.VOID_BOTTLE_ITEM));
            } else {
                user.setStackInHand(hand, new ItemStack(DyedVoidItems.VOID_BOTTLE_ITEM));
            }
            world.playSound(null, user.getX(), user.getY(), user.getZ(), DyedVoidSounds.FILL_VOID_BOTTLE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            cir.setReturnValue(ActionResult.SUCCESS.withNewHandStack(stack));
        }
    }
}
