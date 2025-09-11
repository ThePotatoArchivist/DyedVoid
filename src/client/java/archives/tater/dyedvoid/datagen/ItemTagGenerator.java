package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoidItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {

    public ItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(DyedVoidItems.NO_GRAVITY_TAG).add(
                DyedVoidItems.VOID_BOTTLE_ITEM
        ).add(DyedVoidItems.VOID_BLOCKS);

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of("create", "upright_on_belt")))
                .add(DyedVoidItems.VOID_BOTTLE_ITEM);
    }
}
