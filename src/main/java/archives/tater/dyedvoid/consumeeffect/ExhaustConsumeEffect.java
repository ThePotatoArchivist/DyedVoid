package archives.tater.dyedvoid.consumeeffect;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.world.World;

public record ExhaustConsumeEffect(float exhaustion) implements ConsumeEffect {
    public static final MapCodec<ExhaustConsumeEffect> CODEC = Codec.FLOAT.fieldOf("exhaustion").xmap(ExhaustConsumeEffect::new, ExhaustConsumeEffect::exhaustion);
    public static final PacketCodec<RegistryByteBuf, ExhaustConsumeEffect> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.FLOAT, ExhaustConsumeEffect::exhaustion,
            ExhaustConsumeEffect::new
    );
    public static final Type<ExhaustConsumeEffect> TYPE = new Type<>(CODEC, PACKET_CODEC);

    @Override
    public Type<? extends ExhaustConsumeEffect> getType() {
        return TYPE;
    }

    @Override
    public boolean onConsume(World world, ItemStack stack, LivingEntity user) {
        if (!(user instanceof PlayerEntity player))
            return false;

        player.addExhaustion(exhaustion);

        return true;
    }
}
