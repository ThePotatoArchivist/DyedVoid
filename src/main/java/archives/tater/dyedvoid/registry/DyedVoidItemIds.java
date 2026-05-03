package archives.tater.dyedvoid.registry;

import archives.tater.dyedvoid.DyedVoid;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class DyedVoidItemIds {
    private static ResourceKey<Item> create(String path) {
        return ResourceKey.create(Registries.ITEM, DyedVoid.id(path));
    }

    public static final ResourceKey<Item> VOID_BOTTLE = create("void_bottle");
    public static final ResourceKey<Item> DUMMY_END_PORTAL = create("dummy/end_portal");
    public static final ResourceKey<Item> DUMMY_END_GATEWAY = create("dummy/end_gateway");
}
