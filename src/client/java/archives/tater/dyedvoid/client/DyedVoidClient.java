package archives.tater.dyedvoid.client;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.client.mixin.GameRendererAccessor;
import archives.tater.dyedvoid.client.render.EndVoidRenderer;
import archives.tater.dyedvoid.client.render.SkyVoidRenderer;
import archives.tater.dyedvoid.client.render.SkyVoidSpecialRenderer;
import archives.tater.dyedvoid.client.render.VoidBlockSpecialRenderer;
import archives.tater.dyedvoid.registry.DyedVoidBlockEntities;
import archives.tater.dyedvoid.registry.DyedVoidBlockItemTags;
import archives.tater.dyedvoid.registry.DyedVoidBlocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltInBlockModelsCallback;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.renderpearl.api.GpuFormat;
import com.mojang.renderpearl.api.pipeline.ColorTargetState;
import com.mojang.renderpearl.api.pipeline.DepthStencilState;
import com.mojang.renderpearl.api.pipeline.PrimitiveTopology;
import com.mojang.renderpearl.api.pipeline.RenderPipeline;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BindGroupLayouts;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.client.renderer.block.BuiltInBlockModels;
import net.minecraft.client.renderer.block.model.SpecialBlockModelWrapper;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.special.EndCubeSpecialRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DyedVoidClient implements ClientModInitializer {
    public static SpecialModelRenderer.Unbaked<Void> getModel(BlockState state) {
        return
                state.is(DyedVoidBlocks.END_VOID) ? new EndCubeSpecialRenderer.Unbaked(EndCubeSpecialRenderer.Type.PORTAL) :
                state.is(DyedVoidBlocks.SKY_VOID) ? new SkyVoidSpecialRenderer.Unbaked() :
                new VoidBlockSpecialRenderer.Unbaked(state);
    }

    public static boolean isHiddenOutline(BlockState state) {
        return state.is(DyedVoidBlockItemTags.HIDDEN_OUTLINE.block());
    }

    public static boolean seesHiddenOutlines(LivingEntity entity) {
        return entity.getMainHandItem().is(DyedVoidBlockItemTags.HIDDEN_OUTLINE.item()) || entity.getOffhandItem().is(DyedVoidBlockItemTags.HIDDEN_OUTLINE.item());
    }

    public static final RenderTarget SKY_BUFFER = new TextureTarget(
            DyedVoid.MOD_ID + "_sky",
            Minecraft.getInstance().getWindow().getWidth(),
            Minecraft.getInstance().getWindow().getHeight(),
            null,
            GpuFormat.D32_FLOAT
    );

    public static final AccessibleTexture SKY_TEXTURE = new AccessibleTexture(DyedVoid.MOD_ID + "_sky", Minecraft.getInstance().getWindow().getWidth(), Minecraft.getInstance().getWindow().getHeight());

    private static @Nullable SkyRenderer skyRenderer;

    public static final RenderPipeline SKY_RENDER_PIPELINE = RenderPipelines.register(RenderPipeline.builder(RenderPipelines.GLOBALS_SNIPPET)
            .withBindGroupLayout(BindGroupLayouts.PROJECTION)
            .withBindGroupLayout(BindGroupLayouts.DYNAMIC_TRANSFORMS)
            .withBindGroupLayout(BindGroupLayouts.FOG)
            .withVertexShader(DyedVoid.id("core/rendertype_sky"))
            .withFragmentShader(DyedVoid.id("core/rendertype_sky"))
            .withBindGroupLayout(BindGroupLayouts.SAMPLER0)
            .withVertexBinding(0, DefaultVertexFormat.POSITION)
            .withPrimitiveTopology(PrimitiveTopology.QUADS)
            .withColorTargetState(ColorTargetState.DEFAULT)
            .withDepthStencilState(DepthStencilState.DEFAULT)
            .withLocation(DyedVoid.id("pipeline/sky"))
           .build()
    );

    public static final Identifier SKY_TEXTURE_LOCATION = DyedVoid.id("buffer/sky.png");

    public static final RenderType SKY_RENDER_TYPE = RenderType.create(DyedVoid.MOD_ID + "_sky", RenderSetup.builder(SKY_RENDER_PIPELINE)
            .withTexture("Sampler0", SKY_TEXTURE_LOCATION)
            .setOutline(RenderSetup.OutlineProperty.AFFECTS_OUTLINE)
            .createRenderSetup()
    );

    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(DyedVoidBlockEntities.END_VOID, _ -> new EndVoidRenderer());
        BlockEntityRenderers.register(DyedVoidBlockEntities.SKY_VOID, _ -> new SkyVoidRenderer());

        SpecialModelRenderers.ID_MAPPER.put(DyedVoid.id("void_block"), VoidBlockSpecialRenderer.Unbaked.CODEC);
        SpecialModelRenderers.ID_MAPPER.put(DyedVoid.id("sky_void"), SkyVoidSpecialRenderer.Unbaked.CODEC);

        BuiltInBlockModelsCallback.EVENT.register(builder -> {
            for (var block : DyedVoidBlocks.ALL_VOID_BLOCKS)
                builder.put((BuiltInBlockModels.ModelFactory) (_, state) ->
                        new SpecialBlockModelWrapper.Unbaked<>(getModel(state), Optional.empty()), block);
        });

        Minecraft.getInstance().getTextureManager().register(SKY_TEXTURE_LOCATION, SKY_TEXTURE);

        LevelRenderEvents.START_MAIN.register(context -> {

            if (context.levelState().shouldResetSkyRenderer || skyRenderer == null) {
                if (skyRenderer != null)
                    skyRenderer.close();

                skyRenderer = new SkyRenderer(
                        Minecraft.getInstance().getTextureManager(),
                        Minecraft.getInstance().getAtlasManager(),
                        SKY_BUFFER
                );
            }

            skyRenderer.render(
                    ((GameRendererAccessor) context.gameRenderer()).getFogRenderer().getBuffer(FogRenderer.FogMode.NONE),
                    context.levelState().skyRenderState
            );

            SKY_BUFFER.blitAndBlendToTexture(SKY_TEXTURE.getTextureView(), SKY_TEXTURE.getTextureView());
        });
    }
}
