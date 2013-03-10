package alpha.fod.skyr.item;

import alpha.fod.skyr.Skyr;
import alpha.fod.skyr.SkyrRef;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemElectricCoil extends Item{

	public ItemElectricCoil(int par1) {
		super(par1);
	}
	

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int par7, float par8, float par9, float par10)
    {
        if (world.provider.dimensionId > 0 || world.getBlockId(i, j - 1, k) != Block.blockLapis.blockID|| !Skyr.SkyrPortal.tryToCreatePortal(world, i, j, k))
    	{
    		Skyr.SkyrPortal.tryToCreatePortal(world, i, j, k);
    	}
    	
    	
    	
        return false;
    }
    
	public String getTextureFile(){
		return SkyrRef.item;
	}

}
