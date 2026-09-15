package archives.tater.dyedvoid.registry;

import archives.tater.dyedvoid.DyedVoid;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class DyedVoidBlockTags {
    private static TagKey<Block> create(String path) {
        return TagKey.create(Registries.BLOCK, DyedVoid.id(path));
    }

    public static final TagKey<Block> VOID_BLOCKS = create("void_blocks");
    public static final TagKey<Block> HIDDEN_OUTLINE = create("hidden_outline");
}
