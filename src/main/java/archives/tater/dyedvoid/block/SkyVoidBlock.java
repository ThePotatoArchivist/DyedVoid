package archives.tater.dyedvoid.block;

import archives.tater.dyedvoid.registry.DyedVoidBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import org.jspecify.annotations.Nullable;

public class SkyVoidBlock extends VoidBlock implements EntityBlock {
    public SkyVoidBlock(Properties settings) {
        super(settings);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new SkyVoidBlockEntity(worldPosition, blockState);
    }

    public static class SkyVoidBlockEntity extends BlockEntity {

        public SkyVoidBlockEntity(BlockPos worldPosition, BlockState blockState) {
            super(DyedVoidBlockEntities.SKY_VOID, worldPosition, blockState);
        }

        public boolean shouldRenderFace(Direction direction) {
            if (level == null) return true;
            return Block.shouldRenderFace(this.getBlockState(), this.level.getBlockState(this.getBlockPos().relative(direction)), direction);
        }
    }
}
