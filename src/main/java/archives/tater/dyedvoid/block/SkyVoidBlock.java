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
            if (state.is(this) && state.getValue(POWER).isPowered() && state.getValue(POWER).direction != direction.getOpposite())
                return defaultBlockState().setValue(POWER, Power.fromDirection(direction));
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
            level.setBlockAndUpdate(pos, state.setValue(POWER, Power.SOURCE));
            return;
        } else if (state.getValue(POWER) == Power.SOURCE) {
            level.setBlockAndUpdate(pos, getState(level, pos));
            return;
        }

        var neighborState = level.getBlockState(neighborPos);
        if (!neighborState.is(this)) return;

        if (state.getValue(POWER) == Power.NONE) {
            var offset = neighborPos.subtract(pos);
            var direction = Direction.fromDelta(offset.getX(), offset.getY(), offset.getZ());
            if (direction == null) return;
            if (neighborState.getValue(POWER).isPowered())
                level.setBlockAndUpdate(pos, state.setValue(POWER, Power.fromDirection(direction)));
            return;
        }

        var powerDirection = state.getValue(POWER).direction;
        if (powerDirection == null || !pos.relative(powerDirection).equals(neighborPos)) return;

        if (!neighborState.getValue(POWER).isPowered())
            level.setBlockAndUpdate(pos, state.setValue(POWER, Power.NONE));
    }

    public enum Power implements StringRepresentable {
        NONE("none", null),
        SOURCE("source", null),
        DOWN("down", Direction.DOWN),
        UP("up", Direction.UP),
        NORTH("north", Direction.NORTH),
        SOUTH("south", Direction.SOUTH),
        WEST("west", Direction.WEST),
        EAST("east", Direction.EAST);

        public final @Nullable Direction direction;
        private final String name;

        Power(String name, @Nullable Direction direction) {
            this.direction = direction;
            this.name = name;
        }

        public boolean isPowered() {
            return this != NONE;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        public static Power fromDirection(Direction direction) {
            return switch (direction) {
                case DOWN -> DOWN;
                case UP -> UP;
                case NORTH -> NORTH;
                case SOUTH -> SOUTH;
                case WEST -> WEST;
                case EAST -> EAST;
            };
        }
    }
}
