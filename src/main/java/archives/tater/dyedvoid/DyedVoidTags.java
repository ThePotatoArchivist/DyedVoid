package archives.tater.dyedvoid;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;

public class DyedVoidTags {
    private static <T> TagKey<T> of(ResourceKey<Registry<T>> registry, String path) {
        return TagKey.create(registry, DyedVoid.id(path));
    }

    public static final TagKey<DimensionType> NO_WEATHER = of(Registries.DIMENSION_TYPE, "no_weather");
    public static final TagKey<Biome> END_CITY_ANY_HEIGHT = of(Registries.BIOME, "end_city_any_height");
}
