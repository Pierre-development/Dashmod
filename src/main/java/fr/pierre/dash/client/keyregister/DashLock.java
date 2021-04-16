package fr.pierre.dash.client.keyregister;

import fr.pierre.dash.EntitiesComparator;
import fr.pierre.dash.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DashLock {
    public final KeyBinding keyBinding;
    private final KeyBinding keyBindingDash;
    private boolean dashLock = false;


    public DashLock() {
        keyBinding = new KeyBinding("Lock the entity", Keyboard.KEY_N, "key.categories.dash");
        keyBindingDash = new KeyBinding("Dash the entity", Keyboard.KEY_H, "key.categories.dash");
        ClientRegistry.registerKeyBinding(keyBinding);
        ClientRegistry.registerKeyBinding(keyBindingDash);
    }

    @SubscribeEvent
    public void onEvent(InputEvent.KeyInputEvent event) {
        List<EntityLivingBase> entities = Minecraft.getMinecraft().world.getEntities(EntityLivingBase.class, Objects::nonNull);
        Collections.sort(entities, new EntitiesComparator());
        if(keyBinding.isPressed()) {
            //System.out.println(entities.get(1).getPosition().getDistance(Minecraft.getMinecraft().player.getPosition().getX(), Minecraft.getMinecraft().player.getPosition().getY(), Minecraft.getMinecraft().player.getPosition().getZ()));
            //System.out.println(Minecraft.getMinecraft().player.getDistance(entities.get(1)));
            faceEntity(entities.get(1));
        }

        if(keyBindingDash.isPressed()) {

            System.out.println(entities.get(0));
            System.out.println(entities.get(1));

            final int playerLocationX = Minecraft.getMinecraft().player.getPosition().getX();
            final int playerLocationY = Minecraft.getMinecraft().player.getPosition().getY();
            final int playerLocationZ = Minecraft.getMinecraft().player.getPosition().getZ();


            Minecraft.getMinecraft().player.setPosition(entities.get(1).getPosition().getX() - 2, entities.get(1).getPosition().getY(), entities.get(1).getPosition().getZ());
            Minecraft.getMinecraft().player.setPosition(entities.get(1).getPosition().getX() - 2, entities.get(1).getPosition().getY(), entities.get(1).getPosition().getZ() + 2);
            Minecraft.getMinecraft().player.setPosition(entities.get(1).getPosition().getX() , entities.get(1).getPosition().getY(), entities.get(1).getPosition().getZ() + 2);
            Minecraft.getMinecraft().player.setPosition(entities.get(1).getPosition().getX() + 2, entities.get(1).getPosition().getY(), entities.get(1).getPosition().getZ() + 2);
            Minecraft.getMinecraft().player.setPosition(entities.get(1).getPosition().getX() + 2, entities.get(1).getPosition().getY(), entities.get(1).getPosition().getZ());
            Minecraft.getMinecraft().player.setPosition(entities.get(1).getPosition().getX() + 2, entities.get(1).getPosition().getY(), entities.get(1).getPosition().getZ() - 2);
            Minecraft.getMinecraft().player.setPosition(entities.get(1).getPosition().getX(), entities.get(1).getPosition().getY(), entities.get(1).getPosition().getZ() - 2);

            Minecraft.getMinecraft().player.setPosition(playerLocationX, playerLocationY, playerLocationZ);





        }
    }



    public static synchronized void faceEntity(EntityLivingBase entity) {

        final float[] rotations = getRotationsNeeded(entity);

        if (rotations != null) {
            Minecraft.getMinecraft().player.rotationYaw = rotations[0];
            Minecraft.getMinecraft().player.rotationPitch = rotations[1] + 1.0F;
        }
    }

    public static float[] getRotationsNeeded(Entity entity) {
        if (entity == null) {
            return null;
        }

        final double diffX = entity.posX - Minecraft.getMinecraft().player.posX;
        final double diffZ = entity.posZ - Minecraft.getMinecraft().player.posZ;
        double diffY;

        if (entity instanceof EntityLivingBase) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            diffY = entityLivingBase.posY + entityLivingBase.getEyeHeight() - (Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight());
        } else {
            diffY = (entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().maxY) / 2.0D - (Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight());
        }

        final double dist = MathHelper.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
        final float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);
        return new float[] { Minecraft.getMinecraft().player.rotationYaw + MathHelper.wrapDegrees(yaw - Minecraft.getMinecraft().player.rotationYaw), Minecraft.getMinecraft().player.rotationPitch + MathHelper.wrapDegrees(pitch - Minecraft.getMinecraft().player.rotationPitch) };
    }

    public static DashLock getInstance() {
        return new DashLock();
    }

}
