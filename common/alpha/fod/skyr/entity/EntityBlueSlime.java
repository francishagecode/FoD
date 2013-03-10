package alpha.fod.skyr.entity;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.World;

public class EntityBlueSlime extends EntitySlime{

	public EntityBlueSlime(World par1World) {
		super(par1World);
        this.texture = "/FoD/BlueSlime.png";
	}
	
	public void onLivingUpdate()
    {
        super.onLivingUpdate();
        
        if (!this.onGround && this.motionY < 0.0D)
        {
        	this.motionY = ((this.motionY) < 0.0D ? this.motionY + (Math.abs(this.motionY)/3) :	this.motionY);	//this.motionY *= 0.6D;
        }
        
        if (Math.abs(this.motionX) < 0.001D)
        {
            this.motionX = 0.0D;
        }

        if (Math.abs(this.motionY) < 0.001D)
        {
            this.motionY = 0.0D;
        }

        if (Math.abs(this.motionZ) < 0.001D)
        {
            this.motionZ = 0.0D;
        }

    }

}
