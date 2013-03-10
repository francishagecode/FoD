package alpha.fod.common;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;

public class ItemPickaxeBase extends ItemPickaxe{
	
	public String textFile;

	public ItemPickaxeBase(int par1, EnumToolMaterial tool, String file) {
		super(par1, tool);
		this.textFile = file;
	}
	
	public String getTextureFile(){
		return textFile;
	}

}
