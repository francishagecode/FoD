package alpha.fod.common;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;

public class ItemAxeBase extends ItemAxe{
	
	public String textFile;

	public ItemAxeBase(int par1, EnumToolMaterial tool, String file) {
		super(par1, tool);
		this.textFile = file;
	}
	
	public String getTextureFile(){
		return textFile;
	}

}
