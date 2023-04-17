package com.djs.dtterralith.growthlogic;

import com.ferreusveritas.dynamictrees.DynamicTrees;
import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.growthlogic.*;

public class DTTGrowthLogicKits {

    public static final GrowthLogicKit TALL_DECIDUOUS = new TallDeciduousLogic(DynamicTrees.location("tall_deciduous"));

    public static void register(final Registry<GrowthLogicKit> registry) {
        registry.registerAll(TALL_DECIDUOUS);
    }

}
