package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.registry.DyedVoidItems;
import archives.tater.dyedvoid.registry.DyedVoidSounds;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

@Mixin(BottleItem.class)
public abstract class BottleItemMixn extends Item {
    @Shadow
    protected abstract ItemStack turnBottleIntoItem(ItemStack itemStack, Player player, ItemStack itemStackToTurnInto);

    public BottleItemMixn(Properties settings) {
        super(settings);
    }

    @Definition(id = "PASS", field = "Lnet/minecraft/world/InteractionResult;PASS:Lnet/minecraft/world/InteractionResult$Pass;")
    @Expression("return PASS")
    @ModifyReturnValue(
            method = "use",
            at = @At("MIXINEXTRAS:EXPRESSION:FIRST")
    )
    private InteractionResult getVoidBottle(InteractionResult original, Level level, Player player, InteractionHand hand, @Local(name = "hitResult") BlockHitResult hitResult) {
        if (hitResult.getBlockPos().getY() >= level.getMinY()) return original;

        level.playSound(null, player.getX(), player.getY(), player.getZ(), DyedVoidSounds.FILL_VOID_BOTTLE, SoundSource.NEUTRAL, 1.0F, 1.0F);

        return InteractionResult.SUCCESS.heldItemTransformedTo(
                turnBottleIntoItem(player.getItemInHand(hand), player, DyedVoidItems.VOID_BOTTLE_ITEM.getDefaultInstance())
        );
    }
}
