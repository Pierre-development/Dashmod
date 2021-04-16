package fr.pierre.dash;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

import java.util.Comparator;

public class EntitiesComparator implements Comparator<Entity> {


    @Override
    public int compare(Entity o1, Entity o2) {
        if(o1.getPosition().getDistance(Minecraft.getMinecraft().player.getPosition().getX(), Minecraft.getMinecraft().player.getPosition().getZ(), Minecraft.getMinecraft().player.getPosition().getZ()) < o2.getPosition().getDistance(Minecraft.getMinecraft().player.getPosition().getX(), Minecraft.getMinecraft().player.getPosition().getZ(), Minecraft.getMinecraft().player.getPosition().getZ())) {
            return -1;
        } else {
            return 1;
        }
    }
}
