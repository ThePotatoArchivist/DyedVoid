package archives.tater.dyedvoid;

import archives.tater.dyedvoid.datagen.*;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class DyedVoidDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.BIOME, BiomeTagGenerator::bootstrap);
	}

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModelGenerator::new);
		pack.addProvider(DVRecipeGenerator.Provider::new);
		pack.addProvider(LangGenerator::new);
		pack.addProvider(ItemTagGenerator::new);
		pack.addProvider(BlockTagGenerator::new);
		pack.addProvider(BlockLootTableGenerator::new);

		var whitespacePack = fabricDataGenerator.createBuiltinResourcePack(DyedVoid.id("whitespace"));
		whitespacePack.addProvider(BiomeTagGenerator::new);
	}

}
