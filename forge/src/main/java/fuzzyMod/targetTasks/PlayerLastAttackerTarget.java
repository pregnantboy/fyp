package fuzzyMod.targetTasks;

import java.util.Iterator;
import java.util.List;

import fuzzyMod.entity.EntityMobWithInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;

/**
 * Target task that gets the last attacker that attacked the Player.
 */
public class PlayerLastAttackerTarget {
	private static EntityLiving theTarget;

	/**
	 * Updates the player's attacker. This is done by getting the entities around the Player and sorting them based on their distance to the Player.
	 * Next it checks if the attack target is the player itself.
	 */
	public static void updatePlayerAttacker() {
	
		Minecraft mc = Minecraft.getMinecraft();
//		System.out.println("tring to get entities");
		List entities;
		if (mc.thePlayer == null) {
            return;
        } 
		if (mc.thePlayer.getEntityBoundingBox() != null) {
			entities = mc.thePlayer.getEntityWorld().getEntitiesWithinAABBExcludingEntity(mc.thePlayer, mc.thePlayer.getEntityBoundingBox().expand(16.0D, 4.0D, 16.0D));
			System.out.println("found entities");
		} else {
			return;
		}
		Entity entity;
		EntityLiving entityLiving;
		Iterator<Entity> iterator = entities.iterator();
		while(iterator.hasNext()){
			entity = (Entity)iterator.next();
			if (entity instanceof EntityLiving) {
				entityLiving = (EntityLiving)entity;
				System.out.println("attacktarget: " + entityLiving.getAttackTarget());
				if (entityLiving.getAttackTarget()!= null){
					if (entityLiving.getAttackTarget() == mc.thePlayer) {
						System.out.println("updating");
						theTarget = entityLiving;
					}
				}
			}
		}
	}
	
	/**
	 * Returns the stored player's attacker.
	 */
	public static EntityLiving getPlayerAttacker() {
		if (theTarget != null) {
			return theTarget;
		}
		return null;	
	}
}
