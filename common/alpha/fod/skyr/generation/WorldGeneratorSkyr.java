package alpha.fod.skyr.generation;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import alpha.fod.skyr.Skyr;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorSkyr implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int skyrId = Skyr.dimensionSkyrID;
		if(world.provider.dimensionId == skyrId){
			generateSkyr(world, random, chunkX*16, chunkZ*16);
		}
	}

	private void generateSkyr(World world, Random rand, int i, int j) {
		if(rand.nextFloat() > .8){
			int x = i + rand.nextInt(16);
			int z = j + rand.nextInt(16);
			boolean keepGoing = true;
			for(int k = 64; k < 250; k++){
				int block = world.getBlockId(x, k, z);
				if(block == 0 && keepGoing){
					keepGoing = false;
					this.genGranite(world, rand, x, k, z);
				}
			}
		}
	}
	
	public void genGranite(World world, Random rand, int x, int y, int z){
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				for(int k = 0; k < 3; k++)
				{
					int id = world.getBlockId(x + i, y + j, z + k);
					int belowId = world.getBlockId(x + i, y + j - 1, z + k);
					if(id == 0 && (belowId == Skyr.granite.blockID || belowId == Skyr.SkyrGrass.blockID)){
						System.out.println("This");
						world.setBlock(x + i, y + j, z + k, Skyr.granite.blockID);
					}
				}
			}
		}
	}




}





