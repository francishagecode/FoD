package alpha.fod.skyr.item;

import alpha.fod.skyr.Skyr;
import alpha.fod.skyr.SkyrRef;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCorruptedStoneSeed extends Item{

	public ItemCorruptedStoneSeed(int par1) {
		super(par1);
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if (par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack)) {
            int var11 = par3World.getBlockId(par4, par5, par6);
            Block soil = Block.blocksList[var11];

            if (soil != null && soil.blockMaterial == Material.rock && par3World.isAirBlock(par4, par5 + 1, par6)) {
                par3World.setBlockWithNotify(par4, par5 + 1, par6, Skyr.corruptedStoneSeedBlock.blockID);
                --par1ItemStack.stackSize;
                return true;
                
            } else return false;
		} else return false;
	}
	
	public String getTextureFile(){
		return SkyrRef.item;
	}
}
