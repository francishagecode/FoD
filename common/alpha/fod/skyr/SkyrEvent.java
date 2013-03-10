package alpha.fod.skyr;

import alpha.fod.skyr.block.BlockSkyweed;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class SkyrEvent {

	@ForgeSubscribe
	public void bonemealUsed(BonemealEvent event) {
		if (event.world.getBlockId(event.X, event.Y, event.Z) == Skyr.Skyweed.blockID) {
			((BlockSkyweed) Skyr.Skyweed).fertilize(event.world,event.X, event.Y, event.Z);
		}
	}
	 
}
