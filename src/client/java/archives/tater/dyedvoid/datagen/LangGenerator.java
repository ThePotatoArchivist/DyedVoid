package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.block.ColoredVoidBlock;
import archives.tater.dyedvoid.registry.DyedVoidBlocks;
import archives.tater.dyedvoid.registry.DyedVoidItems;
import archives.tater.dyedvoid.registry.DyedVoidSounds;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.item.DyeColor;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.util.Util.makeDescriptionId;

public class LangGenerator extends FabricLanguageProvider {

    public LangGenerator(FabricPackOutput dataOutput, CompletableFuture<Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    private static String capitalize(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    private static String getName(DyeColor color) {
        return switch (color) {
            case BLACK -> "Void Block";
            case WHITE -> "Luminous Void Block";
            default -> capitalize(color.getName()) + " Void Block";
        };
    }

    @Override
    public void generateTranslations(Provider registriesLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(DyedVoidItems.VOID_BOTTLE_ITEM, "Bottle of Void");
        DyedVoidBlocks.VOID.forEach(block ->
                translationBuilder.add(block, getName(((ColoredVoidBlock) block).color))
        );

        translationBuilder.add(DyedVoidBlocks.END_VOID, "End Void Block");
        translationBuilder.add("itemGroup.dyedvoid.group", "The Dyed Void");
        translationBuilder.add(makeDescriptionId("subtitles", DyedVoidSounds.FILL_VOID_BOTTLE.location()), "Bottle truly empties");
    }
}
