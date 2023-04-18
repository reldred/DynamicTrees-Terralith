package com.djs.dtterralith.growthlogic;

import com.djs.dtterralith.DynamicTreesTerralith;
import com.ferreusveritas.dynamictrees.api.registry.Registry;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;

public class DTTGrowthLogicKits {

    public static final GrowthLogicKit TALL_DECIDUOUS = new TallDeciduousLogic(DynamicTreesTerralith.location("tall_deciduous"));

    public static void register(final Registry<GrowthLogicKit> registry) {
        registry.registerAll(TALL_DECIDUOUS);
    }

}
