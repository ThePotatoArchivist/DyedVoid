package archives.tater.dyedvoid.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.fog.FogRenderer;

@Mixin(GameRenderer.class)
public interface GameRendererAccessor {
    @Accessor
    FogRenderer getFogRenderer();
}
