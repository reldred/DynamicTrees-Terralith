package com.djs.dtterralith;

import com.djs.dtterralith.registry.DTTRegistries;
import com.djs.dtterralith.registry.RegisterTerralithBiomes;
import com.djs.dtterralith.registry.SkylandsGroundFinder;
import com.ferreusveritas.dynamictrees.api.GatherDataHelper;
import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import com.ferreusveritas.dynamictrees.block.leaves.LeavesProperties;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DynamicTreesTerralith.MOD_ID)
public class DynamicTreesTerralith {

    public static final String MOD_ID = "dtterralith";

    public DynamicTreesTerralith() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::gatherData);
        MinecraftForge.EVENT_BUS.register(this);

        RegistryHandler.setup(MOD_ID);

        DTTRegistries.setup();
        RegisterTerralithBiomes.register(modEventBus);
    }
    
    private void gatherData(final GatherDataEvent event) {
        GatherDataHelper.gatherTagData(MOD_ID, event);
        GatherDataHelper.gatherLootData(MOD_ID, event);
        GatherDataHelper.gatherBlockStateAndModelData(MOD_ID, event,
                Family.REGISTRY,
                LeavesProperties.REGISTRY
        );
    }

}
