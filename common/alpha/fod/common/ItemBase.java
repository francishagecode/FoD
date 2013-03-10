package alpha.fod.common;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;

public class ItemBase extends Item{
	
	public String textFile;

	public ItemBase(int par1, String file) {
		super(par1);
		this.textFile = file;
	}
	
	public String getTextureFile(){
		return textFile;
	}

}
