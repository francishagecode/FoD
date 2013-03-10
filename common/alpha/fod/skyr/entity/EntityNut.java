
package alpha.fod.skyr.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityNut extends EntityThrowable {
	
	public EntityNut(World par1World) {
		super(par1World);
	}
	
	public EntityNut(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
	}
	
	public EntityNut(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}
	
	//hits a block or entity
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
		if (par1MovingObjectPosition.entityHit != null) {
			byte var2 = 0;
			par1MovingObjectPosition.entityHit.attackEntityFrom(
					DamageSource.causeThrownDamage(this, this.getThrower()), var2); //Previously func_85052_h
		}
		
		for (int var3 = 0; var3 < 8; ++var3) {
			this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D); //TODO add nut particles
		}
		
		if (!this.worldObj.isRemote) {
			this.setDead();
		}
	}
}
