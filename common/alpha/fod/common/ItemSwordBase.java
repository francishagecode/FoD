package alpha.fod.common;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;

public class ItemSwordBase extends ItemSword{
	
	public String textFile;

	public ItemSwordBase(int par1, EnumToolMaterial tool, String file) {
		super(par1, tool);
		this.textFile = file;
	}
	
	public String getTextureFile(){
		return textFile;
	}

}
