package fr.pierre.dash.proxies;

import fr.pierre.dash.client.gui.HoverLay;
import fr.pierre.dash.client.keyregister.DashLock;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraftforge.common.MinecraftForge;

public class Client extends Common {


    public Client() {
        MinecraftForge.EVENT_BUS.register(new DashLock());

    }




    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void init() {
        super.init();
        MinecraftForge.EVENT_BUS.register(new HoverLay());
    }

    @Override
    public void postInit() {
        super.postInit();
    }


}
