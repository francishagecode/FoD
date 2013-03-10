package alpha.fod.common;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;

public class ItemSpadeBase extends ItemSpade{
	
	public String textFile;

	public ItemSpadeBase(int par1, EnumToolMaterial tool, String file) {
		super(par1, tool);
		this.textFile = file;
	}
	
	public String getTextureFile(){
		return textFile;
	}

}
