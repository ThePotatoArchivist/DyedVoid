package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.registry.DyedVoidBlockItemIds;
import archives.tater.dyedvoid.registry.DyedVoidBlockTags;

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
        builder(DyedVoidBlockTags.VOID_BLOCKS).add(DyedVoidBlockItemIds.ALL_VOID_BLOCKS);

        builder(BlockTags.NEEDS_IRON_TOOL).addTag(DyedVoidBlockTags.VOID_BLOCKS);
        builder(BlockTags.MINEABLE_WITH_PICKAXE).addTag(DyedVoidBlockTags.VOID_BLOCKS);
        builder(BlockTags.MINEABLE_WITH_AXE).addTag(DyedVoidBlockTags.VOID_BLOCKS);
        builder(BlockTags.MINEABLE_WITH_SHOVEL).addTag(DyedVoidBlockTags.VOID_BLOCKS);
        builder(BlockTags.MINEABLE_WITH_HOE).addTag(DyedVoidBlockTags.VOID_BLOCKS);
    }
}
