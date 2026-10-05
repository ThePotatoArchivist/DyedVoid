package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.registry.DyedVoidBlocks;
import archives.tater.dyedvoid.registry.DyedVoidItems;
import archives.tater.dyedvoid.registry.DyedVoidSounds;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import net.minecraft.core.HolderLookup.Provider;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.util.Util.makeDescriptionId;


public class LangGenerator extends FabricLanguageProvider {

    public LangGenerator(FabricDataOutput dataOutput, CompletableFuture<Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(Provider registriesLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(DyedVoidItems.VOID_BOTTLE_ITEM, "Bottle of Void");
        translationBuilder.add(DyedVoidBlocks.BLACK_VOID, "Void Block");
        translationBuilder.add(DyedVoidBlocks.WHITE_VOID, "Luminous Void Block");
        translationBuilder.add(DyedVoidBlocks.LIGHT_GRAY_VOID, "Light Gray Void Block");
        translationBuilder.add(DyedVoidBlocks.GRAY_VOID, "Gray Void Block");
        translationBuilder.add(DyedVoidBlocks.BROWN_VOID, "Brown Void Block");
        translationBuilder.add(DyedVoidBlocks.RED_VOID, "Red Void Block");
        translationBuilder.add(DyedVoidBlocks.ORANGE_VOID, "Orange Void Block");
        translationBuilder.add(DyedVoidBlocks.YELLOW_VOID, "Yellow Void Block");
        translationBuilder.add(DyedVoidBlocks.LIME_VOID, "Lime Void Block");
        translationBuilder.add(DyedVoidBlocks.GREEN_VOID, "Green Void Block");
        translationBuilder.add(DyedVoidBlocks.CYAN_VOID, "Cyan Void Block");
        translationBuilder.add(DyedVoidBlocks.LIGHT_BLUE_VOID, "Light Blue Void Block");
        translationBuilder.add(DyedVoidBlocks.BLUE_VOID, "Blue Void Block");
        translationBuilder.add(DyedVoidBlocks.PURPLE_VOID, "Purple Void Block");
        translationBuilder.add(DyedVoidBlocks.MAGENTA_VOID, "Magenta Void Block");
        translationBuilder.add(DyedVoidBlocks.PINK_VOID, "Pink Void Block");
        translationBuilder.add(DyedVoidBlocks.END_VOID, "End Void Block");
        translationBuilder.add(DyedVoidBlocks.SKY_VOID, "Sky Void Block");
        translationBuilder.add(DyedVoidBlocks.CLOUD_VOID, "Cloud Void Block");
        translationBuilder.add(DyedVoidItems.DUMMY_SKY, "Sky");
        translationBuilder.add(DyedVoidItems.DUMMY_CLOUD, "Cloud");
        translationBuilder.add("itemGroup.dyedvoid.group", "The Dyed Void");
        translationBuilder.add(makeDescriptionId("subtitles", DyedVoidSounds.FILL_VOID_BOTTLE.getLocation()), "Bottle truly empties");
    }
}
