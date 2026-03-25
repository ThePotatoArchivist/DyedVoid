package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoidBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagsProvider.BlockTagsProvider {

    public BlockTagGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        valueLookupBuilder(DyedVoidBlocks.VOID_BLOCKS_TAG).add(DyedVoidBlocks.VOID_BLOCKS);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL).forceAddTag(DyedVoidBlocks.VOID_BLOCKS_TAG);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).forceAddTag(DyedVoidBlocks.VOID_BLOCKS_TAG);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).forceAddTag(DyedVoidBlocks.VOID_BLOCKS_TAG);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL).forceAddTag(DyedVoidBlocks.VOID_BLOCKS_TAG);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_HOE).forceAddTag(DyedVoidBlocks.VOID_BLOCKS_TAG);
    }
}
