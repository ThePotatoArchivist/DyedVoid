package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoid;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class BiomeTagGenerator extends FabricTagsProvider<Biome> {
    public BiomeTagGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.BIOME, registryLookupFuture);
    }

    public static final ResourceKey<Biome> WHITESPACE = ResourceKey.create(Registries.BIOME, DyedVoid.id("whitespace"));

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider registries) {
        Arrays.stream(BiomeTags.class.getDeclaredFields())
                .filter(field -> Modifier.isStatic(field.getModifiers()))
                .map(field -> {
                    try {
                        return field.get(null);
                    } catch (IllegalAccessException e) {
                        return null;
                    }
                })
                .filter(value -> value instanceof TagKey<?> && value != BiomeTags.HAS_NETHER_FOSSIL)
                .forEach(value -> {
                    var key = (TagKey<Biome>) value;
                    if (key.location().getPath().startsWith("has_structure/"))
                        builder(key).add(WHITESPACE);
                });
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(WHITESPACE, new Biome.BiomeBuilder()
                .temperature(0.5f)
                .downfall(0.5f)
                .generationSettings(new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER))
                        .build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(new BiomeSpecialEffects.Builder().waterColor(0).build())
                .build());
    }
}
