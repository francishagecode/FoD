package alpha.fod.skyr.block;

import java.util.Random;

import alpha.fod.skyr.Skyr;
import alpha.fod.skyr.SkyrRef;



import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockCorruptedStoneSeed extends Block {
	
	private float grow = 0.0F;
	private boolean done;
	
	public BlockCorruptedStoneSeed(int par1, int par2, Material par3Material) {
		super(par1, par2, par3Material);
		this.setHardness(0.0F);
		this.setBlockBounds(-0.1F, -0.2F - grow, -0.1F, 0.9F, 0.25F - grow, 0.9F);
		done = false;
	}
	
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
		if (par1World.getBlockMaterial(par2, par3, par4) == Material.rock) return true;
		else return false;
    }
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);

        if (par1World.getBlockLightValue(par2, par3 + 1, par4) <= 8)
        {
        	if (!done){
        		if (par5Random.nextInt(10) == 0)
        		{
        			grow+=0.1F;
        		}
        	}
        	else {
        		par1World.setBlockWithNotify(par2, par3-1, par4, Skyr.corruptedStone.blockID);
        		par1World.setBlockWithNotify(par2, par3, par4, 0);
        	}
        }
	}
	
	public int getRenderType()
    {
        return 6;
    }	
	
	public String getTextureFile(){
		return SkyrRef.block;
	}
}