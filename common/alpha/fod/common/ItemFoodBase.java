package alpha.fod.common;

import net.minecraft.item.ItemFood;

public class ItemFoodBase extends ItemFood{
	
	public String textFile;

	public ItemFoodBase(int par1, int t, boolean h, String file) {
		super(par1, t, h);
		this.textFile = file;
	}
	
	public String getTextureFile(){
		return textFile;
	}

}
