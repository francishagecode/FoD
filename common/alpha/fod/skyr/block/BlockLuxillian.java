
package alpha.fod.skyr.block;

import java.util.Random;

import alpha.fod.skyr.Skyr;
import alpha.fod.skyr.SkyrRef;



import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockLuxillian extends Block {
	
	public BlockLuxillian(int par1, int par2, Material par3Material) {
		super(par1, par2, par3Material);
	}
	
	public int idDropped(int par1, Random par2Random, int par3) {
		return this.blockID;
	}
	
	public int damageDropped(int par1) {
		return this.blockID;
	}
	

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        if (par1World.provider.dimensionId > 0 || par1World.getBlockId(par2, par3 - 1, par4) != Block.blockLapis.blockID|| !Skyr.SkyrPortal.tryToCreatePortal(par1World, par2, par3, par4))
        {
            if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4))
            {
                par1World.setBlockWithNotify(par2, par3, par4, 0);
            }
            else
            {
                par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate() + par1World.rand.nextInt(10));
            }
        }
       
    }
}
