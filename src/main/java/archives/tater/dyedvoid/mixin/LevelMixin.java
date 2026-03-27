package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.DyedVoidEnvironmentAttributes;
import archives.tater.dyedvoid.DyedVoidTags;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.Holder;
import net.minecraft.world.attribute.EnvironmentAttributeSystem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

@Mixin(Level.class)
public abstract class LevelMixin {
    @Shadow
    public abstract Holder<DimensionType> dimensionTypeRegistration();

    @ModifyReturnValue(
            method = "canHaveWeather",
            at = @At("RETURN")
    )
    private boolean weatherAttribute(boolean original) {
        return original && !dimensionTypeRegistration().is(DyedVoidTags.NO_WEATHER);
    }
}
