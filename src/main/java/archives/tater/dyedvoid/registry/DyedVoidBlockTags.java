package archives.tater.dyedvoid.registry;

import archives.tater.dyedvoid.DyedVoid;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class DyedVoidBlockTags {
    public static final TagKey<Block> VOID_BLOCKS = TagKey.create(Registries.BLOCK, DyedVoid.id("void_blocks"));
    public static final TagKey<Block> HIDDEN_OUTLINE = TagKey.create(Registries.BLOCK, DyedVoid.id("hidden_outline"));
}
