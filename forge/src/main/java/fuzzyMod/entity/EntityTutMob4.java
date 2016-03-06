package fuzzyMod.entity;

import fuzzyMod.tasks.HarvestCrops;
import fuzzyMod.tasks.MineOres;
import fuzzyMod.tasks.StoreLoot;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityTutMob4 extends EntityMobWithInventory {
	
	private int lastArrowCount;

	public EntityTutMob4(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub


		this.tasks.addTask(1, new EntityAISwimming(this));
		

		team = 2;
		this.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
		this.setCurrentItemOrArmor(1, new ItemStack(Items.leather_boots));
		this.setCurrentItemOrArmor(2, new ItemStack(Items.leather_leggings));
		this.setCurrentItemOrArmor(3, new ItemStack(Items.leather_chestplate));
		this.setSize(0.9F, 2.0F);
		this.setCanPickUpLoot(true);
		
		this.arrows = 20;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.2);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2);

	}

	public boolean isAIEnabled() {
		return true;
	}

	public void onUpdate()
	{
		super.onUpdate();
	}
}
