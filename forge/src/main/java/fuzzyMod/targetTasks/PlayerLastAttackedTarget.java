package fuzzyMod.targetTasks;

import fuzzyMod.entity.EntityMobWithInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.item.Item;
import net.minecraft.util.MovingObjectPosition;

/**
 * Target task that keeps track of the player's last attacked target.
 */
public class PlayerLastAttackedTarget 
{
    private static Entity theTarget;
    private static Item lastAttackedItem;
    private static boolean activated;

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public static boolean shouldExecute(EntityMobWithInventory mob)
    {
  
    	if (theTarget == null) {
    		return false;
    	} 
    	if (theTarget.getEntityId() == mob.getEntityId()) {  
    		mob.setAttackTarget(null);
    		return false;
    	}
    	if (mob.getDistanceToEntity(theTarget) > 30.0D) {
    		return false;
    	}
    	return true;
    }
    
    /**
	 * Constantly checks if the player swings its held item. If the held item is not an egg, it will update the target that it has attacked.
	 */
    public static void updateLastTarget () {
    	
    	EntityLivingBase player = Minecraft.getMinecraft().thePlayer;

        if (player == null)
        {
            return;
        }
        else 
        {
        	Minecraft mc = Minecraft.getMinecraft();       	
        	if (player.getHeldItem()!= null) {
        		if (player.getHeldItem().getUnlocalizedName().contains("monsterPlacer")) {
        			return;
        		} else {
        			System.out.println("held item: "+ player.getHeldItem().getUnlocalizedName());
        		}
        		if (player.isSwingInProgress) {
		        	MovingObjectPosition objectMouseOver = mc.objectMouseOver;
		        	if(objectMouseOver != null && objectMouseOver.entityHit != null) {
		        		Entity targetCandidate = mc.objectMouseOver.entityHit;
		        		if (mc.objectMouseOver.typeOfHit ==MovingObjectPosition.MovingObjectType.ENTITY && targetCandidate instanceof EntityLivingBase) {
		        			// if it still doesnt work just return targetCandidate rather then this indirect way
		        			theTarget = (EntityLivingBase) player.getEntityWorld().getEntityByID(targetCandidate.getEntityId());
		        			lastAttackedItem = player.getHeldItem().getItem();
		        		}
		        	}
        		}
        	}
        }
    }
    
    /**
	 * Returns the stored entity for last attacked target.
	 * @param mob The referenced mob.
	 */
    public static Entity getLastTarget(EntityMobWithInventory mob) {
    	 if (theTarget != null ) {
    		 if (theTarget.getEntityId() == mob.getEntityId()) {  
    			 return null;
    		 }
    		 if (mob.getDistanceToEntity(theTarget) > 50.0D) {
    	    	return null;
    	     }
    		 theTarget = mob.worldObj.getEntityByID(theTarget.getEntityId());
    		 return theTarget;
    	 }
    	 return null;
    }
    
  
}