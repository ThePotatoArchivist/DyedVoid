package archives.tater.dyedvoid.registry;

import archives.tater.dyedvoid.DyedVoid;

import net.minecraft.tags.BlockItemTagId;

public class DyedVoidBlockItemTags {

    private static BlockItemTagId create(String path) {
        return BlockItemTagId.create(DyedVoid.id(path), DyedVoid.id(path));
    }

    public static final BlockItemTagId VOID_BLOCKS = create("void_blocks");
}
