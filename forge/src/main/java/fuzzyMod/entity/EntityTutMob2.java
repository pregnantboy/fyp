package fuzzyMod.entity;

import fuzzyMod.fuzzyLogic.FuzzyBrain;
import fuzzyMod.targetTasks.PlayerLastAttackedTarget;
import fuzzyMod.targetTasks.PlayerTarget;
import fuzzyMod.tasks.BuildFarm;
import fuzzyMod.tasks.BuildHouse;
import fuzzyMod.tasks.BuildMine;
import fuzzyMod.tasks.MeleeAttack;
import fuzzyMod.tasks.RunAway;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

/**
 * Entity for FuzzyMob. Follows the rules exported into Slot 2 by FuzzyAIBuilder.
 */
public class EntityTutMob2 extends EntityMobWithInventory {
	FuzzyBrain brain;
	private RunAway runaway;
	int ticker;
	public EntityTutMob2(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
		ticker =0;
		runaway = new RunAway(this, 1.6D);
		this.tasks.addTask(1, new EntityAISwimming(this));

		this.setCurrentItemOrArmor(0, new ItemStack(Items.golden_hoe));
		this.setCurrentItemOrArmor(1, new ItemStack(Items.golden_boots));
		this.setCurrentItemOrArmor(2, new ItemStack(Items.golden_leggings));
		this.setCurrentItemOrArmor(4, new ItemStack(Items.golden_helmet));
		this.setCurrentItemOrArmor(3, new ItemStack(Items.golden_chestplate));
		this.setSize(0.9F, 2.0F);
		this.setCanPickUpLoot(true);
		team = 2;
		brain = new FuzzyBrain (this,2);
	}
	
	/**
	 * Alters the attributes of the entity e.g. attackDamage, maxHealth, moveSpeed.
	 * Called before the creation of the object.
	 */
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
				.setBaseValue(20);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
				.setBaseValue(0.25);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance)
				.setBaseValue(0.2);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
				.setBaseValue(2);

	}
	public boolean isAIEnabled() {
		return true;
	}
	
	public void onUpdate() {
		super.onUpdate();
//		if (ticker < 0) {
//			ticker = 20;
//		} else {
//			ticker --;
//			return;
//		}
//		this.runaway.setSource(Minecraft.getMinecraft().thePlayer);
//		this.runaway.nextStep();
		
		brain.setInputs();
		brain.nextStep();

	}
	


}
