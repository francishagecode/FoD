package alpha.fod.skyr.generation;

import alpha.fod.skyr.Skyr;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderSkyr extends WorldProvider {

	 public String getDimensionName() 
	 {
		 return "Skyr";
	 }
	 
	 public boolean canRespawnHere()
	 {
		 return true;
	 }
	 
	 public void registerWorldChunkManager()
	 {
		 this.worldChunkMgr = new WorldChunkManagerHell(Skyr.SkyrPlains, 0.8F, 0.0F);
		 this.dimensionId = Skyr.dimensionSkyrID;
	 }
	 
	 @Override
	 public IChunkProvider createChunkGenerator()
	 {
		 return new ChunkProviderSkyr(worldObj, worldObj.getSeed(), true);
	 }
	 
	 public String getSaveFolder()
	 {
		 return "Skyr";
	 }
}
