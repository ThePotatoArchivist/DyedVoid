package archives.tater.dyedvoid.registry;

import archives.tater.dyedvoid.DyedVoid;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class DyedVoidItemTags {

    private static TagKey<Item> createTag(String path) {
        return TagKey.create(Registries.ITEM, DyedVoid.id(path));
    }

    public static final TagKey<Item> NO_GRAVITY = createTag("no_gravity");
    public static final TagKey<Item> END_VOID_INGREDIENT = createTag("end_void_ingredient");
}
