package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.DyedVoidEnvironmentAttributes;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.chunk.ChunkGenerator;

@Mixin(NaturalSpawner.class)
public class NaturalSpawnerMixin {
    @ModifyReturnValue(
            method = "mobsAt",
            at = @At("RETURN")
    )
    private static WeightedList<MobSpawnSettings.SpawnerData> allowStructureSpawnAttribute(WeightedList<MobSpawnSettings.SpawnerData> original, final ServerLevel level, final StructureManager structureManager, final ChunkGenerator generator, final MobCategory mobCategory, final BlockPos pos) {
        return level.environmentAttributes().getValue(DyedVoidEnvironmentAttributes.NATURAL_MOB_SPAWN, pos) ? original : WeightedList.of();
    }
}
