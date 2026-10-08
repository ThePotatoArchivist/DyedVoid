package archives.tater.dyedvoid.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.renderpearl.api.GpuFormat;
import com.mojang.renderpearl.api.device.GpuDevice;
import com.mojang.renderpearl.api.textures.FilterMode;
import com.mojang.renderpearl.api.textures.GpuTexture;
import net.minecraft.client.renderer.texture.AbstractTexture;

public class AccessibleTexture extends AbstractTexture {
    public AccessibleTexture(String label, int width, int height) {
        GpuDevice device = RenderSystem.getDevice();
        this.texture = device.createTexture(label, 5, GpuFormat.RGBA8_UNORM, width, height, 1, 1);
        this.sampler = RenderSystem.getSamplerCache().getRepeat(FilterMode.NEAREST);
        this.textureView = device.createTextureView(this.texture);
    }

    @Override
    public GpuTexture getTexture() {
        return super.getTexture();
    }
}
