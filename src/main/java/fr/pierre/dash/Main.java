package fr.pierre.dash;


import fr.pierre.dash.proxies.Common;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static fr.pierre.dash.utils.Constants.*;

@Mod(modid = MODID, version = VERSION, name = NAME)
public class Main {

    @Mod.Instance(MODID)
    public static Main instance;

    @SidedProxy(clientSide = CLIENTPROXY, serverSide = SERVERPROXY)
    public static Common proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent fmlPreInitializationEvent) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent fmlInitializationEvent) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent fmlPostInitializationEvent) {
        proxy.postInit();
    }

}
