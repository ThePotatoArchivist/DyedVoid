package archives.tater.dyedvoid.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SkyVoidBlock extends VoidBlock {
    public static final EnumProperty<Power> POWER = EnumProperty.create("power", Power.class);

    public SkyVoidBlock(Properties settings) {
        super(settings);
        registerDefaultState(getStateDefinition().any().setValue(POWER, Power.NONE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWER);
    }

    private @NotNull BlockState getState(Level level, BlockPos pos) {
        if (level.hasNeighborSignal(pos))
            return defaultBlockState().setValue(POWER, Power.SOURCE);
        for (var direction : Direction.values()) {
            var state = level.getBlockState(pos.relative(direction));
            if (state.is(this) && state.getValue(POWER).isPowered())
                return defaultBlockState().setValue(POWER, Power.CHILD);
        }
        return defaultBlockState();
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return getState(context.getLevel(), context.getClickedPos());
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos neighborPos, boolean movedByPiston) {
        if (level.isClientSide) return;

        if (level.hasNeighborSignal(pos)) {
            if (state.getValue(POWER) != Power.SOURCE) {
                level.setBlockAndUpdate(pos, state.setValue(POWER, Power.SOURCE));
                return;
            }
        } else if (state.getValue(POWER) == Power.SOURCE) {
            level.setBlockAndUpdate(pos, state.setValue(POWER, Power.NONE));
            return;
        }

        var neighborState = level.getBlockState(neighborPos);
        if (!neighborState.is(this)) return;

        if (state.getValue(POWER) == Power.NONE) {
            if (neighborState.getValue(POWER).isPowered())
                level.setBlockAndUpdate(pos, state.setValue(POWER, Power.CHILD));
            return;
        }

        if (!neighborState.getValue(POWER).isPowered())
            if (state.getValue(POWER) == Power.CHILD)
                level.setBlockAndUpdate(pos, state.setValue(POWER, Power.NONE));
            else if (state.getValue(POWER) == Power.SOURCE)
                level.setBlockAndUpdate(pos, state.setValue(POWER, Power.CHILD));
    }

    public enum Power implements StringRepresentable {
        NONE("none"),
        SOURCE("source"),
        CHILD("child");

        private final String name;

        Power(String name) {
            this.name = name;
        }

        public boolean isPowered() {
            return this != NONE;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
