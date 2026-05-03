package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.registry.DyedVoidBlockItemIds;
import archives.tater.dyedvoid.registry.DyedVoidBlockItemTags;
import archives.tater.dyedvoid.registry.DyedVoidItemIds;
import archives.tater.dyedvoid.registry.DyedVoidItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;

import org.jspecify.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends FabricTagsProvider.ItemTagsProvider {

    public ItemTagGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture, @Nullable BlockTagsProvider blockTagsProvider) {
        super(output, registryLookupFuture, blockTagsProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        copy(DyedVoidBlockItemTags.VOID_BLOCKS.block(), DyedVoidBlockItemTags.VOID_BLOCKS.item());

        builder(DyedVoidItemTags.NO_GRAVITY)
                .add(DyedVoidItemIds.VOID_BOTTLE)
                .addTag(DyedVoidBlockItemTags.VOID_BLOCKS.item());

        builder(DyedVoidItemTags.END_VOID_INGREDIENT)
                .add(DyedVoidBlockItemIds.VOID.black());

        builder(DyedVoidItemTags.SULFUR_CUBE_ARCHETYPE_NO_GRAVITY)
                .addTag(DyedVoidBlockItemTags.VOID_BLOCKS.item());

        builder(ItemTags.SULFUR_CUBE_SWALLOWABLE)
                .addTag(DyedVoidItemTags.SULFUR_CUBE_ARCHETYPE_NO_GRAVITY);

        builder(TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("create", "upright_on_belt")))
                .add(DyedVoidItemIds.VOID_BOTTLE);
    }
}
