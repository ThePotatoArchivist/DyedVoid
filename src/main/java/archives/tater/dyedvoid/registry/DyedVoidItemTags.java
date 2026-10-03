package archives.tater.dyedvoid.registry;

import archives.tater.dyedvoid.DyedVoid;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class DyedVoidItemTags {
    public static final TagKey<Item> VOID_BLOCKS = TagKey.create(Registries.ITEM, DyedVoid.id("void_blocks"));
    public static final TagKey<Item> HIDDEN_OUTLINE = TagKey.create(Registries.ITEM, DyedVoid.id("hidden_outline"));
    public static final TagKey<Item> NO_GRAVITY = TagKey.create(Registries.ITEM, DyedVoid.id("no_gravity"));
}
