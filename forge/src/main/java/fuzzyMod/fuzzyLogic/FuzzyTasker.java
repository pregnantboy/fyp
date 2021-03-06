package fuzzyMod.fuzzyLogic;

import fuzzyMod.entity.EntityMobWithInventory;
import fuzzyMod.targetTasks.NearestTarget;
import fuzzyMod.targetTasks.PlayerLastAttackedTarget;
import fuzzyMod.targetTasks.PlayerLastAttackerTarget;
import fuzzyMod.tasks.ArrowAttack;
import fuzzyMod.tasks.BuildFarm;
import fuzzyMod.tasks.BuildHouse;
import fuzzyMod.tasks.BuildMine;
import fuzzyMod.tasks.FireballAttack;
import fuzzyMod.tasks.HarvestCrops;
import fuzzyMod.tasks.MeleeAttack;
import fuzzyMod.tasks.MineOres;
import fuzzyMod.tasks.RunAway;
import fuzzyMod.tasks.SowSeeds;
import fuzzyMod.tasks.StoreLoot;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

/**
 * Class for dispatching actions to an AI.
 */
public class FuzzyTasker {
	private EntityMobWithInventory mob;
	ArrowAttack arrow;
	FireballAttack fireball;
	MeleeAttack melee;
	BuildHouse smallhouse;
	BuildHouse largehouse;
	BuildMine shortmine;
	BuildMine longmine;
	BuildFarm smallfarm, largefarm;
	HarvestCrops harvest;
	SowSeeds sow;
	StoreLoot loot;
	MineOres mine;
	RunAway runaway;
	int taskMode;
	int buildspeed;
	
	/**
	 * Constructor for Fuzzy Tasker.
	 */
	public FuzzyTasker(EntityMobWithInventory mob) {
		this.mob = mob;
		fireball = new FireballAttack(this.mob, 30, 3);
		melee = new MeleeAttack(this.mob);
		arrow = new ArrowAttack(this.mob, 30);
		smallhouse = new BuildHouse(this.mob, 8, 8, 10);
		largehouse = new BuildHouse(this.mob, 15, 15, 10);
		shortmine = new BuildMine(this.mob, 10);
		longmine = new BuildMine(this.mob, 20);
		smallfarm = new BuildFarm(this.mob, 10, 10);
		largefarm = new BuildFarm(this.mob, 20, 20);
		harvest = new HarvestCrops(this.mob, 30);
		sow = new SowSeeds(this.mob, 30);
		loot = new StoreLoot(this.mob, 30);
		mine = new MineOres(this.mob, 30);
		runaway = new RunAway(this.mob, 1.0D);
		taskMode = -1;
		buildspeed = 5;
	}
	
	/**
	 * Computes and returns the entity to target.
	 * @param mode Chooses which type of target to acquire.
	 */
	public Entity targeter(int mode) {
		Entity target;
		switch (mode) {
		case 0: {
			// nearest enemy
			target = NearestTarget.nearestEnemy(mob);
			break;
		}
		case 1: {
			// player last attacked target
			Entity temp = PlayerLastAttackedTarget.getLastTarget(mob);
			if (temp == null) {
				target = null;
			} else {
				target = mob.getEntityWorld().getEntityByID(temp.getEntityId());
				System.out.println("targeter" + target);
			}
			break;
		}
		case 2: {
			// player last attacker target
			Entity temp = PlayerLastAttackerTarget.getPlayerAttacker();
			if (temp == null) {
				target = null;
			} else {
				target = mob.getEntityWorld().getEntityByID(temp.getEntityId());
			}
			break;
		}
		default: {
			target = null;
			break;
		}
		}
		return target;
	}

	/**
	 * Set the index of the current task.
	 */
	public void setTask(int mode) {
		taskMode = mode;
	}

	/**
	 * Generic method that calls the nextStep or attempBuildBlock method of the current task being asked to the AI.
	 */
	public void nextStep() {
		switch (taskMode) {
		case 0: {
			fireball.nextStep();
			break;
		}
		case 1: {
			melee.nextStep();
			break;
		}
		case 2: {
			arrow.nextStep();
			break;
		}
		case 3: {
			if (!smallhouse.hasBuiltOnce()) {
				smallhouse.init();
				smallhouse.attemptBuildBlock(buildspeed);
			}
			break;
		}
		case 4: {
			if (!largehouse.hasBuiltOnce()) {
				largehouse.init();
				largehouse.attemptBuildBlock(buildspeed);
			}
			break;
		}
		case 5: {
			if (!shortmine.hasBuiltOnce()) {
				shortmine.init();
				shortmine.attemptBuildBlock(buildspeed);
			}
			break;
		}
		case 6: {
			if (!longmine.hasBuiltOnce()) {
				longmine.init();
				longmine.attemptBuildBlock(buildspeed);
			}
			break;
		}
		case 7: {
			if (!smallfarm.hasBuiltOnce()) {
				smallfarm.init();
				smallfarm.attemptBuildBlock(buildspeed);
			}
			break;
		}
		case 8: {
			if (!largefarm.hasBuiltOnce()) {
				largefarm.init();
				largefarm.attemptBuildBlock(buildspeed);
			}
			break;
		}
		case 9: {
			if (this.mob.getMobIventory().isFull()) {
				loot.nextStep();
			} else {
				harvest.nextStep();
			}
			break;
		}
		case 10: {
			sow.nextStep();
			break;
		}
		case 11: {
			if (this.mob.getMobIventory().isFull()) {
				loot.nextStep();
			} else {
				mine.nextStep();
			}
			break;
		}
		case 12: {
			runaway.nextStep();
			break;
		}
		default: {
			return;
		}
		}
	}
	
	/**
	 * Returns true of the mob is currently building a structure. This is mainly used to prevent a mob from starting to mine or farm when it has not finished building a structure.
	 */
	public boolean isBuilding() {
		if (shortmine.isBuilding() || longmine.isBuilding() || smallfarm.isBuilding() || largefarm.isBuilding()
				|| smallhouse.isBuilding() || largehouse.isBuilding()) {
			return true;
		}
		return false;
	}

}
