package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.registry.DyedVoidItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.SulfurCubeArchetype;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static net.minecraft.world.entity.SulfurCubeArchetype.AttributeEntry.add;
import static net.minecraft.world.entity.SulfurCubeArchetype.AttributeEntry.multiply;

public class SulfurCubeArchetypeGenerator extends FabricDynamicRegistryProvider {
    public SulfurCubeArchetypeGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    private static Holder<SulfurCubeArchetype> register(Entries entries, ResourceKey<SulfurCubeArchetype> key, Function<ResourceKey<SulfurCubeArchetype>, SulfurCubeArchetype> factory) {
        return entries.add(key, factory.apply(key));
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        register(entries, DyedVoid.SULFUR_CUBE_ARCHETYPE_NO_GRAVITY, key -> new SulfurCubeArchetype(
                registries.getOrThrow(DyedVoidItemTags.SULFUR_CUBE_ARCHETYPE_NO_GRAVITY),
                List.of(
                        multiply(Attributes.GRAVITY, 0, key),
                        add(Attributes.KNOCKBACK_RESISTANCE, 0.25, key),
                        add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 0.5, key),
                        add(Attributes.BOUNCINESS, 1, key),
                        multiply(Attributes.FRICTION_MODIFIER, 0.05, key),
                        multiply(Attributes.AIR_DRAG_MODIFIER, 0.05, key)
                ),
                false,
                Optional.empty()
        ));
    }

    @Override
    public String getName() {
        return "Sulfur Cube Archetypes";
    }
}
