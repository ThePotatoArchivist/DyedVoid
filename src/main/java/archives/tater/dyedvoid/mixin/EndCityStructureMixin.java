package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.DyedVoidTags;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.structures.EndCityStructure;

@Mixin(EndCityStructure.class)
public class EndCityStructureMixin {
    @Definition(id = "getY", method = "Lnet/minecraft/core/BlockPos;getY()I")
    @Expression("?.getY() < 60")
    @ModifyExpressionValue(
            method = "findGenerationPoint",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private boolean minY(boolean original, Structure.GenerationContext context) {
        return original && (!(context.biomeSource() instanceof FixedBiomeSource fixedBiomeSource)
                || !fixedBiomeSource.getNoiseBiome(0, 0, 0).is(DyedVoidTags.END_CITY_ANY_HEIGHT));
    }
}
