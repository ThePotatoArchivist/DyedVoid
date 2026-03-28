package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.registry.DyedVoidBlocks;

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
        valueLookupBuilder(DyedVoidBlocks.VOID_PLATES_TAG).add(DyedVoidBlocks.VOID_PLATES);
        valueLookupBuilder(DyedVoidBlocks.ALL_VOID_BLOCKS_TAG)
                .addTag(DyedVoidBlocks.VOID_BLOCKS_TAG)
                .addTag(DyedVoidBlocks.VOID_PLATES_TAG);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL).addTag(DyedVoidBlocks.ALL_VOID_BLOCKS_TAG);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).addTag(DyedVoidBlocks.ALL_VOID_BLOCKS_TAG);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).addTag(DyedVoidBlocks.ALL_VOID_BLOCKS_TAG);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL).addTag(DyedVoidBlocks.ALL_VOID_BLOCKS_TAG);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_HOE).addTag(DyedVoidBlocks.ALL_VOID_BLOCKS_TAG);
    }
}
