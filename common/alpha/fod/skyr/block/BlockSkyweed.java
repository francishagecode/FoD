package alpha.fod.skyr.block;

import java.util.Random;

import alpha.fod.skyr.Skyr;
import alpha.fod.skyr.SkyrRef;



import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockFlower;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockSkyweed extends BlockFlower
{
	public BlockSkyweed(int par1)
    {
        super(par1, 1);
        setTickRandomly(true);
        float f = 0.5F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.disableStats();
        this.setRequiresSelfNotify();
    }
	
	public String getTextureFile(){
		return SkyrRef.block;
	}
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);

        if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
        {
            int i = par1World.getBlockMetadata(par2, par3, par4);

            if (i < 2)
            {
                float f = getGrowthRate(par1World, par2, par3, par4);

                if (par5Random.nextInt((int)(25F / f) + 1) == 0)
                {
                    i++;
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, i);
                }
            }
        }
    }
	
	private float getGrowthRate(World par1World, int par2, int par3, int par4)
    {
        float f = 1.0F;
        int i = par1World.getBlockId(par2, par3, par4 - 1);
        int j = par1World.getBlockId(par2, par3, par4 + 1);
        int k = par1World.getBlockId(par2 - 1, par3, par4);
        int l = par1World.getBlockId(par2 + 1, par3, par4);
        int i1 = par1World.getBlockId(par2 - 1, par3, par4 - 1);
        int j1 = par1World.getBlockId(par2 + 1, par3, par4 - 1);
        int k1 = par1World.getBlockId(par2 + 1, par3, par4 + 1);
        int l1 = par1World.getBlockId(par2 - 1, par3, par4 + 1);
        boolean flag = k == blockID || l == blockID;
        boolean flag1 = i == blockID || j == blockID;
        boolean flag2 = i1 == blockID || j1 == blockID || k1 == blockID || l1 == blockID;

        for (int i2 = par2 - 1; i2 <= par2 + 1; i2++)
        {
            for (int j2 = par4 - 1; j2 <= par4 + 1; j2++)
            {
                int k2 = par1World.getBlockId(i2, par3 - 1, j2);
                float f1 = 0.0F;

                if (k2 == Skyr.SkyrGrass.blockID)
                {
                    f1 = 1.0F;

                    if (par1World.getBlockMetadata(i2, par3 - 1, j2) > 0)
                    {
                        f1 = 3F;
                    }
                }

                if (i2 != par2 || j2 != par4)
                {
                    f1 /= 4F;
                }

                f += f1;
            }
        }

        if (flag2 || flag && flag1)
        {
            f /= 2.0F;
        }

        return f;
    }
	
	protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return par1 == Skyr.SkyrGrass.blockID;
    }
	
	public int getRenderType()
    {
        return 6;
    }

	public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {

		switch (par2) {
		case 0:
			return 1;
		case 1:
			return 2;
		case 2:
			return 3;
		default:
			return 1;
		}
    }
	
	public int idDropped(int par1, Random par2Random, int par3)
    {
        if (par1 == 2)
        {
        	return this.blockID;
        }
        else
        {
            return 0;
        }
    }

	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);

        if (par1World.isRemote)
        {
            return;
        }

        int i = 3 + par7;

        for (int j = 0; j < i; j++)
        {
            if (par1World.rand.nextInt(5) == 0)
            {
                float f = 0.7F;
                float f1 = par1World.rand.nextFloat() * f + (1.0F - f) * 0.5F;
                float f2 = par1World.rand.nextFloat() * f + (1.0F - f) * 0.5F;
                float f3 = par1World.rand.nextFloat() * f + (1.0F - f) * 0.5F;
                EntityItem entityitem = new EntityItem(par1World, (float)par2 + f1, (float)par3 + f2, (float)par4 + f3, new ItemStack(Skyr.Skyweed));
                entityitem.delayBeforeCanPickup = 10;
                par1World.spawnEntityInWorld(entityitem);
            }
        }
    }
	
	public void fertilize(World par1World, int par2, int par3, int par4)
    {
        par1World.setBlockMetadataWithNotify(par2, par3, par4, 2);
    }
}

