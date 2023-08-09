package com.djs.dtterralith.registry;

import com.djs.dtterralith.DynamicTreesTerralith;
import com.djs.dtterralith.cellkits.DTTerralithCellKits;
import com.djs.dtterralith.genfeatures.DTTerralithGenFeatures;
import com.djs.dtterralith.growthlogic.DTTGrowthLogicKits;
import com.djs.dtterralith.trees.PoplarSpecies;
import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.registry.RegistryEvent;
import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeatures;
import com.ferreusveritas.dynamictrees.tree.species.Species;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DTTRegistries {

    public static void setup() {

    }

    @SubscribeEvent
    public static void registerSpeciesTypes (final TypeRegistryEvent<Species> event) {
        event.registerType(DynamicTreesTerralith.location("poplar"), PoplarSpecies.TYPE);
    }

    @SubscribeEvent
    public static void onCellKitRegistry(final RegistryEvent<CellKit> event) {
        DTTerralithCellKits.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onGrowthLogicKitRegistry(final RegistryEvent<GrowthLogicKit> event) {
        DTTGrowthLogicKits.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void onGenFeatureRegistry(final RegistryEvent<GenFeature> event) {
        DTTerralithGenFeatures.register(event.getRegistry());
    }

}

