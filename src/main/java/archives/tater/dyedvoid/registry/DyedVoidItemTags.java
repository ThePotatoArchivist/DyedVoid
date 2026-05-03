package archives.tater.dyedvoid.registry;

import archives.tater.dyedvoid.DyedVoid;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class DyedVoidItemTags {

    private static TagKey<Item> create(String path) {
        return TagKey.create(Registries.ITEM, DyedVoid.id(path));
    }

    public static final TagKey<Item> NO_GRAVITY = create("no_gravity");
    public static final TagKey<Item> END_VOID_INGREDIENT = create("end_void_ingredient");
    public static final TagKey<Item> PLACEABLE_IN_AIR = create("placeable_in_air");
    public static final TagKey<Item> SULFUR_CUBE_ARCHETYPE_NO_GRAVITY = create("sulfur_cube_archetype/no_gravity");
}
