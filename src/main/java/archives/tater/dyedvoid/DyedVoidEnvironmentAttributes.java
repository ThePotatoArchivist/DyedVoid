package archives.tater.dyedvoid;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.attribute.AttributeTypes;
import net.minecraft.world.attribute.EnvironmentAttribute;

public class DyedVoidEnvironmentAttributes {
    private static <T> EnvironmentAttribute<T> register(String path, EnvironmentAttribute<T> attribute) {
        return Registry.register(BuiltInRegistries.ENVIRONMENT_ATTRIBUTE, DyedVoid.id(path), attribute);
    }

    private static <T> EnvironmentAttribute<T> register(String path, EnvironmentAttribute.Builder<T> attribute) {
        return register(path, attribute.build());
    }

    public static final EnvironmentAttribute<Boolean> STRUCTURE_MOB_SPAWN = register("gameplay/structure_mob_spawn", EnvironmentAttribute.builder(AttributeTypes.BOOLEAN)
            .defaultValue(true)
    );

    public static void init() {

    }
}
