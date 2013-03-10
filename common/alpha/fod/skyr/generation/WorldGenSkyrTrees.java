package alpha.fod.skyr.generation;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSkyrTrees extends WorldGenerator{

	int leaves;
	int wood;
	
	public WorldGenSkyrTrees(int wood, int leaves)
	{
		this.leaves = leaves;
		this.wood = wood;
	}
	
	public boolean generate(World world, Random rand, int x, int y,int z) {
		
		if(world.getBlockId(x, y, z) == 0){
			return false;
		}
		
		y += 2 + rand.nextInt(1);
			
		for(int i = 0; i < 2; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				for(int k = 0; k < 2; k++)
				{
					world.setBlock(x + i, y + j, z + k, wood);
				}
			}
		}
		
		for (int j = 0; j < 2; j++) {	
			world.setBlock(x + 2, y + j, z, leaves);
			world.setBlock(x + 2, y + j, z + 1, leaves);

			world.setBlock(x - 1, y + j, z, leaves);
			world.setBlock(x - 1, y + j, z + 1, leaves);
			
			world.setBlock(x, y + j, z - 1, leaves);
			world.setBlock(x + 1, y + j, z - 1, leaves);
			
			world.setBlock(x, y + j, z + 2, leaves);
			world.setBlock(x + 1, y + j, z + 2, leaves);
		}
		
		for (int i = 0; i < 2; i++) {
			for (int k = 0; k < 2; k++) {
				world.setBlock(x + i, y + 2, z + k, leaves);
				world.setBlock(x + i, y - 1, z + k, leaves);

			}

		}
		

		
		return true;
	}

	
}
