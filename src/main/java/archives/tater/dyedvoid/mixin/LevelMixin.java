package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.DyedVoidEnvironmentAttributes;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.attribute.EnvironmentAttributeSystem;
import net.minecraft.world.level.Level;

@Mixin(Level.class)
public abstract class LevelMixin {
    @Shadow
    public abstract EnvironmentAttributeSystem environmentAttributes();

    @ModifyReturnValue(
            method = "canHaveWeather",
            at = @At("RETURN")
    )
    private boolean weatherAttribute(boolean original) {
        var attributes = environmentAttributes();
        if (attributes == null) return original;
        return attributes
                .getDimensionValue(DyedVoidEnvironmentAttributes.HAS_WEATHER)
                .toBoolean(original);
    }
}
