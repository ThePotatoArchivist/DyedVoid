package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoidBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {

    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        valueLookupBuilder(DyedVoidBlocks.VOID_BLOCKS_TAG).add(DyedVoidBlocks.VOID_BLOCKS);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL).forceAddTag(DyedVoidBlocks.VOID_BLOCKS_TAG);
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE).forceAddTag(DyedVoidBlocks.VOID_BLOCKS_TAG);
        valueLookupBuilder(BlockTags.AXE_MINEABLE).forceAddTag(DyedVoidBlocks.VOID_BLOCKS_TAG);
        valueLookupBuilder(BlockTags.SHOVEL_MINEABLE).forceAddTag(DyedVoidBlocks.VOID_BLOCKS_TAG);
        valueLookupBuilder(BlockTags.HOE_MINEABLE).forceAddTag(DyedVoidBlocks.VOID_BLOCKS_TAG);
    }
}
