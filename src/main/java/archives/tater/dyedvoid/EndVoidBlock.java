package archives.tater.dyedvoid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TheEndPortalBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;


public class EndVoidBlock extends VoidBlock implements EntityBlock {
    public EndVoidBlock(Properties settings) {
        super(settings);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EndVoidBlockEntity(pos, state);
    }

    public static class EndVoidBlockEntity extends TheEndPortalBlockEntity {
        public EndVoidBlockEntity(BlockPos pos, BlockState state) {
            super(DyedVoidBlocks.END_VOID_BLOCK_ENTITY, pos, state);
        }

        @Override
        public boolean shouldRenderFace(Direction direction) {
            if (level == null) return true;
            return Block.shouldRenderFace(this.getBlockState(), this.level.getBlockState(this.getBlockPos().relative(direction)), direction);
        }
    }

    public static void tryCraft(Entity entity) {
        if (!(entity instanceof ItemEntity itemEntity)) return;
        var stack = itemEntity.getItem();
        if (!stack.is(DyedVoidItems.BLACK_VOID)) return;
        itemEntity.setItem(new ItemStack(DyedVoidItems.END_VOID, stack.getCount()));
    }
}
