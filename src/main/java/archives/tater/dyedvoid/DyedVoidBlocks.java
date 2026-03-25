package archives.tater.dyedvoid;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class DyedVoidBlocks {

    private static Block register(Identifier identifier, Function<BlockBehaviour.Properties, Block> block, BlockBehaviour.Properties settings) {
        var key = ResourceKey.create(Registries.BLOCK, identifier);
        return Registry.register(BuiltInRegistries.BLOCK, key, block.apply(settings.setId(key)));
    }

    private static Block register(String path, Function<BlockBehaviour.Properties, Block> block, BlockBehaviour.Properties settings) {
        return register(DyedVoid.id(path), block, settings);
    }

    private static Block register(String path, BlockBehaviour.Properties settings) {
        return register(path, Block::new, settings);
    }

    private static Block registerVoidBlock(@Nullable String colorName) {
        var settings = BlockBehaviour.Properties.of()
                .strength(0)
                .destroyTime(3)
                .sound(DyedVoidSounds.VOID_BLOCK_SOUND_GROUP)
                .noTerrainParticles();

        return register(colorName == null ? "void" : colorName + "_void", VoidBlock::new, settings);
    }

    public static final Block WHITE_VOID = registerVoidBlock("white");
    public static final Block LIGHT_GRAY_VOID = registerVoidBlock("light_gray");
    public static final Block GRAY_VOID = registerVoidBlock("gray");
    public static final Block BLACK_VOID = registerVoidBlock(null);
    public static final Block BROWN_VOID = registerVoidBlock("brown");
    public static final Block RED_VOID = registerVoidBlock("red");
    public static final Block ORANGE_VOID = registerVoidBlock("orange");
    public static final Block YELLOW_VOID = registerVoidBlock("yellow");
    public static final Block LIME_VOID = registerVoidBlock("lime");
    public static final Block GREEN_VOID = registerVoidBlock("green");
    public static final Block CYAN_VOID = registerVoidBlock("cyan");
    public static final Block LIGHT_BLUE_VOID = registerVoidBlock("light_blue");
    public static final Block BLUE_VOID = registerVoidBlock("blue");
    public static final Block PURPLE_VOID = registerVoidBlock("purple");
    public static final Block MAGENTA_VOID = registerVoidBlock("magenta");
    public static final Block PINK_VOID = registerVoidBlock("pink");

    public static final Block SHADOW_VOID = registerVoidBlock("shadow");
    public static final Block INVERTED_SHADOW_VOID = registerVoidBlock("inverted_shadow");

    public static final Block END_VOID = register("end_void", EndVoidBlock::new, BlockBehaviour.Properties.of()
            .strength(0)
            .destroyTime(3)
            .sound(DyedVoidSounds.VOID_BLOCK_SOUND_GROUP)
            .noTerrainParticles()
    );
    public static final BlockEntityType<EndVoidBlock.EndVoidBlockEntity> END_VOID_BLOCK_ENTITY = Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            DyedVoid.id("end_void"),
            FabricBlockEntityTypeBuilder.create(EndVoidBlock.EndVoidBlockEntity::new, END_VOID).build()
    );

    public static final Block[] VOID_BLOCKS = {
            BLACK_VOID,
            WHITE_VOID,
            LIGHT_GRAY_VOID,
            GRAY_VOID,
            BROWN_VOID,
            RED_VOID,
            ORANGE_VOID,
            YELLOW_VOID,
            LIME_VOID,
            GREEN_VOID,
            CYAN_VOID,
            LIGHT_BLUE_VOID,
            BLUE_VOID,
            PURPLE_VOID,
            MAGENTA_VOID,
            PINK_VOID,
            SHADOW_VOID,
            INVERTED_SHADOW_VOID,
            END_VOID
    };

    public static final TagKey<Block> VOID_BLOCKS_TAG = TagKey.create(Registries.BLOCK, DyedVoid.id("void_blocks"));

    public static void init() {}
}
