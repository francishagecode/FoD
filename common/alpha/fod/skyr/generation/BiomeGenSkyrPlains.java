package alpha.fod.skyr.generation;

import java.util.Random;

import alpha.fod.skyr.Skyr;



import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenSkyrPlains extends BiomeGenBase
{
    public BiomeGenSkyrPlains(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = 3;
        this.theBiomeDecorator.flowersPerChunk = 0;
        this.theBiomeDecorator.grassPerChunk = 12;
        
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        
        this.topBlock = (byte) Skyr.SkyrGrass.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
    }
    
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return new WorldGenSkyrTrees(Skyr.SkyrWood.blockID, Skyr.SkyrLeaves.blockID);
    }

    public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
    {
        return new WorldGenTallGrass(Skyr.Skyweed.blockID, 0);
    }
}
