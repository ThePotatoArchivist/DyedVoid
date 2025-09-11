package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoidItems;
import archives.tater.dyedvoid.DyedVoidSounds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.util.Util.createTranslationKey;

public class LangGenerator extends FabricLanguageProvider {

    public LangGenerator(FabricDataOutput dataOutput, CompletableFuture<WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(WrapperLookup registriesLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(DyedVoidItems.VOID_BOTTLE_ITEM, "Bottle of Void");
        translationBuilder.add(DyedVoidItems.BLACK_VOID, "Void Block");
        translationBuilder.add(DyedVoidItems.WHITE_VOID, "Luminous Void Block");
        translationBuilder.add(DyedVoidItems.LIGHT_GRAY_VOID, "Light Gray Void Block");
        translationBuilder.add(DyedVoidItems.GRAY_VOID, "Gray Void Block");
        translationBuilder.add(DyedVoidItems.BROWN_VOID, "Brown Void Block");
        translationBuilder.add(DyedVoidItems.RED_VOID, "Red Void Block");
        translationBuilder.add(DyedVoidItems.ORANGE_VOID, "Orange Void Block");
        translationBuilder.add(DyedVoidItems.YELLOW_VOID, "Yellow Void Block");
        translationBuilder.add(DyedVoidItems.LIME_VOID, "Lime Void Block");
        translationBuilder.add(DyedVoidItems.GREEN_VOID, "Green Void Block");
        translationBuilder.add(DyedVoidItems.CYAN_VOID, "Cyan Void Block");
        translationBuilder.add(DyedVoidItems.LIGHT_BLUE_VOID, "Light Blue Void Block");
        translationBuilder.add(DyedVoidItems.BLUE_VOID, "Blue Void Block");
        translationBuilder.add(DyedVoidItems.PURPLE_VOID, "Purple Void Block");
        translationBuilder.add(DyedVoidItems.MAGENTA_VOID, "Magenta Void Block");
        translationBuilder.add(DyedVoidItems.PINK_VOID, "Pink Void Block");
        translationBuilder.add(DyedVoidItems.END_VOID, "End Void Block");
        translationBuilder.add("itemGroup.dyedvoid.group", "The Dyed Void");
        translationBuilder.add(createTranslationKey("subtitles", DyedVoidSounds.FILL_VOID_BOTTLE.getId()), "Bottle truly empties");
    }
}
