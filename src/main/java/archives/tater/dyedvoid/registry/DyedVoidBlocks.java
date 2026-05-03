package archives.tater.dyedvoid.registry;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.block.ColoredVoidBlock;
import archives.tater.dyedvoid.block.EndVoidBlock;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColorCollection;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class DyedVoidBlocks {

    private static Block register(ResourceKey<Block> key, Function<BlockBehaviour.Properties, Block> block, BlockBehaviour.Properties properties) {
        return Registry.register(BuiltInRegistries.BLOCK, key, block.apply(properties.setId(key)));
    }

    private static Block register(BlockItemId id, Function<BlockBehaviour.Properties, Block> block, BlockBehaviour.Properties properties) {
        return register(id.block(), block, properties);
    }

    private static Block register(ResourceKey<Block> key, BlockBehaviour.Properties properties) {
        return register(key, Block::new, properties);
    }

    private static BlockBehaviour.Properties voidBlock() {
        return BlockBehaviour.Properties.of()
                .strength(0)
                .destroyTime(3)
                .sound(DyedVoidSounds.VOID_BLOCK_SOUND_GROUP)
                .noTerrainParticles();
    }

    public static final ColorCollection<Block> VOID = ColorCollection.registerBlocks(
            DyedVoidBlockItemIds.VOID,
            DyedVoidBlocks::register,
            ColoredVoidBlock::new,
            _ -> voidBlock()
    );

    public static final Block END_VOID = register(DyedVoidBlockItemIds.END_VOID, EndVoidBlock::new, voidBlock());
    public static final BlockEntityType<EndVoidBlock.EndVoidBlockEntity> END_VOID_BLOCK_ENTITY = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            DyedVoid.id("end_void"),
            FabricBlockEntityTypeBuilder.create(EndVoidBlock.EndVoidBlockEntity::new, END_VOID).build()
    );

    public static final List<Block> ALL_VOID_BLOCKS = Stream.concat(
            VOID.asList().stream(),
            Stream.of(END_VOID)
    ).toList();

    public static void init() {
        BuiltInRegistries.BLOCK.addAlias(DyedVoidBlockItemIds.LEGACY_BLACK_VOID, DyedVoidBlockItemIds.VOID.black().block().identifier());
    }
}
