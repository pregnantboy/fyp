package pregnantboy.tutorial.entity;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityTutMob extends EntityMob {
	
	private EntityAIBase runAway =  new EntityAIAvoidEntity(this, EntityTutMob2.mobSelector , 5.0F, 2.0, 0.2);
	double xPosition, yPosition, zPosition;
	World world=this.getEntityWorld();
	boolean isBuilding = false;
	
	public EntityTutMob(World worldIn) {
		super(worldIn);
		//this.tasks.addTask(0, new EntityAIWander(this, 0.3D));
		//this.tasks.addTask(1, new EntityAILookIdle(this));
		
		this.setSize(0.9F, 2.0F);
		this.setCanPickUpLoot(true);
		
	}
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
		.setBaseValue(3);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
	}
	public boolean isAIEnabled() {
		return true;
	}
	
	public void onUpdate() {
		super.onUpdate();
		while (!isBuilding){
		 stateBuildFarm();
		}
	}
	 public boolean attackEntityAsMob(Entity p_70652_1_) {
		 spawnExplosionParticle();
		 return super.attackEntityAsMob(p_70652_1_);
		 
	 }
	 @Override
	 public void onItemPickup(Entity p_71001_1_, int p_71001_2_) {
		 System.out.println("picked up shit");
		 System.out.println(p_71001_1_.getEntityId());
		 super.onItemPickup(p_71001_1_, p_71001_2_);
	 }

	 public void stateBuildFarm()
	 {
		 isBuilding = true;
		 Vec3 vec3 = null;
         vec3 = new Vec3(this.posX, this.posY, this.posZ);
         
         this.xPosition = vec3.xCoord;
         this.yPosition = vec3.yCoord;
         this.zPosition = vec3.zCoord;
         
         //this.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, 1); //kalau pakai ni die gerak all the way.    
         System.out.println(xPosition);
		 	//this.getNavigator().getPathToEntityLiving(new EntityTutMod(this.worldObj));
		 
		 	//this.getNavigator().tryMoveToEntityLiving(new EntityTutMod(this.worldObj), 2);
		 	
         //tasks.addTask(3, new EntityAIAvoidEntity(this, EntityTutMod1.class, 8.0F, 0.6D, 0.6D));

 
 		double x = xPosition+1;
 		double y = yPosition;
 		double z = zPosition;
 		
 		for(int p=0;p<7;p++)
 		{

 				for(int i=0;i<12;i++)
 				{
 					if(p!=3&&p!=0&&p!=6&&i!=0&&i!=11)
 					{	
 						BlockPos block = new BlockPos(x,y-1,z);
 						IBlockState iblockstate = Blocks.farmland.getDefaultState();
 						world.setBlockState(block, iblockstate);
 						x= x+1;
 						System.out.println(x);
 					}
 					else if(p==3&&i!=0&&i!=11)
 					{
 						BlockPos block = new BlockPos(x,y-1,z);
 						IBlockState iblockstate = Blocks.water.getDefaultState();
 						world.setBlockState(block, iblockstate);
 						x= x+1;
 						System.out.println(x);	
 					}
 					else if(p==3&&i==0||p==3&&i==11)
 					{
 						BlockPos block = new BlockPos(x,y,z);
 						IBlockState iblockstate = Blocks.acacia_fence_gate.getStateFromMeta(3);
 						world.setBlockState(block, iblockstate);
 						x= x+1;
 						System.out.println(x);		
 					}
 					else
 					{
 						BlockPos block = new BlockPos(x,y,z);
 						IBlockState iblockstate = Blocks.acacia_fence.getDefaultState();
 						world.setBlockState(block, iblockstate);
 						x= x+1;
 						System.out.println(x);		
 					}
 				}
 			x=xPosition+1;
 			z=z+1;
 		}

		
	 }
	 int length= 10, height= 10, breadth= 10;
	 public void stateBuild()
	 {
		 //this.tasks.addTask(1, new EntityAIFront(this, 0.5D)); //Die gerak satu kali abe tk buat ape2.
		 isBuilding = true;
		 Vec3 vec3 = null;
         vec3 = new Vec3(this.posX, this.posY, this.posZ);
         
         this.xPosition = vec3.xCoord;
         this.yPosition = vec3.yCoord;
         this.zPosition = vec3.zCoord;
         
         //this.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, 1); //kalau pakai ni die gerak all the way.    
         System.out.println(xPosition);
		 	//this.getNavigator().getPathToEntityLiving(new EntityTutMod(this.worldObj));
		 
		 	//this.getNavigator().tryMoveToEntityLiving(new EntityTutMod(this.worldObj), 2);
		 	
         //tasks.addTask(3, new EntityAIAvoidEntity(this, EntityTutMod1.class, 8.0F, 0.6D, 0.6D));

 
 		double x = xPosition+1;
 		double y = yPosition;
 		double z = zPosition;
 		
 		for(int p=0;p<breadth;p++)
 		{
 			for(int o=0;o<height;o++)
 			{
 				for(int i=0;i<length;i++)
 				{
 					if(p==((breadth-1)/2)&&i==(length-1)&&o==0)
 					{
 	 					BlockPos door1 = new BlockPos(x,y,z-2);
 	 					BlockPos door2 = new BlockPos(x,y,z-1);
 	 					Block iblockstate = Blocks.birch_door;
 	 					
 	 					//world.setBlockState(block, iblockstate ,10);
 	 					ItemDoor.placeDoor(world, door1, EnumFacing.WEST, iblockstate);
 	 					ItemDoor.placeDoor(world, door2, EnumFacing.WEST, iblockstate);
 	 					//BlockPos block2 = new BlockPos(x,y+1,z);
// 	 					world.setBlockState(block2, iblockstate);
 					}
 					if(p==0||p==(breadth-1)||o==(height-1))
 					{
 						BlockPos block = new BlockPos(x,y,z);
 						IBlockState iblockstate = Blocks.stone.getDefaultState();
 						world.setBlockState(block, iblockstate);
 					}//o is height , p is length, i is breath
 					if(p==0&&o==(height-1)||p==0&&o==(height-3)||p==(breadth-1)&&o==(height-1)||p==(breadth-1)&&o==(height-3)||p==(breadth-1)&&o==(height-5)||p==0&&o==(height-5))
 					{
 						BlockPos block = new BlockPos(x,y,z);
 						IBlockState iblockstate = Blocks.planks.getDefaultState();
 						world.setBlockState(block, iblockstate);
 					}
 					if(o==1&&p==0&&(i!=0&&i!=(length-1))||o==1&&p==(breadth-1)&&(i!=0&&i!=(length-1)))
 					{
 						BlockPos block = new BlockPos(x,y,z);
 						IBlockState iblockstate = Blocks.glass.getDefaultState();
 						world.setBlockState(block, iblockstate);
 					}
 					
 					for(int t=1;t<(breadth-1);t++)
 					{
 						if(p==t&&i==0||p==t&&i==(length-1)&&(o!=1||o!=2))
 						{
 							BlockPos block = new BlockPos(x,y,z);
 	 						IBlockState iblockstate = Blocks.stone.getDefaultState();
 	 						world.setBlockState(block, iblockstate);
 						}
 					}
 					x= x+1;
 					System.out.println(x);
 				}
 				x=xPosition+1;
 				y=y+1;
 			}
 			x=xPosition+1;
 			y=yPosition;
 			z=z+1;
 		}
 		BlockPos block = new BlockPos(xPosition,yPosition-1,zPosition);
		IBlockState iblockstate = Blocks.dirt.getDefaultState();
		world.setBlockState(block, iblockstate);
		
	 }
	 
}
