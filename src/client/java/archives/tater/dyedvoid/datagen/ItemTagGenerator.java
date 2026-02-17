package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoidItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {

    public ItemTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        valueLookupBuilder(DyedVoidItems.NO_GRAVITY_TAG).add(
                DyedVoidItems.VOID_BOTTLE_ITEM
        ).add(DyedVoidItems.VOID_BLOCKS);

        valueLookupBuilder(TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("create", "upright_on_belt")))
                .add(DyedVoidItems.VOID_BOTTLE_ITEM);
    }
}
