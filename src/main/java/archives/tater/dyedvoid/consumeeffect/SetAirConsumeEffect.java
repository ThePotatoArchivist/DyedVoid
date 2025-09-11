package archives.tater.dyedvoid.consumeeffect;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.world.World;

public record SetAirConsumeEffect(int air) implements ConsumeEffect {
    public static final MapCodec<SetAirConsumeEffect> CODEC = Codec.INT.fieldOf("air").xmap(SetAirConsumeEffect::new, SetAirConsumeEffect::air);
    public static final PacketCodec<RegistryByteBuf, SetAirConsumeEffect> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, SetAirConsumeEffect::air,
            SetAirConsumeEffect::new
    );
    public static final Type<SetAirConsumeEffect> TYPE = new Type<>(CODEC, PACKET_CODEC);

    @Override
    public Type<? extends SetAirConsumeEffect> getType() {
        return TYPE;
    }

    @Override
    public boolean onConsume(World world, ItemStack stack, LivingEntity user) {
        user.setAir(air);
        return true;
    }
}
