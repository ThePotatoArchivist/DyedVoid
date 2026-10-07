package archives.tater.dyedvoid.registry;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.block.EndVoidBlock;
import archives.tater.dyedvoid.block.SkyVoidBlock;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class DyedVoidBlockEntities {
    private static <T extends BlockEntity> BlockEntityType<T> register(Identifier id, FabricBlockEntityTypeBuilder.Factory<T> factory, Block... blocks) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.create(factory, blocks).build());
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(String path, FabricBlockEntityTypeBuilder.Factory<T> factory, Block... blocks) {
        return register(DyedVoid.id(path), factory, blocks);
    }

    public static final BlockEntityType<EndVoidBlock.EndVoidBlockEntity> END_VOID = register("end_void", EndVoidBlock.EndVoidBlockEntity::new, DyedVoidBlocks.END_VOID);

    public static final BlockEntityType<SkyVoidBlock.SkyVoidBlockEntity> SKY_VOID = register("sky_void", SkyVoidBlock.SkyVoidBlockEntity::new, DyedVoidBlocks.SKY_VOID);

    public static void init() {

    }
}
