package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.registry.DyedVoidBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;

import net.minecraft.core.HolderLookup;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class BlockLootTableGenerator extends FabricBlockLootSubProvider {

    public BlockLootTableGenerator(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        Arrays.stream(DyedVoidBlocks.VOID_BLOCKS).forEach(this::dropSelf);
    }
}
