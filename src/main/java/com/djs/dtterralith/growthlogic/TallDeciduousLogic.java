package com.djs.dtterralith.growthlogic;

import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKitConfiguration;
import com.ferreusveritas.dynamictrees.growthlogic.context.DirectionManipulationContext;
import com.ferreusveritas.dynamictrees.growthlogic.context.PositionalSpeciesContext;
import com.ferreusveritas.dynamictrees.systems.GrowSignal;
import com.ferreusveritas.dynamictrees.util.CoordUtils;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class TallDeciduousLogic extends GrowthLogicKit {

    public static final ConfigurationProperty<Integer> TRUNK_UP_BOOST = ConfigurationProperty.integer("trunk_up_boost");

    public TallDeciduousLogic(final ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected GrowthLogicKitConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(TRUNK_UP_BOOST, 5);
    }

    @Override
    protected void registerProperties() {
        this.register(TRUNK_UP_BOOST);
    }

    @Override
    public int[] populateDirectionProbabilityMap(GrowthLogicKitConfiguration configuration, DirectionManipulationContext context) {
        final int[] probMap = super.populateDirectionProbabilityMap(configuration, context);

        //boost the trunk continuing up without branching
        if (context.signal().isInTrunk()) {
            probMap[Direction.UP.ordinal()] += configuration.get(TRUNK_UP_BOOST);
        }

        //Ensure that the branch gets out of the trunk at least two blocks so it won't interfere with new side branches at the same level
        if (justTurnedOutOfTrunk(context.signal())) {
            for (Direction dir : CoordUtils.HORIZONTALS) {
                if (context.signal().dir != dir) {
                    probMap[dir.ordinal()] = 0;
                }
            }
        }
        return probMap;
    }

    @Override
    public float getEnergy(GrowthLogicKitConfiguration configuration, PositionalSpeciesContext context) {
        return super.getEnergy(configuration, context) *
                context.species().biomeSuitability(context.level(), context.pos());
    }

    @Override
    public int getLowestBranchHeight(GrowthLogicKitConfiguration configuration, PositionalSpeciesContext context) {
        return (int) (super.getLowestBranchHeight(configuration, context) *
                context.species().biomeSuitability(context.level(), context.pos()));
    }

    private boolean justTurnedOutOfTrunk (GrowSignal signal){
        return signal.numTurns == 1 && (signal.delta.getX() == 1 || signal.delta.getY() == 1);
    }

}
