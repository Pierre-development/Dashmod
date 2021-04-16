package fr.pierre.dash.proxies;

import fr.pierre.dash.EventsHandler;
import net.minecraftforge.common.MinecraftForge;

public class Common {

    public void preInit() {

    }

    public void init() {
        MinecraftForge.EVENT_BUS.register(new EventsHandler());

    }

    public void postInit() {

    }
}
