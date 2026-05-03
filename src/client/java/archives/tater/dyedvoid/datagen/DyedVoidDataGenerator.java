package archives.tater.dyedvoid.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DyedVoidDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModelGenerator::new);
		pack.addProvider(DVRecipeGenerator.Provider::new);
		pack.addProvider(LangGenerator::new);
		var blockTagGenerator = pack.addProvider(BlockTagGenerator::new);
		pack.addProvider((output, registriesFuture) -> new ItemTagGenerator(output, registriesFuture, blockTagGenerator));
		pack.addProvider(BlockLootTableGenerator::new);
		pack.addProvider(SulfurCubeArchetypeGenerator::new);
	}

}
