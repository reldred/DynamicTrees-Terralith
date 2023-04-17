package com.djs.dtterralith.registry;

import com.djs.dtterralith.growthlogic.DTTGrowthLogicKits;
import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.registry.RegistryEvent;
import com.ferreusveritas.dynamictrees.growthlogic.GrowthLogicKit;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DTTRegistries {

    public static void setup() {
        SkylandsGroundFinder.register();

    }

    @SubscribeEvent
    public static void onCellKitRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<CellKit> event) {

    }

    @SubscribeEvent
    public static void onGrowthLogicKitRegistry(final RegistryEvent<GrowthLogicKit> event) {
        DTTGrowthLogicKits.register(event.getRegistry());
    }

}

