package com.stussey.buildermod;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BuilderMod.MOD_ID)
public class BuilderMod
{
    public static final String MOD_ID = "buildermod";
    public static final Logger LOGGER = LogManager.getLogger();

    public BuilderMod() {
        //noinspection Convert2MethodRef
        DistExecutor.runForDist(
                () -> () -> new SideProxy.Client(),
                () -> () ->new SideProxy.Server()
        );
    }

    public static String getVersion(){ return  getVersion(false);}

    public static String getVersion(boolean correctInDev){
        Optional<? extends ModContainer> o = ModList.get().getModContainerById(MOD_ID);
        if (o.isPresent()){
            return o.get().getModInfo().getVersion().toString();
        }
        return "NONE";
    }

    public static boolean isDevBuild(){
        String version = getVersion();
        return "NONE".equals(version);
    }

    public static ResourceLocation getId(String path){
        return new ResourceLocation(MOD_ID, path);
    }



    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
