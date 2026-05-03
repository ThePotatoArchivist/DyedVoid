package archives.tater.dyedvoid.registry;

import archives.tater.dyedvoid.DyedVoid;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class DyedVoidItemTags {
    private static TagKey<Item> create(String no_gravity) {
        return TagKey.create(Registries.ITEM, DyedVoid.id(no_gravity));
    }

    public static final TagKey<Item> VOID_BLOCKS = create("void_blocks");
    public static final TagKey<Item> NO_GRAVITY = create("no_gravity");
    public static final TagKey<Item> PLACEABLE_IN_AIR = create("placeable_in_air");
}
