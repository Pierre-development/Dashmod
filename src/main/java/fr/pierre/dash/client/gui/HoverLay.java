package fr.pierre.dash.client.gui;


import fr.pierre.dash.client.keyregister.DashLock;
import fr.pierre.dash.utils.Constants;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

import java.awt.*;

@Mod.EventBusSubscriber(modid = Constants.MODID, value = {Side.CLIENT})
public class HoverLay {

    public void renderGameOverlay(RenderGameOverlayEvent.Post event) {
        if(DashLock.getInstance().keyBinding.isPressed()) {
            drawCrosshair();
        }
    }

    private void drawCrosshair() {
        Minecraft.getMinecraft().fontRenderer.drawString("entity locked", 0, 10, Color.WHITE.getRGB());
    }

}
