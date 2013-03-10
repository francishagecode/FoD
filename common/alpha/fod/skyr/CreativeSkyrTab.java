package alpha.fod.skyr;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeSkyrTab extends CreativeTabs
{
	public CreativeSkyrTab(int id, String name) {
		super(id, name);
	}

	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return "Skyr";
	}

	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex() {
		return Block.anvil.blockID;
	}
}