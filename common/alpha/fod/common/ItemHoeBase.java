package alpha.fod.common;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;

public class ItemHoeBase extends ItemHoe{
	
	public String textFile;

	public ItemHoeBase(int par1, EnumToolMaterial tool, String file) {
		super(par1, tool);
		this.textFile = file;
	}
	
	public String getTextureFile(){
		return textFile;
	}

}
