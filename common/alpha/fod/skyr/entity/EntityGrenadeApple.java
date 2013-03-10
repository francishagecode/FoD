
package alpha.fod.skyr.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGrenadeApple extends EntityItem {
	
	private double bounceFactor;
	private int fuse;
	private boolean exploded;
	
	public EntityGrenadeApple(World world) {
		super(world);
		setSize(0.5F, 0.5F);
		yOffset = height / 2.0F;
		bounceFactor = 0.2;
		exploded = false;
		fuse = 0;
	}
	
	public EntityGrenadeApple(World world, Entity entity) {
		this(world);
		
		setRotation(entity.rotationYaw, 0);
		// Set the velocity
		double xHeading = -MathHelper.sin((entity.rotationYaw * 3.141593F) / 180F);
		double zHeading = MathHelper.cos((entity.rotationYaw * 3.141593F) / 180F);
		motionX = 0.5 * xHeading * MathHelper.cos((entity.rotationPitch / 180F) * 3.141593F);
		motionY = -0.5 * MathHelper.sin((entity.rotationPitch / 180F) * 3.141593F);
		motionZ = 0.5 * zHeading * MathHelper.cos((entity.rotationPitch / 180F) * 3.141593F);
		
		// Set the position
		setPosition(entity.posX + xHeading * 0.8, entity.posY, entity.posZ + zHeading * 0.8);
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		
		fuse = 50;
	}
	
	protected boolean canTriggerWalking() {
		return false;
	}
	
	public void onUpdate() {
		double prevVelX = motionX;
		double prevVelY = motionY;
		double prevVelZ = motionZ;
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		moveEntity(motionX, motionY, motionZ);
		
		if (motionX != prevVelX) {
			motionX = -bounceFactor * prevVelX;
		}
		
		if (motionY != prevVelY) {
			motionY = -bounceFactor * prevVelY;
		}
		else {
			motionY -= 0.04; //Apply gravity.
		}
		
		if (motionZ != prevVelZ) {
			motionZ = -bounceFactor * prevVelZ;
		}
		
		// Air friction
		motionX *= 0.99;
		motionY *= 0.99;
		motionZ *= 0.99;
		
		if (fuse-- <= 0) {
			explode();
		}
	}
	
	private void explode() {
		if (!exploded) {
			exploded = true;
			worldObj.createExplosion(null, posX, posY, posZ, 2F, false);
		}
	}
	
	public boolean attackEntityFrom(DamageSource entity, int i) {
		super.attackEntityFrom(entity, i);
		explode();
		return false;
	}
	
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setByte("Fuse", (byte) fuse);
	}
	
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		fuse = nbttagcompound.getByte("Fuse");
	}
	
}
